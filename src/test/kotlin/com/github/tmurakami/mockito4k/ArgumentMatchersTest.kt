package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.Serializable

class ArgumentMatchersTest {

    @Test
    fun the_any_method_should_make_a_matcher_that_matches_anything() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(null)
        mock.f(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).f(any())
    }

    @Test
    fun the_eq_method_should_make_a_matcher_that_is_equal_to_the_given_value() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = Any()
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(eq(arg))
    }

    @Test
    fun the_same_method_should_make_a_matcher_that_is_same_as_the_given_value() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = Any()
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(same(arg))
    }

    @Test
    fun the_isA_method_should_make_a_matcher_that_is_of_the_given_type() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("")
        mock.f(Any())
        BDDMockito.then(mock).should().f(isA(Serializable::class))
    }

    @Test
    fun the_isNull_method_should_make_a_matcher_that_is_equal_to_null() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(null)
        BDDMockito.then(mock).should().f(isNull())
    }

    @Test
    fun the_isNotNull_method_should_make_a_matcher_that_is_not_equal_to_null() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(Any())
        BDDMockito.then(mock).should().f(isNotNull())
    }

    @Test
    fun the_nullable_method_should_make_a_matcher_that_is_either_null_or_of_the_given_type() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(null)
        mock.f("")
        mock.f(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).f(nullable<Serializable>())
    }

    @Test
    fun the_matches_method_should_make_a_matcher_that_matches_the_given_regular_expression() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(matches(Regex("[\\w]+")))
    }

    @Test
    fun the_argThat_method_should_make_a_matcher_with_the_given_function() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat { it == arg })
    }

    @Test
    fun the_argThat_method_should_make_a_matcher_with_the_given_ArgumentMatcher() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat(ArgumentMatcher { it == arg }))
    }

}
