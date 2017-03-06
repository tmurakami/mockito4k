package com.github.tmurakami.mockito4k

import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness
import java.io.Serializable

class ArgumentMatchersTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    lateinit var mock: I

    @Test
    fun `any should make a matcher that matches anything`() {
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(Mockito.times(2)).nullable(any())
    }

    @Test
    fun `any_KClass function should make a matcher that is of the given type`() {
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(Mockito.times(1)).nullable(any(Any::class))
    }

    @Test
    fun `eq should make a matcher that is equal to the given value`() {
        val arg = Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(eq(arg))
    }

    @Test
    fun `refEq should make a matcher that is reflection-equal to the given value`() {
        val first = Any()
        val arg = first to Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(refEq(first to null, "second"))
    }

    @Test
    fun `same should make a matcher that is same as the given value`() {
        val arg = Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(same(arg))
    }

    @Test
    fun `isA should make a matcher that is of the given type`() {
        mock.nonNull("")
        mock.nonNull(Any())
        then(mock).should().nonNull(isA(Serializable::class))
    }

    @Test
    fun `isNull should make a matcher that is equal to null`() {
        mock.nullable(null)
        then(mock).should().nullable(isNull())
    }

    @Test
    fun `isNotNull should make a matcher that is not equal to null`() {
        mock.nullable(Any())
        then(mock).should().nullable(isNotNull())
    }

    @Test
    fun `nullable should make a matcher that is either null or of the given type`() {
        mock.nullable(null)
        mock.nullable("")
        mock.nullable(Any())
        then(mock).should(Mockito.times(2)).nullable(nullable<Serializable>())
    }

    @Test
    fun `matches_Regex should make a matcher that matches the given regular expression`() {
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches(Regex("[\\w]+")))
    }

    @Test
    fun `argThat_Function1 should make a primitive matcher with the given function`() {
        val arg = 0
        mock.int(arg)
        then(mock).should().int(argThat { it == 0 })
    }

    @Test
    fun `argThat_Function1 should make a object matcher with the given function`() {
        val arg = Any()
        mock.nonNull(arg)
        BDDMockito.then(mock).should().nonNull(argThat { it == arg })
    }

    @Test
    fun `argThat_ArgumentMatcher should make a primitive matcher with the given ArgumentMatcher`() {
        val arg = 0
        mock.int(arg)
        then(mock).should().int(argThat(ArgumentMatcher { it == arg }))
    }

    @Test
    fun `argThat_ArgumentMatcher should make a object matcher with the given ArgumentMatcher`() {
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
