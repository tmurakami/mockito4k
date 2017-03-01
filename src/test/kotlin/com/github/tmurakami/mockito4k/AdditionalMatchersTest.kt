package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito

class AdditionalMatchersTest {

    @Test
    fun `and(T?, T?) should make a matcher that matches both of the given matchers`() {
        val mock = Mockito.mock(I::class.java)
        mock.f("ab")
        BDDMockito.then(mock).should().f(and(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun `or(T?, T?) should make a matcher that matcher either of the given matchers`() {
        val mock = Mockito.mock(I::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(or(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun `not(T?) should make a matcher that does not match the given matcher`() {
        val mock = Mockito.mock(I::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(not(ArgumentMatchers.eq("a")))
    }

    interface I {
        fun f(arg: String)
    }

}
