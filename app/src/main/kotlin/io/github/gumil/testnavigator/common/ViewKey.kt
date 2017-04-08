package io.github.gumil.testnavigator.common

import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler

internal interface ViewKey: Parcelable {

    fun layout(): ViewLayout

    fun viewChangeHandler(): ViewChangeHandler

    fun onChangeStarted() {}

    fun onChangeEnded() {}
}