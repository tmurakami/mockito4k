package com.github.tmurakami.mockito4k

import org.mockito.internal.stubbing.answers.CallsRealMethods
import org.mockito.invocation.InvocationOnMock
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

// The `CallsRealMethods` that supports interface functions with optional body.
internal object KCallsRealMethods : CallsRealMethods() {

    @Suppress("unused")
    private const val serialVersionUID = -3958443847469232832L

    private val Method.defaultImpl: Method?
        get() =
            try {
                declaringClass.run {
                    if (isInterface) classes.find { it.simpleName == "DefaultImpls" } else null
                }?.getMethod(name, declaringClass, *parameterTypes)
            } catch (e: NoSuchMethodException) {
                null
            }

    override fun answer(invocation: InvocationOnMock): Any? = filterStackTrace {
        val defaultImpl = invocation.method.defaultImpl
        if (defaultImpl == null) {
            super.answer(invocation)
        } else {
            try {
                defaultImpl.invoke(null, invocation.mock, *invocation.arguments)
            } catch (e: InvocationTargetException) {
                throw e.cause!!
            }
        }
    }

    override fun validateFor(invocation: InvocationOnMock) = filterStackTrace {
        if (invocation.method.defaultImpl == null) super.validateFor(invocation)
    }
}
