package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.AdditionalMatchers
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.then

class AdditionalMatchersTest {

    @Test
    fun `and should make a primitive matcher that matches both of the given matchers`() {
        val mock = mock<I>()
        mock.i(1)
        then(mock).should().i(and(AdditionalMatchers.geq(0), AdditionalMatchers.leq(1)))
    }

    @Test
    fun `and should make a object matcher that matches both of the given matchers`() {
        val mock = mock<I>()
        mock.s("ab")
        then(mock).should().s(and(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun `or should make a primitive matcher that matcher either of the given matchers`() {
        val mock = mock<I>()
        mock.i(1)
        then(mock).should().i(or(AdditionalMatchers.geq(0), AdditionalMatchers.leq(1)))
    }

    @Test
    fun `or should make a object matcher that matcher either of the given matchers`() {
        val mock = mock<I>()
        mock.s("b")
        then(mock).should().s(or(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun `not should make a primitive matcher that does not match the given matcher`() {
        val mock = mock<I>()
        mock.i(1)
        then(mock).should().i(not(ArgumentMatchers.eq(0)))
    }

    @Test
    fun `not should make a object matcher that does not match the given matcher`() {
        val mock = mock<I>()
        mock.s("b")
        then(mock).should().s(not(ArgumentMatchers.eq("a")))
    }

    interface I {
        fun i(arg: Int)
        fun s(arg: String)
    }

}
