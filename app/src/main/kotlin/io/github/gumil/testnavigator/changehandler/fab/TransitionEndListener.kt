package io.github.gumil.testnavigator.changehandler.fab

import android.transition.Transition

abstract class TransitionEndListener: Transition.TransitionListener {
    abstract fun onTransitionCompleted(transition: Transition)
    override fun onTransitionEnd(transition: Transition) {
        onTransitionCompleted(transition)
    }

    override fun onTransitionResume(transition: Transition?) {
    }

    override fun onTransitionPause(transition: Transition?) {
    }

    override fun onTransitionCancel(transition: Transition) {
        onTransitionCompleted(transition)
    }

    override fun onTransitionStart(transition: Transition?) {
    }

}