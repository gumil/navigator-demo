package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.zhuinden.simplestack.StateChange

class VerticalChangeHandler : AnimatorChangeHandler() {

    override fun createAnimator(previousView: View, newView: View, direction: Int): Animator {
        val set = AnimatorSet()

        if (direction == StateChange.FORWARD) {
            val translation = direction * newView.height
            set.play(ObjectAnimator.ofFloat(newView, View.TRANSLATION_Y, translation.toFloat(), 0F))
        } else {
            val fromTranslation = -1 * direction * previousView.height
            set.play(ObjectAnimator.ofFloat(previousView, View.TRANSLATION_Y, fromTranslation.toFloat()))
        }
        return set
    }

}