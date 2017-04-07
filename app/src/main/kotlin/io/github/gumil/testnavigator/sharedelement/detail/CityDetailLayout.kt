package io.github.gumil.testnavigator.sharedelement.detail

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v7.widget.LinearLayoutManager
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import org.jetbrains.anko.ctx
import org.jetbrains.anko.recyclerview.v7.recyclerView

internal class CityDetailLayout(
        @DrawableRes private val imageDrawableRes: Int,
        private val title: String
) : ViewLayout() {

    override fun createView(context: Context) = with(context) {
        recyclerView {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(ctx)

            adapter = CityDetailAdapter(title, imageDrawableRes,
                    getString(R.string.transition_tag_image_named, title),
                    getString(R.string.transition_tag_title_named, title))
        }
    }

}