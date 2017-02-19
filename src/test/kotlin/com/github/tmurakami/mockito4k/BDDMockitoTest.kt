package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.Mockito
import java.util.*

class BDDMockitoTest {
    @Test(expected = RuntimeException::class)
    fun the_willThrow_method_should_make_a_BDDStubber_that_causes_error_of_the_given_type() {
        val list = ArrayList<String>()
        val mock = Mockito.spy(list)
        willThrow(RuntimeException::class).given(mock).clear()
        mock.clear()
    }
}
