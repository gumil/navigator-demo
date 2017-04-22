package io.github.gumil.testnavigator.multiple

import android.content.Context
import android.view.ViewGroup
import io.github.gumil.testnavigator.common.ViewLayout
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout

internal class MultipleLayout : ViewLayout() {

    val children = mutableListOf<ViewGroup>()

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            for (i in 0..2) {
                frameLayout {
                }.lparams(matchParent, 0) {
                    weight = 1f
                }.let { children.add(it) }
            }
        }
    }

}