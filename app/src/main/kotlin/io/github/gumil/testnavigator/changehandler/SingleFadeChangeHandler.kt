package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.zhuinden.simplestack.StateChange

class SingleFadeChangeHandler: SingleViewChangeHandler() {
    override fun createAnimator(view: View, direction: Int): Animator {
        val set = AnimatorSet()
        if (direction == StateChange.FORWARD) {
            set.play(ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f))
        } else {
            set.play(ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f))
        }
        return set

    }

}