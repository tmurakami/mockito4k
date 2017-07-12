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
    private lateinit var spy: C

    @Test
    fun `given should stub the function to call the specified answer object`() =
        assertEquals("foo", given(spy) {
            calling { s }.will(Answer { "foo" })
        }.s)

    @Test
    fun `given should stub the function to call the specified answer function`() =
        assertEquals("foo", given(spy) {
            calling { s }.willAnswer { "foo" }
        }.s)

    @Test
    fun `given should stub the function to call the real function`() {
        given(spy) {
            calling { s = any() }.willReturn(Unit).willCallRealMethod()
        }
        spy.s = "foo"
        assertEquals("", spy.s)
        spy.s = "bar"
        assertEquals("bar", spy.s)
    }

    @Test
    fun `given should stub the function to return the specified value`() =
        assertEquals("foo", given(spy) {
            calling { s }.willReturn("foo")
        }.s)

    @Test
    fun `given should stub the void function to return Unit`() {
        given(spy) {
            calling { s = any() }.willReturn(Unit)
        }
        spy.s = "foo"
        then(spy).should().s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the specified error`() {
        given(spy) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the error of the specified type`() {
        given(spy) {
            calling { s = any() }.willThrow(E::class)
        }.s = "foo"
    }

    private class C {
        var s: String = ""
    }

    private class E : Exception()

}
