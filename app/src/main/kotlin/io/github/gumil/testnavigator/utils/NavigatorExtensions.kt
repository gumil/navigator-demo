package io.github.gumil.testnavigator.utils

import android.content.Context
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.MainActivity
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.multiple.StateChangerStack

internal fun Context.goTo(stateChanger: StateChanger, viewKey: ViewKey) {
    StateChangerStack.push(stateChanger)
    MainActivity.instance?.setChildStateChanger(stateChanger)
    Navigator.getBackstack(this).goTo(viewKey)
    MainActivity.instance?.removeChildStateChanger()
}

internal fun Context.goBack(stateChanger: StateChanger) {
    StateChangerStack.pop()
    MainActivity.instance?.setChildStateChanger(stateChanger)
    Navigator.getBackstack(this).goBack()
    MainActivity.instance?.removeChildStateChanger()
}