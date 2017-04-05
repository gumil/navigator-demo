package io.github.gumil.testnavigator.common

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.zhuinden.navigator.DefaultStateChanger
import com.zhuinden.navigator.Navigator
import com.zhuinden.navigator.ViewChangeHandler
import com.zhuinden.navigator.changehandlers.NoOpViewChangeHandler
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import io.github.gumil.testnavigator.MainActivity

internal class ViewStateChanger(
        val context: Context,
        val container: ViewGroup,
        var externalStateChanger: StateChanger = ViewStateChanger.NoOpStateChanger(),
        var viewChangeCompletionListener: DefaultStateChanger.ViewChangeCompletionListener = ViewStateChanger.NoOpViewChangeCompletionListener()
) : StateChanger {

    private class NoOpStateChanger : StateChanger {
        override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
            completionCallback.stateChangeComplete()
        }
    }

    private class NoOpViewChangeCompletionListener : DefaultStateChanger.ViewChangeCompletionListener {
        override fun handleViewChangeComplete(stateChange: StateChange,
                                              container: ViewGroup,
                                              previousView: View?,
                                              newView: View,
                                              completionCallback: DefaultStateChanger.ViewChangeCompletionListener.Callback) {
            completionCallback.viewChangeComplete()
        }
    }

    private fun finishStateChange(stateChange: StateChange,
                                  container: ViewGroup,
                                  previousView: View?,
                                  newView: View,
                                  completionCallback: StateChanger.Callback) {
        viewChangeCompletionListener.handleViewChangeComplete(stateChange, container, previousView,
                newView) { completionCallback.stateChangeComplete() }
    }

    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        externalStateChanger.handleStateChange(stateChange, StateChanger.Callback {
            if (stateChange.topNewState<Any>() == stateChange.topPreviousState<Any>()) {
                completionCallback.stateChangeComplete()
                return@Callback
            }
            val previousKey = stateChange.topPreviousState<ViewKey>()
            val previousView = container.getChildAt(0)
            if (previousView != null && previousKey != null) {
                Navigator.persistViewToState(previousView)
            }
            val newKey = stateChange.topNewState<ViewKey>()
            val newContext = stateChange.createContext(context, newKey)
            val newView = newKey.layout().inflate(newContext)
            Navigator.restoreViewFromState(newView)

            newKey.onChangeStarted()
            setAnimating(context, true)
            if (previousView == null) {
                container.addView(newView)
                finishStateChange(stateChange, container, previousView, newView, completionCallback)
                newKey.onChangeEnded()
                setAnimating(context, false)
            } else {
                val viewChangeHandler: ViewChangeHandler
                if (stateChange.direction == StateChange.FORWARD) {
                    viewChangeHandler = newKey.viewChangeHandler()
                } else if (previousKey != null && stateChange.direction == StateChange.BACKWARD) {
                    viewChangeHandler = previousKey.viewChangeHandler()
                } else {
                    viewChangeHandler = NoOpViewChangeHandler()
                }
                viewChangeHandler.performViewChange(container,
                        previousView,
                        newView,
                        stateChange.direction
                ) {
                    finishStateChange(stateChange, container, previousView, newView, completionCallback)
                    newKey.onChangeEnded()
                    setAnimating(context, false)
                }
            }
        })
    }

    private fun setAnimating(context: Context, isAnimating: Boolean) {
        (context as? MainActivity)?.let {
            it.isAnimating = isAnimating
        }
    }
}