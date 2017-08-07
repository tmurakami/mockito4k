package com.github.tmurakami.mockito4k

import org.mockito.internal.stubbing.answers.ThrowsException
import org.mockito.invocation.InvocationOnMock

internal class Mockito4kThrowsException(throwable: Throwable) : ThrowsException(throwable) {

    override fun validateFor(invocation: InvocationOnMock) {
        if (invocation.mock.javaClass.isCompiledByKotlinCompiler) {
            // Kotlin has no checked exception.
            return
        }
        super.validateFor(invocation)
    }

    private val Class<*>.isCompiledByKotlinCompiler: Boolean
        // All the Kotlin classes are marked with the `kotlin.Metadata` annotation. Note that if the annotation is
        // renamed or removed by shrinking tools (e.g., ProGuard), the code below will not work well.
        get() = declaredAnnotations.any { it.annotationClass.java.name == "kotlin.Metadata" }

    companion object {
        private const val serialVersionUID = 2358320508432788020L
    }

}
