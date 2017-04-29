package io.github.gumil.testnavigator.sharedelement.detail

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.DrawableRes
import io.github.gumil.testnavigator.changehandler.SharedElementDelayingChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class CityDetailKey(
        @DrawableRes private val imageDrawableRes: Int,
        private val title: String,
        private val names: MutableList<String>
) : ViewKey() {

    override fun layout() = CityDetailLayout(imageDrawableRes, title)

    override fun viewChangeHandler() = SharedElementDelayingChangeHandler(names)

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            mutableListOf<String>()
    )

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CityDetailKey> = object : Parcelable.Creator<CityDetailKey> {
            override fun createFromParcel(`in`: Parcel) = CityDetailKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<CityDetailKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(imageDrawableRes)
        dest.writeString(title)
    }

}