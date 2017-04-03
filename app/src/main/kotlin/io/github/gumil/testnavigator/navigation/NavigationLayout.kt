package io.github.gumil.testnavigator.navigation

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.zhuinden.navigator.Navigator
import com.zhuinden.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.home.HomeDemoModel
import io.github.gumil.testnavigator.home.HomeKey
import io.github.gumil.testnavigator.utils.applyButtonBarStyle
import io.github.gumil.testnavigator.utils.getMaterialColor
import org.jetbrains.anko.*

internal class NavigationLayout(
        val index: Int = 0,
        val displayUpMode: DisplayUpMode = DisplayUpMode.SHOW_FOR_CHILDREN_ONLY
) : ViewLayout() {

    private lateinit var popButton: Button
    private lateinit var nextButton: Button
    private lateinit var upButton: Button

    override fun createView(context: Context) = with(context) {
        toolbarTitle = HomeDemoModel.NAVIGATION.title
        verticalLayout {
            backgroundColor = getMaterialColor(index)

            textView {
                textSize = 20f
                text = getString(R.string.navigation_title, index)
                gravity = Gravity.CENTER
            }.lparams(matchParent, 0) {
                weight = 1f
            }

            linearLayout {
                backgroundColor = Color.WHITE

                popButton = button {
                    text = getString(R.string.pop_to_root)

                    onClick {
                        Navigator.getBackstack(ctx).goTo(HomeKey())
                    }
                }.lparams(0, matchParent) {
                    weight = 1f
                }.applyButtonBarStyle()

                upButton = button {
                    text = getString(R.string.go_up)
                    visibility = if (displayUpMode != DisplayUpMode.SHOW) View.GONE else View.VISIBLE

                    onClick {
                        Navigator.getBackstack(ctx).goTo(NavigationKey().apply {
                            viewChangeHandler = SegueViewChangeHandler()
                        })
                    }
                }.lparams(0, matchParent) {
                    weight = 1f
                }.applyButtonBarStyle()

                nextButton = button {
                    text = getString(R.string.next_controller)

                    onClick {
                        Navigator.getBackstack(ctx).goTo(NavigationKey(
                                index + 1, displayUpMode.displayUpModeForChild
                        ).apply {
                            viewChangeHandler = SegueViewChangeHandler()
                        })
                    }
                }.lparams(0, matchParent) {
                    weight = 1f
                }.applyButtonBarStyle()
            }.lparams(matchParent, dip(72)) {
                gravity = Gravity.BOTTOM
            }
        }
    }


    fun setButtonsEnabled(enabled: Boolean) {
        nextButton.isEnabled = enabled
        popButton.isEnabled = enabled
        upButton.isEnabled = enabled
    }
}