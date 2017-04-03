package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.zhuinden.simplestack.StateChange

class VerticalChangeHandler : SingleViewChangeHandler() {

    override fun createAnimator(view: View, direction: Int): Animator {
        val set = AnimatorSet()

        if (direction == StateChange.FORWARD) {
            val translation = direction * view.height
            set.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translation.toFloat(), 0F))
        } else {
            val fromTranslation = -1 * direction * view.height
            set.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, fromTranslation.toFloat()))
        }
        return set
    }

}