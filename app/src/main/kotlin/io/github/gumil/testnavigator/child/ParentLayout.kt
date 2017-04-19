package io.github.gumil.testnavigator.child

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.utils.getColorRes
import io.github.gumil.testnavigator.utils.getMaterialColor
import org.jetbrains.anko.*

internal class ParentLayout : ViewLayout() {

    val children = mutableListOf<ViewGroup>()

    private var index = 0

    override fun createView(context: Context) = with(context) {
        toolbarTitle = "Parent/Child Demo"
        verticalLayout {
            backgroundColor = getColorRes(R.color.orange_300)
            setPadding(dip(24), 0, dip(24), 0)


            textView(getString(R.string.parent_controller)) {
                textSize = 28f
                padding = dip(16)

                onClick {
                    for (i in 0..4) {
                        Navigator.getBackstack(context)
                                .goTo(ChildKey("Child Controller 0",
                                        context.getMaterialColor(0), false))
                    }
                }

            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.CENTER_HORIZONTAL
            }

            for (i in 0..4) {
                frameLayout {
                    view { }.lparams(matchParent, matchParent)
                }.lparams(matchParent, 0) {
                    weight = 1f
                }.let { children.add(it) }
            }
        }
    }
}