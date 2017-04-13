package io.github.gumil.testnavigator.home

import android.support.annotation.ColorRes
import io.github.gumil.testnavigator.R

internal enum class HomeDemoModel(
        val title: String,
        @ColorRes val color: Int
) {
    NAVIGATION("Navigation Demos", R.color.red_300),
    TRANSITIONS("Transition Demos", R.color.blue_grey_300),
    SHARED_ELEMENT_TRANSITIONS("Shared Element Demos", R.color.purple_300),
    CHILD_CONTROLLERS("Child Controllers", R.color.orange_300),
    VIEW_PAGER("ViewPager", R.color.green_300),
    TARGET_CONTROLLER("Target Controller", R.color.pink_300),
    MULTIPLE_CHILD_ROUTERS("Multiple Child Routers", R.color.deep_orange_300),
    MASTER_DETAIL("Master Detail", R.color.grey_300),
    DRAG_DISMISS("Drag Dismiss", R.color.lime_300),
//    RX_LIFECYCLE("Rx Lifecycle", R.color.teal_300),
//    RX_LIFECYCLE_2("Rx Lifecycle 2", R.color.brown_300)
}