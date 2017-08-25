package com.github.tmurakami.mockito4k

import org.mockito.internal.stubbing.answers.ThrowsException
import org.mockito.invocation.InvocationOnMock

internal class KThrowsException(throwable: Throwable) : ThrowsException(throwable) {

    override fun answer(invocation: InvocationOnMock?): Any? = filterStackTrace { super.answer(invocation) }

    // Kotlin has no checked exceptions.
    override fun validateFor(invocation: InvocationOnMock) = Unit

    companion object {
        private const val serialVersionUID = 8529150053065919680L
    }

}
