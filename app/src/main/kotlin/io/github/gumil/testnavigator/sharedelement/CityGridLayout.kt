package io.github.gumil.testnavigator.sharedelement

import android.content.Context
import android.graphics.PorterDuff
import android.support.annotation.ColorRes
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import com.zhuinden.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.sharedelement.detail.CityDetailKey
import io.github.gumil.testnavigator.utils.getColorRes
import io.github.gumil.testnavigator.utils.textAppearance
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import java.util.ArrayList

internal class CityGridLayout(
        private val title: String,
        @ColorRes private val dotColor: Int,
        private val fromPosition: Int
) : ViewLayout() {

    private val cityGridAdapter by lazy {
        CityGridAdapter().apply {
            onRowClicked = onRowClicked()
        }
    }

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            val normalPadding = resources.getDimensionPixelSize(R.dimen.padding_normal)
            setPadding(normalPadding, normalPadding, normalPadding, 0)
            gravity = Gravity.CENTER_HORIZONTAL

            imageView {
                val drawable = getDrawable(R.drawable.circle)
                drawable.setColorFilter(ctx.getColorRes(dotColor),
                        PorterDuff.Mode.SRC_ATOP)
                setImageDrawable(drawable)
                transitionName = getString(R.string.transition_tag_dot_indexed, fromPosition)
            }.lparams(dip(96), dip(96))

            textView(title) {
                textAppearance(android.R.style.TextAppearance_Large)
                transitionName = getString(R.string.transition_tag_title_indexed, fromPosition)
            }.lparams(wrapContent, wrapContent) {
                topMargin = dip(40)
            }

            recyclerView {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(ctx, 2)
                adapter = cityGridAdapter
            }.lparams(matchParent, matchParent) {
                topMargin = dip(16)
            }
        }
    }

    private fun onRowClicked(): (CityModel) -> Unit {
        return {
            val imageTransitionName = view.context.getString(R.string.transition_tag_image_named, it.title)
            val titleTransitionName = view.context.getString(R.string.transition_tag_title_named, it.title)

            val names = ArrayList<String>()
            names.add(imageTransitionName)
            names.add(titleTransitionName)

            Navigator.getBackstack(view.context).goTo(CityDetailKey(it.drawableRes, it.title, names))

        }
    }
}