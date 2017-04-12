package io.github.gumil.testnavigator.target.title

import android.content.Context
import android.view.Gravity
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import org.jetbrains.anko.*

internal class TargetTitleLayout(
        val onTitlePicked: (String) -> Unit = {}
) : ViewLayout() {

    override fun createView(context: Context) = with(context) {
        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(24)

            val editText = editText {
                hint = getString(R.string.enter_title)
            }.lparams(matchParent, wrapContent)

            button {
                text = getString(R.string.use_title)

                onClick {
                    onTitlePicked(editText.text.toString())
                    Navigator.getBackstack(ctx).goBack()
                }
            }.lparams(matchParent, wrapContent)
        }
    }

}