package io.github.gumil.testnavigator.master

import android.content.Context
import android.content.res.Configuration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.LinearLayout
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.childcontroller.ChildKey
import io.github.gumil.testnavigator.childcontroller.ChildLayout
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.getColorRes
import io.github.gumil.testnavigator.utils.isOrientation
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

internal class MasterLayout : ViewLayout() {

    private var container: ViewGroup? = null
    private lateinit var recyclerView: RecyclerView

    override fun createView(context: Context) = with(context) {
        if (isOrientation(Configuration.ORIENTATION_PORTRAIT))
            getPortraitLayout() else getLandscapeLayout()
    }

    private fun Context.getPortraitLayout(): LinearLayout {
        return verticalLayout {
            textView(getString(R.string.rotate_for_two_pane)) {
                padding = dip(24)
                backgroundColor = getColorRes(R.color.grey_300)
                textSize = 20f
            }.lparams(matchParent, wrapContent)

            recyclerView = recyclerView().initializeView().lparams(matchParent, matchParent)
        }
    }

    private fun Context.getLandscapeLayout(): LinearLayout {
        return linearLayout {

            recyclerView = recyclerView {
                backgroundColor = getColorRes(R.color.grey_300)
            }.initializeView().lparams(dip(200), matchParent)

            container = frameLayout().lparams(matchParent, matchParent)
        }
    }

    private fun RecyclerView.initializeView() = apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = DetailItemAdapter(onRowSelected())
    }

    private fun RecyclerView.onRowSelected(): (Int) -> Unit {
        return {
            val model = DetailItemModel.values()[it]

            if (context.isOrientation(Configuration.ORIENTATION_PORTRAIT)) {
                Navigator.getBackstack(context).goTo(
                        ChildKey(model.detail, model.backgroundColor, true).apply {
                            changeHandler = SegueViewChangeHandler()
                        }
                )
            } else container?.let {
                val child = ChildLayout(model.detail, model.backgroundColor, true).inflate(context)
                it.addView(child)
            }

            adapter.notifyDataSetChanged()
        }
    }

    fun initializeView() {
        if (view.context.isOrientation(Configuration.ORIENTATION_LANDSCAPE)) {
            recyclerView.onRowSelected().invoke(0)
        }
    }
}