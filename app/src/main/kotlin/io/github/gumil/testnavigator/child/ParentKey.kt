package io.github.gumil.testnavigator.child

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.MainActivity
import io.github.gumil.testnavigator.changehandler.FadeChangeHandler
import io.github.gumil.testnavigator.common.ViewKey
import io.github.gumil.testnavigator.home.HomeKey
import io.github.gumil.testnavigator.utils.getMaterialColor

internal data class ParentKey(
        val tag: String = ParentKey::javaClass.name
) : ViewKey() {

    private var stateChanger: StateChanger? = null

    private var index = 0

    override fun layout() = ParentLayout()

    override fun viewChangeHandler() = FadeChangeHandler()

    constructor(parcel: Parcel) : this()

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ParentKey> = object : Parcelable.Creator<ParentKey> {
            override fun createFromParcel(`in`: Parcel) = ParentKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<ParentKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    override fun onChangeEnded() {
        super.onChangeEnded()
        (layout as? ParentLayout)?.let {
            val context = layout.view.context
            stateChanger = ChildStateChanger(
                    context,
                    { addChild(context, it) },
                    it.children
            )

            stateChanger?.let {
                MainActivity.instance?.setChildStateChanger(it)
                addChild(context, StateChange.FORWARD)
            }
        }

    }

    private fun addChild(context: Context, direction: Int) {
        (layout as? ParentLayout)?.let {
            val backstack = Navigator.getBackstack(context)
            if (index < it.children.size && direction == StateChange.FORWARD) {
                backstack.goTo(ChildKey("Child Controller $index",
                        context.getMaterialColor(index++), false))
            } else if (--index >= 0 && direction == StateChange.BACKWARD) {
                backstack.goTo(ChildKey("Child Controller $index",
                        context.getMaterialColor(index), false))
            }

            if (index == -1 && direction == StateChange.BACKWARD){
                MainActivity.instance?.removeChildStateChanger()
                backstack.goTo(HomeKey())
            }
        }
    }
}