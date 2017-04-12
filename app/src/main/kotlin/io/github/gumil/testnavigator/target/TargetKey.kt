package io.github.gumil.testnavigator.target

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class TargetKey() : ViewKey() {

    override fun layout() = TargetLayout()

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TargetKey> = object : Parcelable.Creator<TargetKey> {
            override fun createFromParcel(`in`: Parcel) = TargetKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<TargetKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) { }
}