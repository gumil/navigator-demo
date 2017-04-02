package io.github.gumil.testnavigator.navigation

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler

internal data class NavigationKey(
        val index: Int = 0,
        val displayUpMode: DisplayUpMode = DisplayUpMode.SHOW_FOR_CHILDREN_ONLY
) : ViewKey {

    var viewChangeHandler: ViewChangeHandler = FadeChangeHandler()

    private val navigationLayout by lazy {
        NavigationLayout(index, displayUpMode)
    }

    override fun layout(): ViewLayout {
        return navigationLayout
    }

    override fun viewChangeHandler(): ViewChangeHandler {
        return viewChangeHandler
    }

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
        navigationLayout.setButtonsEnabled(false)
    }

    override fun onChangeEnded() {
        navigationLayout.setButtonsEnabled(true)
    }
}