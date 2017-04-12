package io.github.gumil.testnavigator.home

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.changehandlers.NoOpViewChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal data class HomeKey(
        private val tag: String = HomeKey::javaClass.name
) : ViewKey() {

    override fun layout() = HomeLayout()

    override fun viewChangeHandler() = NoOpViewChangeHandler()

    constructor(parcel: Parcel) : this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HomeKey> = object : Parcelable.Creator<HomeKey> {
            override fun createFromParcel(`in`: Parcel) =  HomeKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<HomeKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}
}