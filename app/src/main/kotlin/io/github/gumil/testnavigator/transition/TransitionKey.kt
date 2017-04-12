package io.github.gumil.testnavigator.transition

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.changehandler.VerticalChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal data class TransitionKey(
        private val transitionDemo: TransitionDemo = TransitionDemo.VERTICAL
) : ViewKey() {

    private var changeHandler: ViewChangeHandler = VerticalChangeHandler()

    constructor(transitionDemo: TransitionDemo,
                changeHandler: ViewChangeHandler) : this(transitionDemo) {
        this.changeHandler = changeHandler
    }

    override fun layout() = TransitionLayout(transitionDemo.colorId,
            transitionDemo.title, transitionDemo.ordinal)

    override fun viewChangeHandler(): ViewChangeHandler {
        return changeHandler
    }

    constructor(parcel: Parcel) : this(
            TransitionDemo.fromIndex(parcel.readInt())
    )

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TransitionKey> = object : Parcelable.Creator<TransitionKey> {
            override fun createFromParcel(`in`: Parcel) = TransitionKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<TransitionKey>(size)
        }

    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(transitionDemo.ordinal)
    }
}