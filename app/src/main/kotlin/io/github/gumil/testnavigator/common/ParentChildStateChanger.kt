package io.github.gumil.testnavigator.common

import android.content.Context
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger

internal class ParentChildStateChanger(
        context: Context,
        container: ViewGroup
) : StateChanger {

    private val viewStateChanger = ViewStateChanger(context, container)

    var childStateChanger: StateChanger? = null

    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        childStateChanger?.handleStateChange(stateChange, completionCallback)
                ?: viewStateChanger.handleStateChange(stateChange, completionCallback)
    }

}