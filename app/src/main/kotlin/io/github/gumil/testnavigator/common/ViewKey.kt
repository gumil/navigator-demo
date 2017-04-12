package io.github.gumil.testnavigator.common

import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler

internal abstract class ViewKey : Parcelable {

    val layout by lazy {
        layout()
    }

    val viewChangeHandler by lazy {
        viewChangeHandler()
    }

    abstract protected fun layout(): ViewLayout

    abstract protected fun viewChangeHandler(): ViewChangeHandler

    open fun onChangeStarted() {}

    open fun onChangeEnded() {}

    override fun describeContents() = hashCode()
}