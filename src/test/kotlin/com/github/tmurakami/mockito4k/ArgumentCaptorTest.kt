package com.github.tmurakami.mockito4k

import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

class ArgumentCaptorTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    lateinit var mock: I

    @Test
    fun `capture should capture the argument without causing IllegalStateException`() {
        val arg = Any()
        mock.f(arg)
        val captor = argumentCaptor<Any>()
        then(mock).should().f(capture(captor))
        assertSame(arg, captor.value)
    }

    interface I {
        fun f(arg: Any)
    }

}
