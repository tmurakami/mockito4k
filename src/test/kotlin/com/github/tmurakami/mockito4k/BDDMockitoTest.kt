package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.stubbing.Answer

class BDDMockitoTest {

    @Test
    fun `given should stub the function to call the specified answer object`() {
        val mock = spy<C>()
        assertEquals("foo", given(mock) {
            running { s }.will(Answer { "foo" })
        }.s)
    }

    @Test
    fun `given should stub the function to call the specified answer function`() {
        val mock = spy<C>()
        assertEquals("foo", given(mock) {
            running { s }.will { "foo" }
        }.s)
    }

    @Test
    fun `given should stub the function to call the real function`() {
        val mock = spy<C>()
        given(mock) {
            running { s = any(String::class) }.willReturn(Unit).willCallRealMethod()
        }
        mock.s = "foo"
        assertEquals("", mock.s)
        mock.s = "bar"
        assertEquals("bar", mock.s)
    }

    @Test
    fun `given should stub the function to return the specified value`() {
        val mock = spy<C>()
        assertEquals("foo", given(mock) {
            running { s }.willReturn("foo")
        }.s)
    }

    @Test
    fun `given should stub the void function to return Unit`() {
        val mock = spy<C>()
        given(mock) {
            running { s = any(String::class) }.willReturn(Unit)
        }
        mock.s = "foo"
        then(mock).should().s = "foo"
    }

    @Test(expected = IllegalStateException::class)
    fun `given should stub the function to throw the specified error`() {
        val mock = spy<C>()
        given(mock) {
            running { s = any(String::class) }.willThrow(IllegalStateException())
        }.s = "foo"
    }

    @Test(expected = IllegalStateException::class)
    fun `given should stub the function to throw the error of the specified type`() {
        val mock = spy<C>()
        given(mock) {
            running { s = any(String::class) }.willThrow(IllegalStateException::class)
        }.s = "foo"
    }

    open class C {
        open var s: String = ""
    }

}
