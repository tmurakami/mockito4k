package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.util.*

class BDDMockitoTest {

    @Test(expected = RuntimeException::class)
    fun `willThrow should make a BDDStubber that causes error of the given type`() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willThrow(RuntimeException::class).given(mock).clear()
        mock.clear()
    }

    @Test(expected = RuntimeException::class)
    fun `BDDMyOngoingStubbing_willThrow should make a BDDMyOngoingStubbing that causes error of the given type`() {
        val mock = Mockito.mock(List::class.java)
        BDDMockito.given(mock[0]).willThrow(RuntimeException::class)
        mock[0]
    }

}
