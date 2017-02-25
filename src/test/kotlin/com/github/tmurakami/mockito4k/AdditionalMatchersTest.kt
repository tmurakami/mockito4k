package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito

class AdditionalMatchersTest {

    @Test
    fun the_and_function_should_make_a_matcher_that_matches_both_of_the_given_matchers() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("ab")
        BDDMockito.then(mock).should().f(and(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun the_or_function_should_make_a_matcher_that_matcher_either_of_the_given_matchers() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(or(ArgumentMatchers.startsWith("a"), ArgumentMatchers.endsWith("b")))
    }

    @Test
    fun the_not_function_should_make_a_matcher_that_does_not_match_the_given_matcher() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(not(ArgumentMatchers.eq("a")))
    }

}
