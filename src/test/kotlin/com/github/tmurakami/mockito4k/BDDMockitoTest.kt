package com.github.tmurakami.mockito4k

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class BDDMockitoTest {

    @Test(expected = RuntimeException::class)
    fun testGiven() {
        abstract class A {
            abstract fun f(): Any?
        }

        val mock = Mockito.mock(A::class.java)
        given(mock.f()).willReturn(Unit).willReturn(null).willThrow(RuntimeException())
        assertEquals(Unit, mock.f())
        assertNull(mock.f())
        mock.f()
    }

    @Test
    fun testThen() {
        abstract class A {
            abstract fun f()
        }

        val mock = Mockito.mock(A::class.java)
        mock.f()
        mock.f()
        then(mock).should(times(2)).f()
    }

    @Test(expected = RuntimeException::class)
    fun testWillThrow() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willThrow(RuntimeException()).given(mock).clear()
        mock.clear()
    }

    @Test
    fun testWillAnswer() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willAnswer { "${arguments[0]}" }.given(mock)[any()]
        assertEquals("0", mock[0])
        assertEquals("1", mock[1])
    }

    @Test
    fun testWillDoNothing() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willDoNothing().given(mock).clear()
        mock.add("one")
        mock.clear()
        assertEquals(1, mock.size)
    }

    @Test
    fun testWillReturn() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willReturn(null).given(mock)[0]
        assertNull(mock[0])
    }

    @Test
    fun testWillCallRealMethod() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willCallRealMethod().given(mock).clear()
        mock.add("one")
        mock.clear()
        assertTrue(mock.isEmpty())
    }

}
