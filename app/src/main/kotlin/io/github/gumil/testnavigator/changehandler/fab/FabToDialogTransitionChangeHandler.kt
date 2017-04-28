package io.github.gumil.testnavigator.changehandler.fab

import android.support.v4.content.ContextCompat
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionSet
import android.view.View
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChange
import io.github.gumil.testnavigator.R
import io.github.gumil.testnavigator.changehandler.TransitionChangeHandler
import io.github.gumil.testnavigator.dialog.DialogLayout
import io.github.gumil.testnavigator.home.HomeLayout

class FabToDialogTransitionChangeHandler : TransitionChangeHandler() {

    private var fab: View? = null
    private var dialogBackground: View? = null
    private var fabParent: ViewGroup? = null

    override fun getTransition(container: ViewGroup, previousView: View, newView: View, direction: Int): Transition {
        val backgroundFade = Fade()
        backgroundFade.addTarget(DialogLayout.DIALOG_BACKGROUND_ID)

        val fabTransform = FabTransform(ContextCompat.getColor(container.context, R.color.colorAccent), R.drawable.ic_github_face)

        val set = TransitionSet()
        set.addTransition(backgroundFade)
        set.addTransition(fabTransform)

        return set
    }

    override fun prepareForTransition(container: ViewGroup, previousView: View, newView: View, transition: Transition, direction: Int, onTransitionPreparedListener: () -> Unit) {
        fab = if (direction == StateChange.FORWARD) previousView.findViewById(HomeLayout.FAB_ID)
        else newView.findViewById(HomeLayout.FAB_ID)
        fabParent = fab?.parent as ViewGroup

        if (direction == StateChange.BACKWARD) {
            /*
             * Before we transition back we want to remove the fab
             * in order to add it again for the TransitionManager to be able to detect the change
             */
            fabParent?.removeView(fab)
            fab?.visibility = View.VISIBLE

            /*
             * Before we transition back we need to move the dialog's background to the new view
             * so its fade won't take place over the fab transition
             */
            dialogBackground = previousView.findViewById(DialogLayout.DIALOG_BACKGROUND_ID)
            (dialogBackground?.parent as ViewGroup?)?.removeView(dialogBackground)
            fabParent?.addView(dialogBackground)
        }

        onTransitionPreparedListener()
    }

    override fun executePropertyChanges(container: ViewGroup, previousView: View, newView: View,
                                        transition: Transition, direction: Int) {
        if (direction == StateChange.FORWARD) {
            fabParent?.removeView(fab)
            container.addView(newView)

            /*
             * After the transition is finished we have to add the fab back to the original container.
             * Because otherwise we will be lost when trying to transition back.
             * Set it to invisible because we don't want it to jump back after the transition
             */
            transition.addListener(object : TransitionEndListener() {
                override fun onTransitionCompleted(transition: Transition) {
                    fab?.visibility = View.GONE
                    fabParent?.addView(fab)
                    fab = null
                    fabParent = null
                }
            })
        } else {
            dialogBackground?.visibility = View.INVISIBLE
            fabParent?.addView(fab)
            container.removeView(previousView)

            transition.addListener(object : TransitionEndListener() {
                override fun onTransitionCompleted(transition: Transition) {
                    fabParent?.removeView(dialogBackground)
                    fabParent?.findViewById(HomeLayout.FAB_ID)?.visibility = View.VISIBLE
                    dialogBackground = null
                }
            })
        }
    }

}