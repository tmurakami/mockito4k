package com.github.tmurakami.mockito4k

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.io.Serializable
import java.util.regex.Pattern

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ArgumentMatchersTest {

    @Mock
    private lateinit var mock: I

    @Test
    fun `anyNullable should make a matcher that matches anything`() {
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(times(2)).nullable(anyNullable())
    }

    @Test
    fun `any function should make a matcher that is of the given type`() {
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(times(1)).nullable(any())
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
        then(mock).should().nonNull(isA<Serializable>())
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
        then(mock).should(times(2)).nullable(nullable<Serializable>())
    }

    @Test
    fun `matches_String should make a matcher that matches the given regular expression`() {
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches("[\\w]+"))
    }

    @Test
    fun `matches_Regex should make a matcher that matches the given regular expression`() {
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches(Regex("[\\w]+")))
    }

    @Test
    fun `matches_Pattern should make a matcher that matches the given regular expression`() {
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches(Pattern.compile("[\\w]+")))
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
        then(mock).should().nonNull(argThat { it == arg })
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

    private interface I {
        fun int(arg: Int)
        fun nonNull(arg: Any)
        fun nullable(arg: Any?)
    }

}
