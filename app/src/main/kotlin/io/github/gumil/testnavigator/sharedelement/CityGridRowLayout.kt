package io.github.gumil.testnavigator.sharedelement

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.textAppearance
import org.jetbrains.anko.*

internal class CityGridRowLayout : ViewLayout() {

    lateinit var imageView: ImageView
    lateinit var textView: TextView

    var onRowClicked: ((CityModel) -> Unit)? = null

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, dip(200))
            clipChildren = false
            clipToPadding = false
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(8)

            imageView = imageView().lparams(matchParent, 0) {
                weight = 1f
                bottomMargin = dip(16)
            }

            textView = textView {
                textAppearance(android.R.style.TextAppearance_Large)
            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }

    fun bind(item: CityModel) {
        imageView.setImageResource(item.drawableRes)
        textView.text = item.title

        imageView.transitionName = view.context.getString(R.string.transition_tag_title_named, item.title)
        textView.transitionName = view.context.getString(R.string.transition_tag_image_named, item.title)

        view.setOnClickListener {
            onRowClicked?.invoke(item)
        }
    }
}