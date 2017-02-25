package com.github.tmurakami.mockito4k

import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class ArgumentCaptorTest {
    @Test
    fun the_capture_function_should_be_able_to_capture_the_argument() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = Any()
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        val captor = argumentCaptor<Any>()
        BDDMockito.then(mock).should().f(capture(captor))
        assertSame(arg, captor.value)
    }
}
