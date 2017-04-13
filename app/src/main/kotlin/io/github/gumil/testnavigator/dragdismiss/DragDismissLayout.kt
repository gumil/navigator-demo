package io.github.gumil.testnavigator.dragdismiss

import android.content.Context
import android.view.Gravity
import android.view.View
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.getColorRes
import io.github.gumil.testnavigator.widget.ElasticDragDismissFrameLayout
import io.github.gumil.testnavigator.widget.dragDismissLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

internal class DragDismissLayout : ViewLayout() {
    override fun createView(context: Context) = with(context) {
        toolbarTitle = "Drag to Dismiss"
        dragDismissLayout {
            addListener(object : ElasticDragDismissFrameLayout.ElasticDragDismissCallback() {
                override fun onDragDismissed() {
                    super.onDragDismissed()
                    Navigator.getBackstack(ctx).goBack()
                }
            })

            verticalLayout {
                backgroundColor = getColorRes(R.color.lime_300)

                textView(getString(R.string.drag_to_dismiss)) {
                    textSize = 22f
                    padding = dip(16)
                    isClickable = true
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                nestedScrollView {
                    clipToPadding = false
                    isFillViewport = true
                    overScrollMode = View.OVER_SCROLL_NEVER

                    textView(getString(R.string.lorem_ipsum)) {
                        backgroundColor = getColorRes(android.R.color.white)
                        padding = dip(16)
                        textSize = 20f
                    }.lparams(matchParent, wrapContent)
                }
            }
        }
    }
}