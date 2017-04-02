package io.github.gumil.testnavigator.common

import android.os.Parcelable
import com.zhuinden.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.common.ViewLayout

internal interface ViewKey: Parcelable {

    fun layout(): ViewLayout

    fun viewChangeHandler(): ViewChangeHandler

    fun onChangeStarted() {}

    fun onChangeEnded() {}
}