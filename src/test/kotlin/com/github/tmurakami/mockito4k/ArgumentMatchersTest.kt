package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito
import org.mockito.BDDMockito.then
import org.mockito.Mockito
import java.io.Serializable

class ArgumentMatchersTest {

    @Test
    fun `any should make a matcher that matches anything`() {
        val mock = mock<I>()
        mock.o(null)
        mock.o(Any())
        then(mock).should(Mockito.times(2)).o(any())
    }

    @Test
    fun `any_KClass function should make a matcher that is of the given type`() {
        val mock = mock<I>()
        mock.o(null)
        mock.o(Any())
        then(mock).should(Mockito.times(1)).o(any(Any::class))
    }

    @Test
    fun `eq should make a matcher that is equal to the given value`() {
        val arg = Any()
        val mock = mock<I>()
        mock.o(arg)
        then(mock).should().o(eq(arg))
    }

    @Test
    fun `same should make a matcher that is same as the given value`() {
        val arg = Any()
        val mock = mock<I>()
        mock.o(arg)
        then(mock).should().o(same(arg))
    }

    @Test
    fun `isA should make a matcher that is of the given type`() {
        val mock = mock<I>()
        mock.o("")
        mock.o(Any())
        then(mock).should().o(isA(Serializable::class))
    }

    @Test
    fun `isNull should make a matcher that is equal to null`() {
        val mock = mock<I>()
        mock.o(null)
        then(mock).should().o(isNull())
    }

    @Test
    fun `isNotNull should make a matcher that is not equal to null`() {
        val mock = mock<I>()
        mock.o(Any())
        then(mock).should().o(isNotNull())
    }

    @Test
    fun `nullable should make a matcher that is either null or of the given type`() {
        val mock = mock<I>()
        mock.o(null)
        mock.o("")
        mock.o(Any())
        then(mock).should(Mockito.times(2)).o(nullable<Serializable>())
    }

    @Test
    fun `matches_Regex should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.o("foobar")
        then(mock).should().o(matches(Regex("[\\w]+")))
    }

    @Test
    fun `argThat_Function1 should make a primitive matcher with the given function`() {
        val mock = mock<I>()
        val arg = 0
        mock.i(arg)
        then(mock).should().i(argThat { it == 0 })
    }

    @Test
    fun `argThat_Function1 should make a object matcher with the given function`() {
        val mock = mock<I>()
        val arg = Any()
        mock.o(arg)
        BDDMockito.then(mock).should().o(argThat { it == arg })
    }

    @Test
    fun `argThat_ArgumentMatcher should make a primitive matcher with the given ArgumentMatcher`() {
        val mock = mock<I>()
        val arg = 0
        mock.i(arg)
        then(mock).should().i(argThat(ArgumentMatcher { it == arg }))
    }

    @Test
    fun `argThat_ArgumentMatcher should make a object matcher with the given ArgumentMatcher`() {
        val mock = mock<I>()
        val arg = Any()
        mock.o(arg)
        then(mock).should().o(argThat(ArgumentMatcher { it == arg }))
    }

    interface I {
        fun i(arg: Int)
        fun o(arg: Any?)
    }

}
