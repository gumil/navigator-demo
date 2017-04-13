package io.github.gumil.testnavigator.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import io.github.gumil.testnavigator.R

internal fun Context.getSelectableItemBackground(): Drawable {
    val typedArray = this.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackground))
    return typedArray.getDrawable(0).apply {
        typedArray.recycle()
    }
}

internal fun Button.applyButtonBarStyle(): Button {
    background = context.getSelectableItemBackground()
    textAppearance(R.style.TextAppearance_AppCompat_Widget_Button_Borderless_Colored)
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

internal fun Context.getColorRes(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

internal fun View.waitForMeasure(callback: (View, Int, Int) -> Unit) {
    val width = width
    val height = height

    if (width > 0 && height > 0) {
        callback(this, width, height)
        return
    }

    val view = this
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            val observer = viewTreeObserver
            if (observer.isAlive) {
                observer.removeOnPreDrawListener(this)
            }

            callback(view, width, height)

            return true
        }
    })
}

internal fun TextView.textAppearance(@StyleRes style: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextAppearance(style)
    } else {
        setTextAppearance(context, style)
    }
}

internal fun Context.isOrientation(orientation: Int) = resources.configuration.orientation == orientation