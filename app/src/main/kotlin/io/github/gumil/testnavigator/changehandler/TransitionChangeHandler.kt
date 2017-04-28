package io.github.gumil.testnavigator.changehandler

import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import com.zhuinden.simplestack.navigator.ViewChangeHandler

abstract class TransitionChangeHandler : ViewChangeHandler {

    override fun performViewChange(container: ViewGroup,
                                   previousView: View,
                                   newView: View,
                                   direction: Int,
                                   completionCallback: ViewChangeHandler.CompletionCallback) {
        val transition = getTransition(container, previousView, newView, direction)

        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                completionCallback.onCompleted()
            }

            override fun onTransitionResume(transition: Transition?) {}

            override fun onTransitionPause(transition: Transition?) {}

            override fun onTransitionCancel(transition: Transition?) {
                completionCallback.onCompleted()
            }

            override fun onTransitionStart(transition: Transition?) {}
        })

        prepareForTransition(container, previousView, newView, transition, direction) {
            TransitionManager.beginDelayedTransition(container, transition)
            executePropertyChanges(container, previousView, newView, transition, direction)
        }
    }

    protected open fun prepareForTransition(container: ViewGroup, previousView: View, newView: View,
                                            transition: Transition, direction: Int,
                                            onTransitionPreparedListener: () -> Unit) {
        onTransitionPreparedListener()
    }


    abstract fun getTransition(container: ViewGroup, previousView: View, newView: View, direction: Int): Transition

    protected open fun executePropertyChanges(container: ViewGroup,
                                              previousView: View,
                                              newView: View,
                                              transition: Transition,
                                              direction: Int) {
        if (previousView.parent == container) {
            container.removeView(previousView)
        }
        if (newView.parent == null) {
            container.addView(newView)
        }
    }

}