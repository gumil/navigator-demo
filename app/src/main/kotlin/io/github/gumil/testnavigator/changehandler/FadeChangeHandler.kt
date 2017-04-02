package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.zhuinden.navigator.changehandlers.AnimatorViewChangeHandler

class FadeChangeHandler : AnimatorViewChangeHandler() {

    override fun createAnimator(previousView: View, newView: View, direction: Int): Animator {
        val set = AnimatorSet()
        set.play(ObjectAnimator.ofFloat(previousView, "alpha", 1f, 0f))
        set.play(ObjectAnimator.ofFloat(newView, "alpha", 0f, 1f))
        return set
    }

}