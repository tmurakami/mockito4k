package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito

class AdditionalMatchersTest {

    @Test
    fun testAnd() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("ab")
        BDDMockito.then(mock).should().f(and(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun testOr() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(or(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun testNot() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(not(ArgumentMatchers.eq("a")))
    }

}
