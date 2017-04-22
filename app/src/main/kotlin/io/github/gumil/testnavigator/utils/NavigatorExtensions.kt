package io.github.gumil.testnavigator.utils

import android.content.Context
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.MainActivity
import io.github.gumil.testnavigator.common.ViewKey

internal fun Context.goTo(stateChanger: StateChanger, viewKey: ViewKey) {
    MainActivity.instance?.setChildStateChanger(stateChanger)
    Navigator.getBackstack(this).goTo(viewKey)
    MainActivity.instance?.removeChildStateChanger()
}