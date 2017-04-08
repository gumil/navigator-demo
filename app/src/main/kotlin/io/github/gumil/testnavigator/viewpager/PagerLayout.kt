package io.github.gumil.testnavigator.viewpager

import android.content.Context
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.childcontroller.child.ChildKey
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.getColorRes
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

internal class PagerLayout : ViewLayout() {

    private val PAGE_COLORS = intArrayOf(
            R.color.green_300,
            R.color.cyan_300,
            R.color.deep_purple_300,
            R.color.lime_300,
            R.color.red_300
    )

    private val childKeys = listOf(
            ChildKey(getTitle(0), PAGE_COLORS[0], true),
            ChildKey(getTitle(1), PAGE_COLORS[1], true),
            ChildKey(getTitle(2), PAGE_COLORS[2], true),
            ChildKey(getTitle(3), PAGE_COLORS[3], true),
            ChildKey(getTitle(4), PAGE_COLORS[4], true)
    )

    override fun createView(context: Context) = with(context) {
        toolbarTitle = "ViewPager Demo"
        verticalLayout {
            val tablayout = tabLayout(android.R.style.ThemeOverlay_Material_Dark_ActionBar) {
                backgroundColor = getColorRes(R.color.colorPrimary)
                elevation = dip(6).toFloat()
                minimumHeight = dip(56)
            }.lparams(matchParent, wrapContent)

            viewPager{
                backgroundColor = 0xff00ff
                adapter = KeyPageAdapter(childKeys)

                tablayout.setupWithViewPager(this)
            }.lparams(matchParent, matchParent)
        }
    }

    private fun getTitle(position: Int): String {
        return "Child $position (Swipe to see more)"
    }

}