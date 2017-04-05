package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class FlipChangeHandler(
        val flipDirection: FlipDirection = FlipChangeHandler.FlipDirection.RIGHT,
        val duration: Long = FlipChangeHandler.DEFAULT_ANIMATION_DURATION
) : AnimatorChangeHandler() {

    companion object {
        private const val DEFAULT_ANIMATION_DURATION: Long = 300
    }

    enum class FlipDirection(internal val inStartRotation: Int,
                             internal val outEndRotation: Int,
                             internal val property: Property<View, Float>) {
        LEFT(-180, 180, View.ROTATION_Y),
        RIGHT(180, -180, View.ROTATION_Y),
        UP(-180, 180, View.ROTATION_X),
        DOWN(180, -180, View.ROTATION_X)
    }

    override fun createAnimator(previousView: View, newView: View, direction: Int): Animator {
        val animatorSet = AnimatorSet()

        newView.let {
            it.alpha = 0f

            val rotation = ObjectAnimator.ofFloat<View>(it, flipDirection.property,
                    flipDirection.inStartRotation.toFloat(), 0F)
                    .setDuration(duration)
            rotation.interpolator = AccelerateDecelerateInterpolator()
            animatorSet.play(rotation)

            val alpha = ObjectAnimator.ofFloat<View>(it, View.ALPHA, 1F)
                    .setDuration(duration / 2)
            alpha.startDelay = duration / 3
            animatorSet.play(alpha)
        }


        previousView.let {
            val rotation = ObjectAnimator.ofFloat<View>(it, flipDirection.property, 0F,
                    flipDirection.outEndRotation.toFloat())
                    .setDuration(duration)
            rotation.interpolator = AccelerateDecelerateInterpolator()
            animatorSet.play(rotation)

            val alpha = ObjectAnimator.ofFloat<View>(it, View.ALPHA, 0F)
                    .setDuration(duration / 2)
            alpha.startDelay = duration / 3
            animatorSet.play(alpha)
        }


        return animatorSet
    }
}