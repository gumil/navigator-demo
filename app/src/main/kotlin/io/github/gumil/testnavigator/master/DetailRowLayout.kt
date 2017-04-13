package io.github.gumil.testnavigator.master

import android.content.Context
import android.content.res.Configuration
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.getColorRes
import io.github.gumil.testnavigator.utils.isOrientation
import io.github.gumil.testnavigator.utils.textAppearance
import org.jetbrains.anko.*

internal class DetailRowLayout(
        val onRowSelected: (Int) -> Unit = {}
) : ViewLayout() {

    private lateinit var title: TextView
    private lateinit var root: ViewGroup

    override fun createView(context: Context) = with(context) {
        root = linearLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(24)

            title = textView {
                textAppearance(android.R.style.TextAppearance_Large)
                textSize = 20f
            }
        }
        root
    }

    fun bind(item: DetailItemModel, position: Int, selectedIndex: Int) {
        title.text = item.title
        root.onClick {
            onRowSelected(position)
        }

        if (view.context.isOrientation(Configuration.ORIENTATION_LANDSCAPE)
                && selectedIndex == position) {
            root.setBackgroundColor(root.context.getColorRes(R.color.grey_400))
        } else {
            root.setBackgroundColor(root.context.getColorRes(android.R.color.transparent))
        }

    }

}