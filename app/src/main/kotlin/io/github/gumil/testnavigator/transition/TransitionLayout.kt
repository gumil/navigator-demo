package io.github.gumil.testnavigator.transition

import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.ColorRes
import android.view.Gravity
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.home.HomeDemoModel
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton

internal class TransitionLayout(
        @ColorRes val colorId: Int,
        val title: String,
        val index: Int
): ViewLayout() {

    override fun createView(context: Context) = with(context) {
        toolbarTitle = HomeDemoModel.TRANSITIONS.title
        frameLayout {
            view {
                backgroundColor = getColorRes(colorId)
            }.lparams(matchParent, matchParent)

            textView(title) {
                gravity = Gravity.CENTER_HORIZONTAL
                textSize = 20f
            }.lparams(matchParent, dip(80)) {
                topMargin = dip(96)
                transitionName = getString(R.string.transition_tag_title)
            }

            floatingActionButton {
                imageResource = R.drawable.ic_arrow_forward_white_36dp
                elevation = dip(0).toFloat()
                compatElevation = dip(0).toFloat()
                transitionName = getString(R.string.transition_tag_dot)
                backgroundTintList = ColorStateList.valueOf(getColorRes(getButtonColor()))
            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(24)
            }
        }
    }

    @ColorRes
    fun getButtonColor(): Int {
        val nextIndex = index + 1
        val buttonColor =  if (nextIndex < TransitionDemo.values().size) {
            TransitionDemo.fromIndex(nextIndex).colorId
        } else { 0 }

        if(buttonColor == 0) {
            return TransitionDemo.fromIndex(0).colorId
        }

        return buttonColor
    }

}