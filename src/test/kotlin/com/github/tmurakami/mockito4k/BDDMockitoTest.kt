package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.Mockito
import java.util.*

class BDDMockitoTest {

    @Test(expected = RuntimeException::class)
    fun testWillThrowKClass() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willThrow(RuntimeException::class).given(mock).clear()
        mock.clear()
    }

    @Test(expected = RuntimeException::class)
    fun testWillThrowClass() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willThrow(RuntimeException::class.java).given(mock).clear()
        mock.clear()
    }

}
