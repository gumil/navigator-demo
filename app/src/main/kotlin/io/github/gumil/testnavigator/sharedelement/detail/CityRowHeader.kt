package io.github.gumil.testnavigator.sharedelement.detail

import android.content.Context
import android.support.annotation.DrawableRes
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.textAppearance
import org.jetbrains.anko.*

internal class CityRowHeader : ViewLayout() {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
            setPadding(0, 0, 0, dip(16))

            imageView = imageView {
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
            }.lparams(matchParent, wrapContent)

            textView = textView {
                textAppearance(android.R.style.TextAppearance_Large)
            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.CENTER
            }
        }
    }

    fun bind(@DrawableRes imageDrawableRes: Int, title: String,
             imageTransitionName: String, textViewTransitionName: String) {
        imageView.setImageResource(imageDrawableRes)
        textView.text = title

        imageView.transitionName = imageTransitionName
        textView.transitionName = textViewTransitionName
    }
}