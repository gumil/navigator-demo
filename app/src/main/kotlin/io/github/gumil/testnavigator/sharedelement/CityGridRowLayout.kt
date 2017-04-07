package io.github.gumil.testnavigator.sharedelement

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import org.jetbrains.anko.*

internal class CityGridRowLayout : ViewLayout() {

    lateinit var image: ImageView
    lateinit var title: TextView

    var onRowClicked: ((CityModel) -> Unit)? = null

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, dip(200))
            clipChildren = false
            clipToPadding = false
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(8)

            image = imageView().lparams(matchParent, 0) {
                weight = 1f
                bottomMargin = dip(16)
            }

            title = textView {
                setTextAppearance(ctx, android.R.style.TextAppearance_Large)
            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }

    fun bind(item: CityModel) {
        image.setImageResource(item.drawableRes)
        title.text = item.title

        image.transitionName = view.context.getString(R.string.transition_tag_title_named, item.title)
        title.transitionName = view.context.getString(R.string.transition_tag_image_named, item.title)

        view.setOnClickListener {
            onRowClicked?.invoke(item)
        }
    }
}