package io.github.gumil.testnavigator.childcontroller

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class ChildKey(
        private val title: String,
        private val bgColor: Int,
        private val isColorRes: Boolean
): ViewKey() {

    var changeHandler: ViewChangeHandler = FadeChangeHandler()

    override fun layout() = ChildLayout(title, bgColor, isColorRes)

    override fun viewChangeHandler() = changeHandler

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