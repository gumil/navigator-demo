package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.zhuinden.simplestack.StateChange

class ScaleFadeChangeHandler : SingleViewChangeHandler() {
    override fun createAnimator(view: View, direction: Int): Animator {
        val animator = AnimatorSet()
        animator.play(ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f))

        if (direction == StateChange.BACKWARD) {
            animator.play(ObjectAnimator.ofFloat(view, View.ALPHA, view.alpha, 1f))
            animator.play(ObjectAnimator.ofFloat(view, View.ALPHA, 0f))
            animator.play(ObjectAnimator.ofFloat(view, View.SCALE_X, 0.8f))
            animator.play(ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.8f))
        } else {
            animator.play(ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f))
        }

        return animator
    }

}