package io.github.gumil.testnavigator.multiple

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.common.ViewLayout

internal class MultipleKey(): ViewKey() {
    override fun layout(): ViewLayout {
        throw UnsupportedOperationException("not implemented")
    }

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MultipleKey> = object : Parcelable.Creator<MultipleKey> {
            override fun createFromParcel(`in`: Parcel) = MultipleKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<MultipleKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

}