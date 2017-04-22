package io.github.gumil.testnavigator.child

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.changehandlers.NoOpViewChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import org.jetbrains.anko.frameLayout

internal class ChildStateChanger(
        val context: Context,
        val onChangeCompleted: (Int) -> Unit,
        val containerList: List<ViewGroup>
) : StateChanger {

    var index: Int = 0

    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        val newKey = stateChange.topNewState<ViewKey>()
        val previousKey = stateChange.topPreviousState<ViewKey>()
        val newContext = stateChange.createContext(context, newKey)
        val container: ViewGroup
        val newView: View

        if (stateChange.direction == StateChange.FORWARD) {
            container = containerList[index++]
            newView = newKey.layout.inflate(newContext)
        } else {
            container = containerList[--index]
            newView = with(newContext) { frameLayout {  } }
        }

        val previousView = container.getChildAt(0)

        if (previousView == null) {
            container.addView(newView)
            onChangeCompleted(stateChange.direction)
            completionCallback.stateChangeComplete()
            newKey.onChangeEnded()
        } else {
            val viewChangeHandler = if (stateChange.direction == StateChange.FORWARD) {
                newKey.viewChangeHandler()
            } else if (previousKey != null && stateChange.direction == StateChange.BACKWARD) {
                previousKey.viewChangeHandler()
            } else {
                NoOpViewChangeHandler()
            }

            viewChangeHandler.performViewChange(
                    container,
                    previousView,
                    newView,
                    stateChange.direction
            ) {
                onChangeCompleted(stateChange.direction)
                completionCallback.stateChangeComplete()
                newKey.onChangeEnded()
            }
        }
    }

}