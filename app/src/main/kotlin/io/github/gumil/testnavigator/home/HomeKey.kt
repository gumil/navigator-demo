package io.github.gumil.testnavigator.home

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.zhuinden.simplestack.navigator.changehandlers.NoOpViewChangeHandler
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewKey

internal data class HomeKey(
        private val TAG: String = HomeKey::javaClass.name
) : ViewKey() {

    private var state: Parcelable? = null

    override fun layout() = HomeLayout()

    override fun viewChangeHandler() = NoOpViewChangeHandler()

    constructor(parcel: Parcel) : this() {
        parcel.readParcelable<RecyclerView.SavedState>(RecyclerView.SavedState::class.java.classLoader)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HomeKey> = object : Parcelable.Creator<HomeKey> {
            override fun createFromParcel(`in`: Parcel) =  HomeKey(`in`)

            override fun newArray(size: Int) = arrayOfNulls<HomeKey>(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(state, flags)
    }

    override fun onViewRemoved() {
        super.onViewRemoved()

        (layout as? HomeLayout)?.let {
            state = it.getLayoutManagerState()
        }
    }

    override fun onChangeStarted() {
        super.onChangeStarted()
        (layout as? HomeLayout)?.let { home ->
            state?.let { home.restoreLayoutManager(it) }
        }
    }

    override fun onCreateOptionsMenu(): Int {
        return R.menu.home
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (layout as? HomeLayout)?.let(HomeLayout::onClickFab)
        return true
    }
}