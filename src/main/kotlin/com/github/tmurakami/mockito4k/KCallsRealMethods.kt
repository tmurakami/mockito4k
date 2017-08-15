package com.github.tmurakami.mockito4k

import org.mockito.internal.stubbing.answers.CallsRealMethods
import org.mockito.invocation.InvocationOnMock
import java.lang.reflect.Method

// The `CallsRealMethods` that supports interface methods with body.
internal object KCallsRealMethods : CallsRealMethods() {

    @Suppress("unused")
    private const val serialVersionUID = -3958443847469232832L

    override fun answer(invocation: InvocationOnMock): Any? {
        val defaultImpl = invocation.method.defaultImpl ?: return super.answer(invocation)
        return defaultImpl.invoke(null, invocation.mock, *invocation.arguments)
    }

    override fun validateFor(invocation: InvocationOnMock) {
        if (invocation.method.defaultImpl == null) super.validateFor(invocation)
    }

    private val Method.defaultImpl: Method?
        get() = if (declaringClass.isInterface && declaringClass.isCompiledByKotlinCompiler)
            try {
                try {
                    Class.forName(declaringClass.name + "\$DefaultImpls", false, declaringClass.classLoader)
                } catch (e: ClassNotFoundException) {
                    null
                }?.getMethod(name, declaringClass, *parameterTypes)
            } catch (e: NoSuchMethodException) {
                null
            }
        else null

}
