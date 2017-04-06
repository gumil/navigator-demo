package io.github.gumil.testnavigator.transition

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.changehandler.VerticalChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal data class TransitionKey(
        private val transitionDemo: TransitionDemo = TransitionDemo.VERTICAL
) : ViewKey {

    private var changeHandler: ViewChangeHandler = VerticalChangeHandler()

    constructor(transitionDemo: TransitionDemo,
                changeHandler: ViewChangeHandler): this(transitionDemo) {
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

    @JvmField
    val CREATOR: Parcelable.Creator<TransitionKey> = object : Parcelable.Creator<TransitionKey> {
        override fun createFromParcel(`in`: Parcel): TransitionKey {
            return TransitionKey(`in`)
        }

        override fun newArray(size: Int): Array<TransitionKey?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(transitionDemo.ordinal)
    }
}