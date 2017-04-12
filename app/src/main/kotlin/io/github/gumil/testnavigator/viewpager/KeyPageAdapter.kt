package io.github.gumil.testnavigator.viewpager

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import io.github.gumil.testnavigator.childcontroller.child.ChildKey

internal class KeyPageAdapter(
        private val data: List<ChildKey>
): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val viewKey = data[position]
        container.addView(viewKey.layout.inflate(container.context))
        return viewKey
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (`object` as? ChildKey)?.let {
            try {
                container.removeView(it.layout.view)
            } catch (e: UninitializedPropertyAccessException) {
                e.printStackTrace()
            }
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (`object` as? ChildKey)?.let {
            try {
                it.layout.view == view
            } catch (e: UninitializedPropertyAccessException) {
                false
            }
        } ?: false
    }

    override fun getCount() = data.size

    override fun getPageTitle(position: Int): CharSequence {
        return "Page $position"
    }
}