package io.github.gumil.testnavigator.transition

import android.support.annotation.ColorRes
import com.zhuinden.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.changehandler.VerticalChangeHandler

internal enum class TransitionDemo(
        val title: String,
        @ColorRes val colorId: Int,
        val changeHandler: ViewChangeHandler
) {
    VERTICAL("Vertical Slide Animation",
            R.color.blue_grey_300,
            VerticalChangeHandler()),
    CIRCULAR("Circular Reveal Animation (on Lollipop and above, else Fade)",
            R.color.red_300,
            VerticalChangeHandler()),
    FADE("Fade Animation",
            R.color.blue_300,
            VerticalChangeHandler()),
    FLIP("Flip Animation",
            R.color.deep_orange_300,
            VerticalChangeHandler()),
    HORIZONTAL("Horizontal Slide Animation",
            R.color.green_300,
            VerticalChangeHandler()),
    ARC_FADE("Arc/Fade Shared Element Transition (on Lollipop and above, else Fade)",
            0,
            VerticalChangeHandler()),
    ARC_FADE_RESET("Arc/Fade Shared Element Transition (on Lollipop and above, else Fade)",
            R.color.pink_300,
            VerticalChangeHandler());


    companion object {

        fun fromIndex(index: Int): TransitionDemo {
            return TransitionDemo.values()[index]
        }
    }
}