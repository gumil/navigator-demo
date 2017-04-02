package io.github.gumil.testnavigator.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.Button
import io.github.gumil.testnavigator.R

internal fun Context.getSelectableItemBackground(): Drawable {
    val typedArray = this.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackground))
    return typedArray.getDrawable(0).apply {
        typedArray.recycle()
    }
}

internal fun Button.applyButtonBarStyle(): Button {
    background = context.getSelectableItemBackground()
    setTextAppearance(context, R.style.TextAppearance_AppCompat_Widget_Button_Borderless_Colored)
    return this
}

internal fun Context.getMaterialColor(index: Int): Int {
    val colors = resources.obtainTypedArray(R.array.mdcolor_300)

    return colors.getColor(index % colors.length(), Color.BLACK).apply {
        colors.recycle()
    }
}


internal fun Context.findActivity(): Activity {
    if (this is Activity) {
        return this
    } else {
        val contextWrapper = this as ContextWrapper
        val baseContext = contextWrapper.baseContext ?: throw IllegalStateException("Activity was not found as base context of view!")
        return baseContext.findActivity()
    }
}