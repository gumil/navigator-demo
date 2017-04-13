package io.github.gumil.testnavigator.dragdismiss

import android.os.Parcel
import android.os.Parcelable
import io.github.gumil.testnavigator.changehandler.ScaleFadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class DragDismissKey() : ViewKey() {

    override fun layout() = DragDismissLayout()

    override fun viewChangeHandler() = ScaleFadeChangeHandler()

    constructor(parcel: Parcel): this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DragDismissKey> = object : Parcelable.Creator<DragDismissKey> {
            override fun createFromParcel(`in`: Parcel) =  DragDismissKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<DragDismissKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

}