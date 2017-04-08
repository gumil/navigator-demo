package io.github.gumil.testnavigator.childcontroller.child

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView

internal class ChildLayout(
        private val title: String,
        private val bgColor: Int,
        private val isColorRes: Boolean
): ViewLayout() {

    override fun createView(context: Context) = with(context) {
        frameLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            var color = bgColor
            if (isColorRes) {
                color = getColorRes(bgColor)
            }

            backgroundColor = color

            textView(title) {
                textSize = 20f
                gravity = Gravity.CENTER
            }
        }
    }

}