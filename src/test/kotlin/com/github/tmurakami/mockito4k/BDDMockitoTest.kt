package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class BDDMockitoTest {

    @Spy
    lateinit var mock: C

    @Test
    fun `given should stub the function to call the specified answer object`() =
        assertEquals("foo", given(mock) {
            running { s }.will(Answer { "foo" })
        }.s)

    @Test
    fun `given should stub the function to call the specified answer function`() =
        assertEquals("foo", given(mock) {
            running { s }.willAnswer { "foo" }
        }.s)

    @Test
    fun `given should stub the function to call the real function`() {
        given(mock) {
            running { s = any(String::class) }.willReturn(Unit).willCallRealMethod()
        }
        mock.s = "foo"
        assertEquals("", mock.s)
        mock.s = "bar"
        assertEquals("bar", mock.s)
    }

    @Test
    fun `given should stub the function to return the specified value`() =
        assertEquals("foo", given(mock) {
            running { s }.willReturn("foo")
        }.s)

    @Test
    fun `given should stub the void function to return Unit`() {
        given(mock) {
            running { s = any(String::class) }.willReturn(Unit)
        }
        mock.s = "foo"
        then(mock).should().s = "foo"
    }

    @Test(expected = IllegalStateException::class)
    fun `given should stub the function to throw the specified error`() {
        given(mock) {
            running { s = any(String::class) }.willThrow(IllegalStateException())
        }.s = "foo"
    }

    @Test(expected = IllegalStateException::class)
    fun `given should stub the function to throw the error of the specified type`() {
        given(mock) {
            running { s = any(String::class) }.willThrow(IllegalStateException::class)
        }.s = "foo"
    }

    open class C {
        open var s: String = ""
    }

}
