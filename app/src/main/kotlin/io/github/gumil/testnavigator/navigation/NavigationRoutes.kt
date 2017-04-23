package io.github.gumil.testnavigator.navigation

import android.content.Context
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.MainActivity
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.common.ViewStateChanger
import io.github.gumil.testnavigator.home.HomeKey
import io.github.gumil.testnavigator.multiple.StateChangerStack

internal class NavigationRoutes(
        var isFullScreen: Boolean = true
) {
    var onPop: (Context) -> Unit = {
        if (isFullScreen) {
            Navigator.getBackstack(it).goTo(HomeKey())
        }
    }

    var onUp: (Context) -> Unit = {
        if (isFullScreen) {
            Navigator.getBackstack(it).goTo(NavigationKey().apply {
                changeHandler = SegueViewChangeHandler()
            })
        }
    }

    var onNext: (Context, Int, DisplayUpMode, ViewGroup?) -> Unit = { ctx, index, display, container ->
        if (isFullScreen) {
            Navigator.getBackstack(ctx).goTo(NavigationKey(
                    index, display
            ).apply {
                changeHandler = SegueViewChangeHandler()
            })
        } else {
            container?.let {
                ctx.goTo(ViewStateChanger(ctx, it), NavigationKey(index, display,
                        container.tag as String).apply {
                    (layout as? NavigationLayout)?.let {
                        it.navigationRoutes.isFullScreen = false
                        it.container = container
                        changeHandler = SegueViewChangeHandler()
                    }
                })
            }
        }
    }


    private fun Context.goTo(stateChanger: StateChanger, viewKey: ViewKey) {
        MainActivity.instance?.setChildStateChanger(stateChanger)
        Navigator.getBackstack(this).goTo(viewKey)
        MainActivity.instance?.removeChildStateChanger()
    }
}