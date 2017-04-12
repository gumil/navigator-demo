package io.github.gumil.testnavigator.childcontroller.child

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class ChildKey(
        private val title: String,
        private val bgColor: Int,
        private val isColorRes: Boolean
): ViewKey() {

    override fun layout() = ChildLayout(title, bgColor, isColorRes)

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt() == 1
    )

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ChildKey> = object : Parcelable.Creator<ChildKey> {
            override fun createFromParcel(`in`: Parcel) =  ChildKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<ChildKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeInt(bgColor)
        dest.writeInt(if (isColorRes) 1 else 0)
    }

}