package com.github.tmurakami.mockito4k

import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class ArgumentCaptorTest {

    @Test
    fun `capture(ArgumentCaptor) should capture the argument without causing IllegalStateException`() {
        val arg = Any()
        val mock = Mockito.mock(I::class.java)
        mock.f(arg)
        val captor = argumentCaptor<Any>()
        BDDMockito.then(mock).should().f(capture(captor))
        assertSame(arg, captor.value)
    }

    interface I {
        fun f(arg: Any)
    }

}
