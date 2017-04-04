package io.github.gumil.testnavigator.transition

import android.support.annotation.ColorRes
import io.github.gumil.testnavigator.R

internal enum class TransitionDemo(
        val title: String,
        @ColorRes val colorId: Int
) {
    VERTICAL("Vertical Slide Animation",
            R.color.blue_grey_300),
    CIRCULAR("Circular Reveal Animation (on Lollipop and above, else Fade)",
            R.color.red_300),
    FADE("Fade Animation",
            R.color.blue_300),
    FLIP("Flip Animation",
            R.color.deep_orange_300),
    HORIZONTAL("Horizontal Slide Animation",
            R.color.green_300),
    ARC_FADE("Arc/Fade Shared Element Transition (on Lollipop and above, else Fade)",
            0),
    ARC_FADE_RESET("Arc/Fade Shared Element Transition (on Lollipop and above, else Fade)",
            R.color.pink_300);


    companion object {

        fun fromIndex(index: Int): TransitionDemo {
            return TransitionDemo.values()[index]
        }
    }
}