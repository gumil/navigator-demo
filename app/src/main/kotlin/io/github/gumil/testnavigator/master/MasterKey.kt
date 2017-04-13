package io.github.gumil.testnavigator.master

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class MasterKey() : ViewKey() {
    override fun layout() = MasterLayout()

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MasterKey> = object : Parcelable.Creator<MasterKey> {
            override fun createFromParcel(`in`: Parcel) =  MasterKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<MasterKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    override fun onChangeStarted() {
        super.onChangeStarted()
        (layout as? MasterLayout)?.let(MasterLayout::initializeView)
    }
}