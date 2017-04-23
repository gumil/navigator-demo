package io.github.gumil.testnavigator.multiple

import android.content.Context
import android.view.ViewGroup
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.navigation.NavigationLayout
import org.jetbrains.anko.ctx
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout

internal class MultipleLayout : ViewLayout() {

    val children = mutableListOf<ViewGroup>()

    override fun createView(context: Context) = with(context) {
        toolbarTitle = "Child Router Demo"
        verticalLayout {
            for (i in 0..2) {
                frameLayout {
                    addView(NavigationLayout().apply {
                        navigationRoutes.isFullScreen = false
                        container = this@frameLayout.apply {
                            tag = "NAVIGATION TAG $i"
                        }
                    }.inflate(ctx))
                }.lparams(matchParent, 0) {
                    weight = 1f
                }.let { children.add(it) }
            }
        }
    }

}