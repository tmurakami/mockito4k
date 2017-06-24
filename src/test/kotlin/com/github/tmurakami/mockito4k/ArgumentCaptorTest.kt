package com.github.tmurakami.mockito4k

import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ArgumentCaptorTest {

    @Mock
    private lateinit var mock: I

    @Test
    fun `capture should capture the argument without causing IllegalStateException`() {
        val arg = Any()
        mock.f(arg)
        val captor = argumentCaptor<Any>()
        then(mock).should().f(capture(captor))
        assertSame(arg, captor.value)
    }

    private interface I {
        fun f(arg: Any)
    }

}
