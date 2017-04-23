package io.github.gumil.testnavigator.navigation

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.multiple.StateChangerStack

internal data class NavigationKey(
        private val index: Int = 0,
        private val displayUpMode: DisplayUpMode = DisplayUpMode.SHOW_FOR_CHILDREN_ONLY,
        private val tag: String = ""
) : ViewKey() {

    var changeHandler: ViewChangeHandler = FadeChangeHandler()

    override fun layout() = NavigationLayout(index, displayUpMode)

    override fun viewChangeHandler() = changeHandler

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            DisplayUpMode.values()[parcel.readInt()]
    )

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NavigationKey> = object : Parcelable.Creator<NavigationKey> {
            override fun createFromParcel(`in`: Parcel) = NavigationKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<NavigationKey>(size)
        }
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

    override fun onBackPressed(): Boolean {
        if (StateChangerStack.isEmpty()) {
            return false
        }

        (layout as? NavigationLayout)?.onBackPressed()
        return true
    }
}