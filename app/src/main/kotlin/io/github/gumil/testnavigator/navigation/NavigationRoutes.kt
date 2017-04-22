package io.github.gumil.testnavigator.navigation

import android.content.Context
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.home.HomeKey

internal class NavigationRoutes(
        isFullScreen: Boolean = true
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

    var onNext: (Context, Int, DisplayUpMode) -> Unit = { ctx, index, display ->
        if (isFullScreen) {
            Navigator.getBackstack(ctx).goTo(NavigationKey(
                    index, display
            ).apply {
                changeHandler = SegueViewChangeHandler()
            })
        }
    }
}