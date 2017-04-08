package io.github.gumil.testnavigator.navigation

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.common.ViewLayout

internal data class NavigationKey(
        private val index: Int = 0,
        private val displayUpMode: DisplayUpMode = DisplayUpMode.SHOW_FOR_CHILDREN_ONLY
) : ViewKey() {

    var changeHandler: ViewChangeHandler = FadeChangeHandler()

    override fun layout() = NavigationLayout(index, displayUpMode)

    override fun viewChangeHandler() = changeHandler

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            DisplayUpMode.values()[parcel.readInt()]
    )

    @JvmField
    val CREATOR: Parcelable.Creator<NavigationKey> = object : Parcelable.Creator<NavigationKey> {
        override fun createFromParcel(`in`: Parcel): NavigationKey {
            return NavigationKey(`in`)
        }

        override fun newArray(size: Int): Array<NavigationKey?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(index)
        dest.writeInt(displayUpMode.ordinal)
    }

    override fun onChangeStarted() {
        (layout as? NavigationLayout)?.setButtonsEnabled(false)
    }

    override fun onChangeEnded() {
        (layout as? NavigationLayout)?.setButtonsEnabled(true)
    }
}