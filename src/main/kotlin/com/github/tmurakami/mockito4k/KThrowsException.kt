package com.github.tmurakami.mockito4k

import org.mockito.internal.stubbing.answers.ThrowsException
import org.mockito.invocation.InvocationOnMock

internal class KThrowsException(throwable: Throwable) : ThrowsException(throwable) {

    override fun validateFor(invocation: InvocationOnMock) {
        // Kotlin has no checked exceptions, so only invocations that are not for Kotlin should be validated.
        if (!invocation.mock.javaClass.isCompiledByKotlinCompiler) super.validateFor(invocation)
    }

    companion object {
        private const val serialVersionUID = 8529150053065919680L
    }

}
