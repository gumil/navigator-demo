package io.github.gumil.testnavigator.transition

import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.ColorRes
import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhuinden.navigator.Navigator
import com.zhuinden.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.changehandler.ArcFadeMoveChangeHandler
import io.github.gumil.testnavigator.changehandler.CircularRevealChangeHandler
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.changehandler.FlipChangeHandler
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.home.HomeDemoModel
import io.github.gumil.testnavigator.home.HomeKey
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton

internal class TransitionLayout(
        private @ColorRes val colorId: Int,
        private val title: String,
        private val index: Int
) : ViewLayout() {

    lateinit var container: ViewGroup
    lateinit var viewNext: View

    companion object {
        private const val containerId = 1
    }

    override fun createView(context: Context) = with(context) {
        toolbarTitle = HomeDemoModel.TRANSITIONS.title
        container = if (index == 5) getSharedView() else getNormalView()

        container
    }

    private fun Context.getNormalView(): ViewGroup {
        return frameLayout {
            id = containerId
            view {
                backgroundColor = getColorRes(colorId)
            }.lparams(matchParent, matchParent)

            textView(title)
                    .setAttributes(ctx)
                    .lparams(matchParent, dip(80)) {
                        topMargin = dip(96)
                    }

            viewNext = floatingActionButton()
                    .setAttributes(ctx)
                    .lparams(wrapContent, wrapContent) {
                        gravity = Gravity.BOTTOM or Gravity.END
                        margin = dip(24)
                    }
        }
    }

    private fun Context.getSharedView(): ViewGroup {
        return frameLayout {
            id = containerId
            textView(title)
                    .setAttributes(ctx)
                    .lparams(matchParent, dip(80)) {
                        topMargin = dip(24)
                    }

            viewNext = floatingActionButton()
                    .setAttributes(ctx)
                    .lparams(wrapContent, wrapContent) {
                        gravity = Gravity.CENTER
                        margin = dip(24)
                    }
        }
    }

    private fun TextView.setAttributes(context: Context): TextView {
        id = 123456
        transitionName = context.getString(R.string.transition_tag_title)
        textSize = 20f
        gravity = Gravity.CENTER_HORIZONTAL

        return this
    }

    private fun FloatingActionButton.setAttributes(context: Context): FloatingActionButton {
        id = 654321
        imageResource = R.drawable.ic_arrow_forward_white_36dp
        elevation = dip(0).toFloat()
        compatElevation = dip(0).toFloat()
        transitionName = context.getString(R.string.transition_tag_dot)
        backgroundTintList = ColorStateList.valueOf(context.getColorRes(getButtonColor()))

        onClick {
            goTo(context, index + 1)
        }

        return this
    }

    @ColorRes
    private fun getButtonColor(): Int {
        val nextIndex = index + 1
        val buttonColor = if (nextIndex < TransitionDemo.values().size) {
            TransitionDemo.fromIndex(nextIndex).colorId
        } else 0

        if (buttonColor == 0) {
            return TransitionDemo.fromIndex(0).colorId
        }

        return buttonColor
    }

    private fun goTo(context: Context, index: Int) {

        if (index >= TransitionDemo.values().size) {
            Navigator.getBackstack(context).goTo(HomeKey())
            return
        }

        val demo = TransitionDemo.fromIndex(index)
        val transitionKey =
                when (demo) {
                    TransitionDemo.VERTICAL -> TransitionKey()
                    TransitionDemo.CIRCULAR -> TransitionKey(demo,
                            CircularRevealChangeHandler(viewNext, container))
                    TransitionDemo.FADE -> TransitionKey(demo,
                            FadeChangeHandler())
                    TransitionDemo.FLIP -> TransitionKey(demo,
                            FlipChangeHandler())
                    TransitionDemo.HORIZONTAL -> TransitionKey(demo,
                            SegueViewChangeHandler())
                    TransitionDemo.ARC_FADE -> TransitionKey(demo,
                            ArcFadeMoveChangeHandler())
                    TransitionDemo.ARC_FADE_RESET -> TransitionKey(demo,
                            ArcFadeMoveChangeHandler())
                }

        Navigator.getBackstack(context).goTo(transitionKey)
    }
}