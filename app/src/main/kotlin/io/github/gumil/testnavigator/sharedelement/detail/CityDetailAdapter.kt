package io.github.gumil.testnavigator.sharedelement.detail

import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.textView
import org.jetbrains.anko.wrapContent

internal class CityDetailAdapter(
        private val title: String,
        @DrawableRes private val imageDrawableRes: Int,
        private val imageViewTransitionName: String,
        private val textViewTransitionName: String
): RecyclerView.Adapter<ViewHolder>() {


    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_DETAIL = 1
    }

    private val LIST_ROWS = arrayOf(
            "• This is a city.",
            "• There's some cool stuff about it.",
            "• But really this is just a demo, not a city guide app.",
            "• This demo is meant to show some nice transitions, as long as you're on Lollipop or later.",
            "• You should have seen some sweet shared element transitions using the ImageView and the TextView in the \"header\" above.",
            "• This transition utilized some callbacks to ensure all the necessary rows in the RecyclerView were laid about before the transition occurred.",
            "• Just adding some more lines so it scrolls now...\n\n\n\n\n\n\nThe end."
    )

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_TYPE_HEADER
        } else {
            return VIEW_TYPE_DETAIL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == VIEW_TYPE_HEADER) {
            return HeaderViewHolder(CityRowHeader().apply { inflate(parent.context) })
        } else {
            return DetailViewHolder(with(parent.context) {
                frameLayout {
                    textView {
                        textSize = 20f
                    }.lparams(wrapContent, wrapContent) {
                        setPadding(dip(16), 0, dip(16), dip(16))
                    }
                }
            })
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder ->
                holder.bind(imageDrawableRes, title, imageViewTransitionName, textViewTransitionName)
            is DetailViewHolder ->
                holder.bind(LIST_ROWS[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return 1 + LIST_ROWS.size
    }

}