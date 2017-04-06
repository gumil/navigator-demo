package io.github.gumil.testnavigator.home

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.navigator.ViewChangeHandler
import com.zhuinden.navigator.changehandlers.NoOpViewChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal data class HomeKey(
        private val tag: String = HomeKey::javaClass.name
) : ViewKey {

    override fun layout() = HomeLayout()

    override fun viewChangeHandler(): ViewChangeHandler {
        return NoOpViewChangeHandler()
    }

    constructor(parcel: Parcel): this()

    @JvmField
    val CREATOR: Parcelable.Creator<HomeKey> = object : Parcelable.Creator<HomeKey> {
        override fun createFromParcel(`in`: Parcel): HomeKey {
            return HomeKey(`in`)
        }

        override fun newArray(size: Int): Array<HomeKey?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}
}