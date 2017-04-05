package io.github.gumil.testnavigator.changehandler

import android.transition.*
import android.view.View
import android.view.ViewGroup

class ArcFadeMoveChangeHandler : TransitionChangeHandler() {

    override fun getTransition(container: ViewGroup,
                               previousView: View,
                               newView: View,
                               direction: Int): Transition {
        val transition = TransitionSet()
                .setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                .addTransition(Fade(Fade.OUT))
                .addTransition(TransitionSet()
                        .addTransition(ChangeBounds())
                        .addTransition(ChangeClipBounds())
                        .addTransition(ChangeTransform()))
                .addTransition(Fade(Fade.IN))

        transition.pathMotion = ArcMotion()

        return transition
    }

}