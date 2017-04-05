package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewGroup
import com.zhuinden.navigator.ViewChangeHandler
import com.zhuinden.simplestack.StateChange
import io.github.gumil.testnavigator.utils.waitForMeasure

abstract class AnimatorChangeHandler : ViewChangeHandler {

    override fun performViewChange(container: ViewGroup,
                                   previousView: View,
                                   newView: View,
                                   direction: Int,
                                   completionCallback: ViewChangeHandler.CompletionCallback) {
        if (direction == StateChange.FORWARD) {
            container.addView(newView)
        } else {
            container.addView(newView, container.indexOfChild(previousView))
        }

        newView.waitForMeasure { view, width, height ->
            runAnimation(previousView, view, direction, object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    container.removeView(previousView)
                    completionCallback.onCompleted()
                }
            })
        }
    }

    // animation
    private fun runAnimation(previousView: View, newView: View, direction: Int, animatorListenerAdapter: AnimatorListenerAdapter) {
        val animator = createAnimator(previousView, newView, direction)
        animator.addListener(animatorListenerAdapter)
        animator.start()
    }

    abstract fun createAnimator(previousView: View, newView: View, direction: Int): Animator
}