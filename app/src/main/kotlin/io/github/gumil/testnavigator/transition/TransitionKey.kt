package io.github.gumil.testnavigator.transition

import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import io.github.gumil.testnavigator.changehandler.ArcFadeMoveChangeHandler
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.changehandler.FlipChangeHandler
import io.github.gumil.testnavigator.changehandler.VerticalChangeHandler
import io.github.gumil.testnavigator.common.ViewKey

internal data class TransitionKey(
        private val transitionDemo: TransitionDemo = TransitionDemo.VERTICAL
) : ViewKey() {

    var changeHandler: ViewChangeHandler = FadeChangeHandler()

    override fun layout() = TransitionLayout(transitionDemo.colorId,
            transitionDemo.title, transitionDemo.ordinal)

    override fun viewChangeHandler(): ViewChangeHandler {
        return when (transitionDemo) {
            TransitionDemo.VERTICAL -> VerticalChangeHandler()
            TransitionDemo.CIRCULAR -> changeHandler
            TransitionDemo.FADE -> FadeChangeHandler()
            TransitionDemo.FLIP -> FlipChangeHandler()
            TransitionDemo.HORIZONTAL -> SegueViewChangeHandler()
            TransitionDemo.ARC_FADE -> ArcFadeMoveChangeHandler()
            TransitionDemo.ARC_FADE_RESET -> ArcFadeMoveChangeHandler()
        }
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

    override fun onBackPressed(): Boolean {
        if (transitionDemo == TransitionDemo.CIRCULAR) {
            (layout as? TransitionLayout)?.getCircularChangeHandler()?.let {
                changeHandler = it
            }
        }
        return super.onBackPressed()
    }
}