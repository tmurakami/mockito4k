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
    private lateinit var mock: C

    @Test
    fun `given should stub the function to call the specified answer object`() =
        assertEquals("foo", given(mock) {
            calling { s }.will(Answer { "foo" })
        }.s)

    @Test
    fun `given should stub the function to call the specified answer function`() =
        assertEquals("foo", given(mock) {
            calling { s }.willAnswer { "foo" }
        }.s)

    @Test
    fun `given should stub the function to call the real function`() {
        given(mock) {
            calling { s = any() }.willReturn(Unit).willCallRealMethod()
        }
        mock.s = "foo"
        assertEquals("", mock.s)
        mock.s = "bar"
        assertEquals("bar", mock.s)
    }

    @Test
    fun `given should stub the function to return the specified value`() =
        assertEquals("foo", given(mock) {
            calling { s }.willReturn("foo")
        }.s)

    @Test
    fun `given should stub the void function to return Unit`() {
        given(mock) {
            calling { s = any() }.willReturn(Unit)
        }
        mock.s = "foo"
        then(mock).should().s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the specified error`() {
        given(mock) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the error of the specified type`() {
        given(mock) {
            calling { s = any() }.willThrow(E::class)
        }.s = "foo"
    }

    private class C {
        var s: String = ""
    }

    private class E : Exception()

}
