package io.github.gumil.testnavigator.changehandler

import android.transition.Transition
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

class SharedElementDelayingChangeHandler(
        private val waitForTransitionNames: MutableList<String> = mutableListOf<String>()
) : ArcFadeMoveChangeHandler() {

    private val removedViews = mutableListOf<Pair<View, ViewGroup>>()
    private var onPreDrawListener: ViewTreeObserver.OnPreDrawListener? = null


    override fun prepareForTransition(container: ViewGroup, previousView: View, newView: View,
                                      transition: Transition, direction: Int,
                                      onTransitionPreparedListener: () -> Unit) {
        newView.parent?.let {
            if (waitForTransitionNames.isNotEmpty()) {
                onPreDrawListener = ViewTreeObserver.OnPreDrawListener {
                    val addedSubviewListeners: Boolean = false
                    val foundViews = mutableListOf<View>()
                    for (transitionName in waitForTransitionNames) {
                        getViewWithTransitionName(previousView, transitionName)?.let {
                            foundViews.add(it)
                        } ?: return@OnPreDrawListener false

                        if (!addedSubviewListeners) {
                            for (view in foundViews) {
                                view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                                    override fun onPreDraw(): Boolean {
                                        view.viewTreeObserver.removeOnPreDrawListener(this)
                                        waitForTransitionNames.remove(view.transitionName)

                                        val parent = view.parent as ViewGroup
                                        removedViews.add(Pair(view, parent))
                                        parent.removeView(view)

                                        if (waitForTransitionNames.size == 0) {
                                            newView.viewTreeObserver.removeOnPreDrawListener(onPreDrawListener)

                                            newView.visibility = View.INVISIBLE

                                            onTransitionPreparedListener()
                                        }
                                        return true
                                    }
                                })
                            }
                        }
                    }
                    false
                }
            }
        } ?: onTransitionPreparedListener()
    }

    private fun getViewWithTransitionName(view: View, transitionName: String): View? {
        if (transitionName == view.transitionName) {
            return view
        }

        if (view is ViewGroup) {
            val viewGroup = view
            val childCount = viewGroup.childCount

            (0..childCount - 1).mapNotNull {
                return getViewWithTransitionName(viewGroup.getChildAt(it), transitionName)
            }
        }

        return null
    }

    override fun executePropertyChanges(container: ViewGroup, previousView: View, newView: View,
                                        transition: Transition, direction: Int) {
        newView.visibility = View.VISIBLE

        for (removedView in removedViews) {
            removedView.second.addView(removedView.first)
        }

        removedViews.clear()
        super.executePropertyChanges(container, previousView, newView, transition, direction)
    }
}