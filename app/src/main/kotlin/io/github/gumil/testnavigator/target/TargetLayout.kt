package io.github.gumil.testnavigator.target

import android.content.Context
import android.view.Gravity
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.applyButtonBarStyle
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.*

internal class TargetLayout : ViewLayout() {

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            backgroundColor = getColorRes(R.color.pink_300)

            textView {
                gravity = Gravity.CENTER
            }.lparams(matchParent, 0) {
                weight = 1f
            }


            val size = resources.getDimensionPixelSize(R.dimen.display_target_image_size)
            imageView {

            }.lparams(size, size) {
                gravity = Gravity.CENTER_HORIZONTAL
                margin = resources.getDimensionPixelSize(R.dimen.display_target_image_margin)
            }

            linearLayout {
                backgroundColor = getColorRes(android.R.color.white)

                button(getString(R.string.pick_title))
                        .applyButtonBarStyle()
                        .lparams(0, matchParent) {
                            weight = 1f
                        }

                button(getString(R.string.pick_image))
                        .applyButtonBarStyle()
                        .lparams(0, matchParent) {
                            weight = 1f
                        }

            }.lparams(matchParent, dip(80)) {
                gravity = Gravity.BOTTOM
            }
        }
    }

}