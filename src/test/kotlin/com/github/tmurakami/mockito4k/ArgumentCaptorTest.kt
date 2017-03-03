package com.github.tmurakami.mockito4k

import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.BDDMockito.then

class ArgumentCaptorTest {

    @Test
    fun `capture should capture the argument without causing IllegalStateException`() {
        val arg = Any()
        val mock = mock<I>()
        mock.f(arg)
        val captor = argumentCaptor<Any>()
        then(mock).should().f(capture(captor))
        assertSame(arg, captor.value)
    }

    interface I {
        fun f(arg: Any)
    }

}
