package io.github.gumil.testnavigator.home

import android.animation.AnimatorInflater
import android.content.Context
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.ImageView
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.child.ParentKey
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.dragdismiss.DragDismissKey
import io.github.gumil.testnavigator.master.MasterKey
import io.github.gumil.testnavigator.navigation.NavigationKey
import io.github.gumil.testnavigator.sharedelement.CityGridKey
import io.github.gumil.testnavigator.target.TargetKey
import io.github.gumil.testnavigator.transition.TransitionKey
import io.github.gumil.testnavigator.viewpager.PagerKey
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

internal class HomeLayout : ViewLayout() {

    private val homeAdapter by lazy {
        HomeAdapter().apply {
            onRowClicked = onRowClicked()
        }
    }

    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    override fun createView(context: Context) = with(context) {
        toolbarTitle = context.getString(R.string.app_name)
        frameLayout {
            recyclerView {
                setHasFixedSize(true)
                recyclerLayoutManager = LinearLayoutManager(ctx)
                layoutManager = recyclerLayoutManager
                adapter = homeAdapter
            }.lparams(matchParent, matchParent)

            floatingActionButton {
                elevation = resources.getDimensionPixelSize(R.dimen.z_fab).toFloat()
                scaleType = ImageView.ScaleType.MATRIX
                stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.raise)
                transitionName = getString(R.string.fab_dialog_transition_name)
                imageResource = R.drawable.ic_github_face
            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.BOTTOM or Gravity.END
                bottomMargin = resources.getDimensionPixelSize(R.dimen.padding_normal)
                rightMargin = resources.getDimensionPixelSize(R.dimen.padding_normal)
                marginEnd = resources.getDimensionPixelSize(R.dimen.padding_normal)
            }
        }
    }

    private fun onRowClicked(): (HomeDemoModel, Int) -> Unit {
        return { homeDemoModel, position ->
            val backstack = Navigator.getBackstack(view.context)
            when (homeDemoModel) {
                HomeDemoModel.NAVIGATION -> backstack.goTo(NavigationKey())
                HomeDemoModel.TRANSITIONS -> backstack.goTo(TransitionKey())
                HomeDemoModel.SHARED_ELEMENT_TRANSITIONS -> backstack.goTo(CityGridKey(homeDemoModel, position))
                HomeDemoModel.CHILD_CONTROLLERS -> backstack.goTo(ParentKey())
                HomeDemoModel.VIEW_PAGER -> backstack.goTo(PagerKey())
                HomeDemoModel.TARGET_CONTROLLER -> backstack.goTo(TargetKey())
//                HomeDemoModel.MULTIPLE_CHILD_ROUTERS -> backstack.goTo(MultipleKey())
                HomeDemoModel.MASTER_DETAIL -> backstack.goTo(MasterKey())
                HomeDemoModel.DRAG_DISMISS ->  backstack.goTo(DragDismissKey())
//                HomeDemoModel.RX_LIFECYCLE -> { }
//                HomeDemoModel.RX_LIFECYCLE_2 -> { }
            }
        }
    }

    fun restoreLayoutManager(parcelable: Parcelable) {
        recyclerLayoutManager.onRestoreInstanceState(parcelable)
    }

    fun getLayoutManagerState() = recyclerLayoutManager.onSaveInstanceState()
}