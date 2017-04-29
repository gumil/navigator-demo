package io.github.gumil.testnavigator.widget

import android.content.Context
import org.jetbrains.anko.custom.ankoView

fun Context.dragDismissLayout(theme: Int = 0) = dragDismissLayout(theme) {}
inline fun Context.dragDismissLayout(theme: Int = 0,
                                         init: ElasticDragDismissFrameLayout.() -> Unit)
        = ankoView(::ElasticDragDismissFrameLayout, theme, init)