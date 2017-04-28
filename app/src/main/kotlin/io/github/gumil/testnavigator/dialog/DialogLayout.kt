package io.github.gumil.testnavigator.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.method.LinkMovementMethod
import android.view.Gravity
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.applyButtonBarStyle
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.*

internal class DialogLayout(
        private val title: String,
        private val description: CharSequence
) : ViewLayout() {

    companion object {
        const val DIALOG_BACKGROUND_ID = 9899
    }

    override fun createView(context: Context) = with(context) {
        frameLayout {
            view {
                id = DIALOG_BACKGROUND_ID
                backgroundColor = getColorRes(R.color.immersive_background)
            }.lparams(matchParent, matchParent)

            verticalLayout {
                elevation = resources.getDimensionPixelSize(R.dimen.dialog_elevation).toFloat()
                minimumWidth = dip(280)
                transitionName = getString(R.string.fab_dialog_transition_name)
                backgroundResource = R.drawable.dialog_bg

                textView(title) {
                    gravity = Gravity.CENTER_VERTICAL
                    textSize = 18f
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = Color.BLACK
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(20)
                    topMargin = dip(24)
                    leftMargin = dip(24)
                    rightMargin = dip(24)
                }

                textView(description) {
                    gravity = Gravity.CENTER_VERTICAL
                    textSize = 20f
                    movementMethod = LinkMovementMethod.getInstance()
                    textColor = Color.BLACK
                }.lparams(wrapContent, wrapContent) {
                    leftMargin = dip(24)
                    rightMargin = dip(24)
                }

                button().applyButtonBarStyle {
                    text = getString(android.R.string.ok)
                    minWidth = dip(64)
                    onClick {
                        Navigator.getBackstack(ctx).goBack()
                    }
                }.lparams(wrapContent, dip(36)) {
                    gravity = Gravity.END or Gravity.BOTTOM
                    bottomMargin = dip(8)
                    marginEnd = dip(8)
                    leftMargin = dip(8)
                    rightMargin = dip(8)
                    topMargin = dip(8)
                }
            }.lparams(wrapContent, wrapContent) {
                bottomMargin = dip(8)
                topMargin = dip(8)
                leftMargin = dip(16)
                rightMargin = dip(16)
                gravity = Gravity.CENTER
            }

        }
    }
}