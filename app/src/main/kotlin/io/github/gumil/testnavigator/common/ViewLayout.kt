package io.github.gumil.testnavigator.common

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.github.gumil.testnavigator.utils.findActivity

internal abstract class ViewLayout {

    lateinit var view: View
    var toolbarTitle = ""

    fun inflate(context: Context): View {
        view = createView(context)
        if (toolbarTitle.isNotBlank()) {
            setActionBarTitle(toolbarTitle)
        }
        return view
    }

    private fun setActionBarTitle(title: String) {
        val activity = view.context.findActivity()

        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.title = title
        }
    }

    protected abstract fun createView(context: Context): View
}