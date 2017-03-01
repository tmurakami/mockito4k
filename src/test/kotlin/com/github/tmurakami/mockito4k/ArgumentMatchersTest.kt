package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.Serializable

class ArgumentMatchersTest {

    @Test
    fun `any() should make a matcher that matches anything`() {
        val mock = Mockito.mock(I::class.java)
        mock.nullableAny(null)
        mock.nullableAny(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).nullableAny(any())
    }

    @Test
    fun `any(KClass) function should make a matcher that is of the given type`() {
        val mock = Mockito.mock(I::class.java)
        mock.nullableAny(null)
        mock.nullableAny(Any())
        BDDMockito.then(mock).should(Mockito.times(1)).nullableAny(any(Any::class))
    }

    @Test
    fun `eq(T) should make a matcher that is equal to the given value`() {
        val arg = Any()
        val mock = Mockito.mock(I::class.java)
        mock.any(arg)
        BDDMockito.then(mock).should().any(eq(arg))
    }

    @Test
    fun `same(T) should make a matcher that is same as the given value`() {
        val arg = Any()
        val mock = Mockito.mock(I::class.java)
        mock.any(arg)
        BDDMockito.then(mock).should().any(same(arg))
    }

    @Test
    fun `isA(KClass) should make a matcher that is of the given type`() {
        val mock = Mockito.mock(I::class.java)
        mock.any("")
        mock.any(Any())
        BDDMockito.then(mock).should().any(isA(Serializable::class))
    }

    @Test
    fun `isNull() should make a matcher that is equal to null`() {
        val mock = Mockito.mock(I::class.java)
        mock.nullableAny(null)
        BDDMockito.then(mock).should().nullableAny(isNull())
    }

    @Test
    fun `isNotNull() should make a matcher that is not equal to null`() {
        val mock = Mockito.mock(I::class.java)
        mock.nullableAny(Any())
        BDDMockito.then(mock).should().nullableAny(isNotNull())
    }

    @Test
    fun `nullable() should make a matcher that is either null or of the given type`() {
        val mock = Mockito.mock(I::class.java)
        mock.nullableAny(null)
        mock.nullableAny("")
        mock.nullableAny(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).nullableAny(nullable<Serializable>())
    }

    @Test
    fun `matches(Regex) should make a matcher that matches the given regular expression`() {
        val mock = Mockito.mock(I::class.java)
        mock.string("foobar")
        BDDMockito.then(mock).should().string(matches(Regex("[\\w]+")))
    }

    @Test
    fun `argThat(Function1) should make a matcher with the given function`() {
        val mock = Mockito.mock(I::class.java)
        val arg = Any()
        mock.any(arg)
        BDDMockito.then(mock).should().any(argThat { it == arg })
    }

    @Test
    fun `argThat(ArgumentMatcher) should make a matcher with the given ArgumentMatcher`() {
        val mock = Mockito.mock(I::class.java)
        val arg = Any()
        mock.any(arg)
        BDDMockito.then(mock).should().any(argThat(ArgumentMatcher { it == arg }))
    }

    interface I {
        fun any(arg: Any)
        fun nullableAny(arg: Any?)
        fun string(arg: String)
    }

}
