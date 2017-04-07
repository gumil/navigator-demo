package io.github.gumil.testnavigator.sharedelement.detail

import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

internal sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

internal class HeaderViewHolder(
        val header: CityRowHeader
) : ViewHolder(header.view) {

    fun bind(@DrawableRes imageDrawableRes: Int, title: String,
             imageTransitionName: String, textViewTransitionName: String) {
        header.bind(imageDrawableRes, title, imageTransitionName, textViewTransitionName)
    }
}

internal class DetailViewHolder(itemView: View) : ViewHolder(itemView) {

    fun bind(detail: String) {
        (itemView as? ViewGroup)?.let {
            (it.getChildAt(0) as? TextView)?.let {
                it.text = detail
            }
        }
    }
}