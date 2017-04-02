package io.github.gumil.testnavigator.navigation

internal enum class DisplayUpMode {
    SHOW,
    SHOW_FOR_CHILDREN_ONLY,
    HIDE;

    val displayUpModeForChild: DisplayUpMode
        get() {
            when (this) {
                HIDE -> return HIDE
                else -> return SHOW
            }
        }
}