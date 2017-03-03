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
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(Mockito.times(2)).nullable(any())
    }

    @Test
    fun `any_KClass function should make a matcher that is of the given type`() {
        val mock = mock<I>()
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(Mockito.times(1)).nullable(any(Any::class))
    }

    @Test
    fun `eq should make a matcher that is equal to the given value`() {
        val arg = Any()
        val mock = mock<I>()
        mock.nonNull(arg)
        then(mock).should().nonNull(eq(arg))
    }

    @Test
    fun `refEq should make a matcher that is reflection-equal to the given value`() {
        val first = Any()
        val arg = first to Any()
        val mock = mock<I>()
        mock.nonNull(arg)
        then(mock).should().nonNull(refEq(first to null, "second"))
    }

    @Test
    fun `same should make a matcher that is same as the given value`() {
        val arg = Any()
        val mock = mock<I>()
        mock.nonNull(arg)
        then(mock).should().nonNull(same(arg))
    }

    @Test
    fun `isA should make a matcher that is of the given type`() {
        val mock = mock<I>()
        mock.nonNull("")
        mock.nonNull(Any())
        then(mock).should().nonNull(isA(Serializable::class))
    }

    @Test
    fun `isNull should make a matcher that is equal to null`() {
        val mock = mock<I>()
        mock.nullable(null)
        then(mock).should().nullable(isNull())
    }

    @Test
    fun `isNotNull should make a matcher that is not equal to null`() {
        val mock = mock<I>()
        mock.nullable(Any())
        then(mock).should().nullable(isNotNull())
    }

    @Test
    fun `nullable should make a matcher that is either null or of the given type`() {
        val mock = mock<I>()
        mock.nullable(null)
        mock.nullable("")
        mock.nullable(Any())
        then(mock).should(Mockito.times(2)).nullable(nullable<Serializable>())
    }

    @Test
    fun `matches_Regex should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches(Regex("[\\w]+")))
    }

    @Test
    fun `argThat_Function1 should make a primitive matcher with the given function`() {
        val mock = mock<I>()
        val arg = 0
        mock.int(arg)
        then(mock).should().int(argThat { it == 0 })
    }

    @Test
    fun `argThat_Function1 should make a object matcher with the given function`() {
        val mock = mock<I>()
        val arg = Any()
        mock.nonNull(arg)
        BDDMockito.then(mock).should().nonNull(argThat { it == arg })
    }

    @Test
    fun `argThat_ArgumentMatcher should make a primitive matcher with the given ArgumentMatcher`() {
        val mock = mock<I>()
        val arg = 0
        mock.int(arg)
        then(mock).should().int(argThat(ArgumentMatcher { it == arg }))
    }

    @Test
    fun `argThat_ArgumentMatcher should make a object matcher with the given ArgumentMatcher`() {
        val mock = mock<I>()
        val arg = Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(argThat(ArgumentMatcher { it == arg }))
    }

    interface I {
        fun int(arg: Int)
        fun nonNull(arg: Any)
        fun nullable(arg: Any?)
    }

}
