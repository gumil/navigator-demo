package io.github.gumil.testnavigator.home

import android.animation.AnimatorInflater
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.ImageView
import com.zhuinden.simplestack.navigator.Navigator
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.common.ViewLayout
import io.github.gumil.testnavigator.navigation.NavigationKey
import io.github.gumil.testnavigator.sharedelement.CityGridKey
import io.github.gumil.testnavigator.transition.TransitionKey
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

internal class HomeLayout : ViewLayout() {

    private val homeAdapter by lazy {
        HomeAdapter().apply {
            onRowClicked = onRowClicked()
        }
    }

        override fun createView(context: Context) = with(context) {
            toolbarTitle = context.getString(R.string.app_name)
            frameLayout {
                recyclerView {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(ctx)
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
                    HomeDemoModel.NAVIGATION -> {
                        backstack.goTo(NavigationKey())
                    }
                    HomeDemoModel.TRANSITIONS -> {
                        backstack.goTo(TransitionKey())
                    }
                    HomeDemoModel.SHARED_ELEMENT_TRANSITIONS -> {
                        backstack.goTo(CityGridKey(homeDemoModel, position))
                    }
                    HomeDemoModel.CHILD_CONTROLLERS -> {

                    }
                    HomeDemoModel.VIEW_PAGER -> {

                    }
                    HomeDemoModel.TARGET_CONTROLLER -> {

                    }
                    HomeDemoModel.MULTIPLE_CHILD_ROUTERS -> {

                    }
                    HomeDemoModel.MASTER_DETAIL -> {

                    }
                    HomeDemoModel.DRAG_DISMISS -> {

                    }
                    HomeDemoModel.RX_LIFECYCLE -> {

                    }
                    HomeDemoModel.RX_LIFECYCLE_2 -> {

                    }
                }
            }
        }

    }