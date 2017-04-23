package io.github.gumil.testnavigator.multiple

import com.zhuinden.simplestack.StateChanger
import java.util.*

internal object StateChangerStack {

    private var stack = Stack<StateChanger>()

    fun push(stateChanger: StateChanger) {
        stack.push(stateChanger)
    }

    fun pop() = stack.pop()

    fun isEmpty() = stack.empty()

    fun peek() = stack.clear()

    fun clear() {
        stack.clear()
    }

    override fun toString(): String {
        return stack.toString()
    }
}