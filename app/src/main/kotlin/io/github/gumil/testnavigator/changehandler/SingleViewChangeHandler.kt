package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import io.github.gumil.testnavigator.utils.waitForMeasure

abstract class SingleViewChangeHandler: ViewChangeHandler {

    override fun performViewChange(container: ViewGroup,
                                   previousView: View,
                                   newView: View,
                                   direction: Int,
                                   completionCallback: ViewChangeHandler.CompletionCallback) {

        val view = if (direction == StateChange.FORWARD) {
            newView.also {
                container.addView(newView)
            }
        } else {
            container.getChildAt(container.childCount - 1)
        }

        view.waitForMeasure { view, _, _ ->
            runAnimation(view, direction, object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (direction == -1) {
                        container.removeView(view)
                    }
                    completionCallback.onCompleted()
                }
            })
        }
    }

    // animation
    private fun runAnimation(view: View, direction: Int, animatorListenerAdapter: AnimatorListenerAdapter) {
        val animator = createAnimator(view, direction)
        animator.addListener(animatorListenerAdapter)
        animator.start()
    }

    abstract fun createAnimator(view: View, direction: Int): Animator
}