package io.github.gumil.testnavigator.childcontroller.child

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class ChildKey(
        private val title: String,
        private val bgColor: Int,
        private val isColorRes: Boolean
): ViewKey {

    private val layout by lazy {
        ChildLayout(title, bgColor, isColorRes)
    }

    override fun layout() = layout

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt() == 1
    )

    @JvmField
    val CREATOR: Parcelable.Creator<ChildKey> = object : Parcelable.Creator<ChildKey> {
        override fun createFromParcel(`in`: Parcel): ChildKey {
            return ChildKey(`in`)
        }

        override fun newArray(size: Int): Array<ChildKey?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeInt(bgColor)
        dest.writeInt(if (isColorRes) 1 else 0)
    }

}