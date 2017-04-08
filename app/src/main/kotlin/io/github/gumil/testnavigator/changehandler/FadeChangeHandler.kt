package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.zhuinden.simplestack.navigator.changehandlers.AnimatorViewChangeHandler

class FadeChangeHandler : AnimatorViewChangeHandler() {

    override fun createAnimator(previousView: View, newView: View, direction: Int): Animator {
        val set = AnimatorSet()
        set.play(ObjectAnimator.ofFloat(previousView, View.ALPHA, 1f, 0f))
        set.play(ObjectAnimator.ofFloat(newView, View.ALPHA, 0f, 1f))
        return set
    }

}