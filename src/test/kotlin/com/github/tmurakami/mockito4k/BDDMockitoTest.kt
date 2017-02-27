package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.util.*

class BDDMockitoTest {

    @Test(expected = RuntimeException::class)
    fun the_willThrow_function_should_make_a_BDDStubber_that_causes_error_of_the_given_type() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willThrow(RuntimeException::class).given(mock).clear()
        mock.clear()
    }

    @Test(expected = RuntimeException::class)
    fun the_willThrow_function_should_make_a_BDDMyOngoingStubbing_that_causes_error_of_the_given_type() {
        val mock = Mockito.mock(List::class.java)
        BDDMockito.given(mock[0]).willThrow(RuntimeException::class)
        mock[0]
    }

}
