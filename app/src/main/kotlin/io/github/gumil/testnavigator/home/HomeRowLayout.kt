package io.github.gumil.testnavigator.home

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.utils.getSelectableItemBackground
import org.jetbrains.anko.*


internal class HomeRowLayout : ViewLayout() {

    lateinit var imageDot: ImageView
    lateinit var title: TextView

    var onRowClicked: ((HomeDemoModel, Int) -> Unit)? = null

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
            clipChildren = false
            clipToPadding = false
            gravity = Gravity.CENTER_VERTICAL
            orientation = LinearLayout.HORIZONTAL
            padding = dip(24)

            imageDot = imageView {
                imageResource = R.drawable.circle
            }.lparams(dip(48), dip(48)) {
                rightMargin = dip(16)
            }

            title = textView {
                setTextAppearance(ctx, android.R.style.TextAppearance_Large)
            }

            background = getSelectableItemBackground()
        }
    }

    fun bind(position: Int, homeDemoModel: HomeDemoModel) {
        val context = view.context
        val color = ContextCompat.getColor(context, homeDemoModel.color)
        imageDot.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        imageDot.transitionName = context.getString(R.string.transition_tag_dot_indexed, position)
        title.text = homeDemoModel.title
        title.transitionName = context.getString(R.string.transition_tag_title_indexed, position)

        view.setOnClickListener {
            onRowClicked?.invoke(homeDemoModel, position)
        }
    }
}