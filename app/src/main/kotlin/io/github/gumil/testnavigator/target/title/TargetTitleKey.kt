package io.github.gumil.testnavigator.target.title

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal class TargetTitleKey(
        private val onTitlePicked: (String) -> Unit = {}
) : ViewKey() {

    override fun layout() = TargetTitleLayout(onTitlePicked)

    override fun viewChangeHandler() = SegueViewChangeHandler()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TargetTitleKey> = object : Parcelable.Creator<TargetTitleKey> {
            override fun createFromParcel(`in`: Parcel) = TargetTitleKey()

            override fun newArray(size: Int) = arrayOfNulls<TargetTitleKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) { }

}