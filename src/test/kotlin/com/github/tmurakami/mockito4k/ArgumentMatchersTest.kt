package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.Serializable

class ArgumentMatchersTest {

    @Test
    fun `any should make a matcher that matches anything`() {
        val mock = Mockito.mock(I::class.java)
        mock.f(null)
        mock.f(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).f(any())
    }

    @Test
    fun `any_KClass function should make a matcher that is of the given type`() {
        val mock = Mockito.mock(I::class.java)
        mock.f(null)
        mock.f(Any())
        BDDMockito.then(mock).should(Mockito.times(1)).f(any(Any::class))
    }

    @Test
    fun `eq should make a matcher that is equal to the given value`() {
        val arg = Any()
        val mock = Mockito.mock(I::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(eq(arg))
    }

    @Test
    fun `same should make a matcher that is same as the given value`() {
        val arg = Any()
        val mock = Mockito.mock(I::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(same(arg))
    }

    @Test
    fun `isA should make a matcher that is of the given type`() {
        val mock = Mockito.mock(I::class.java)
        mock.f("")
        mock.f(Any())
        BDDMockito.then(mock).should().f(isA(Serializable::class))
    }

    @Test
    fun `isNull should make a matcher that is equal to null`() {
        val mock = Mockito.mock(I::class.java)
        mock.f(null)
        BDDMockito.then(mock).should().f(isNull())
    }

    @Test
    fun `isNotNull should make a matcher that is not equal to null`() {
        val mock = Mockito.mock(I::class.java)
        mock.f(Any())
        BDDMockito.then(mock).should().f(isNotNull())
    }

    @Test
    fun `nullable should make a matcher that is either null or of the given type`() {
        val mock = Mockito.mock(I::class.java)
        mock.f(null)
        mock.f("")
        mock.f(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).f(nullable<Serializable>())
    }

    @Test
    fun `matches_Regex should make a matcher that matches the given regular expression`() {
        val mock = Mockito.mock(I::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(matches(Regex("[\\w]+")))
    }

    @Test
    fun `argThat_Function1 should make a matcher with the given function`() {
        val mock = Mockito.mock(I::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat { it == arg })
    }

    @Test
    fun `argThat_ArgumentMatcher should make a matcher with the given ArgumentMatcher`() {
        val mock = Mockito.mock(I::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat(ArgumentMatcher { it == arg }))
    }

    interface I {
        fun f(arg: Any?)
    }

}
