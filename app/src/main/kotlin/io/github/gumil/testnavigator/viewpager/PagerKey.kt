package io.github.gumil.testnavigator.viewpager

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class PagerKey : ViewKey() {
    override fun layout() = PagerLayout()

    override fun viewChangeHandler() = FadeChangeHandler()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PagerKey> = object : Parcelable.Creator<PagerKey> {
            override fun createFromParcel(`in`: Parcel) = PagerKey()

            override fun newArray(size: Int) = arrayOfNulls<PagerKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
    }
}