package com.github.tmurakami.mockito4k

import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.mockito.stubbing.ValidableAnswer
import java.io.Serializable

internal class AnswerDelegate<T>(private val delegate: Answer<T>,
                                 private val shouldBeValidated: InvocationOnMock.() -> Boolean) :
    Answer<T>, ValidableAnswer, Serializable {

    override fun answer(invocation: InvocationOnMock): T = delegate.answer(invocation)

    override fun validateFor(invocation: InvocationOnMock) {
        val delegate = this.delegate
        if (delegate is ValidableAnswer && invocation.shouldBeValidated()) delegate.validateFor(invocation)
    }

    companion object {
        private const val serialVersionUID = 2358320508432788020L
    }

}
