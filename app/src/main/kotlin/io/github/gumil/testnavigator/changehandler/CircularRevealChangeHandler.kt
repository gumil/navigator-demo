package io.github.gumil.testnavigator.changehandler

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChange

class CircularRevealChangeHandler(
        fromView: View,
        containerView: ViewGroup
) : SingleViewChangeHandler() {

    private val cx: Int
    private val cy: Int

    init {
        val fromLocation = IntArray(2)
        fromView.getLocationInWindow(fromLocation)

        val containerLocation = IntArray(2)
        containerView.getLocationInWindow(containerLocation)

        val relativeLeft = fromLocation[0] - containerLocation[0]
        val relativeTop = fromLocation[1] - containerLocation[1]

        cx = fromView.width / 2 + relativeLeft
        cy = fromView.height / 2 + relativeTop
    }

    override fun createAnimator(view: View, direction: Int): Animator {
        val radius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val isPush = direction == StateChange.FORWARD
        return if (isPush) {
            ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, radius)
        } else {
            ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0f)
        }
    }

}