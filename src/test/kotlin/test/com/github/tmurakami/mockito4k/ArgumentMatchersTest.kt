package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.any
import com.github.tmurakami.mockito4k.anyNullable
import com.github.tmurakami.mockito4k.argThat
import com.github.tmurakami.mockito4k.eq
import com.github.tmurakami.mockito4k.isA
import com.github.tmurakami.mockito4k.isNotNull
import com.github.tmurakami.mockito4k.isNull
import com.github.tmurakami.mockito4k.matches
import com.github.tmurakami.mockito4k.mock
import com.github.tmurakami.mockito4k.nullable
import com.github.tmurakami.mockito4k.refEq
import com.github.tmurakami.mockito4k.same
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito.then
import org.mockito.Mockito.times
import java.io.Serializable
import java.util.regex.Pattern

@WithMockito
class ArgumentMatchersTest {

    @Test
    fun `anyNullable() should make a matcher that matches anything`() {
        val mock = mock<I>()
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(times(2)).nullable(anyNullable())
    }

    @Test
    fun `any() should make a matcher that is of the given type`() {
        val mock = mock<I>()
        mock.nullable(null)
        mock.nullable(Any())
        then(mock).should(times(1)).nullable(any())
    }

    @Test
    fun `eq() should make a matcher that is equal to the given value`() {
        val mock = mock<I>()
        val arg = Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(eq(arg))
    }

    @Test
    fun `refEq() should make a matcher that is reflection-equal to the given value`() {
        val mock = mock<I>()
        val first = Any()
        val arg = first to Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(refEq(first to null, "second"))
    }

    @Test
    fun `same() should make a matcher that is same as the given value`() {
        val mock = mock<I>()
        val arg = Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(same(arg))
    }

    @Test
    fun `isA() should make a matcher that is of the given type`() {
        val mock = mock<I>()
        mock.nonNull("")
        mock.nonNull(Any())
        then(mock).should().nonNull(isA<Serializable>())
    }

    @Test
    fun `isNull() should make a matcher that is equal to null`() {
        val mock = mock<I>()
        mock.nullable(null)
        then(mock).should().nullable(isNull())
    }

    @Test
    fun `isNotNull() should make a matcher that is not equal to null`() {
        val mock = mock<I>()
        mock.nullable(Any())
        then(mock).should().nullable(isNotNull())
    }

    @Test
    fun `nullable() should make a matcher that is either null or of the given type`() {
        val mock = mock<I>()
        mock.nullable(null)
        mock.nullable("")
        mock.nullable(Any())
        then(mock).should(times(2)).nullable(nullable<Serializable>())
    }

    @Test
    fun `matches(String) should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches("[\\w]+"))
    }

    @Test
    fun `matches(Regex) should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches(Regex("[\\w]+")))
    }

    @Test
    fun `matches(Pattern) should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.nonNull("foobar")
        then(mock).should().nonNull(matches(Pattern.compile("[\\w]+")))
    }

    @Test
    fun `argThat(Function1) should make a primitive matcher with the given function`() {
        val mock = mock<I>()
        val arg = 0
        mock.int(arg)
        then(mock).should().int(argThat { it == 0 })
    }

    @Test
    fun `argThat(Function1) should make a object matcher with the given function`() {
        val mock = mock<I>()
        val arg = Any()
        mock.nonNull(arg)
        then(mock).should().nonNull(argThat { it == arg })
    }

    @Test
    fun `argThat(ArgumentMatcher) should make a primitive matcher with the given ArgumentMatcher`() {
        val mock = mock<I>()
        val arg = 0
        mock.int(arg)
        then(mock).should().int(argThat(ArgumentMatcher { it == arg }))
    }

    @Test
    fun `argThat(ArgumentMatcher) should make a object matcher with the given ArgumentMatcher`() {
        val mock = mock<I>()
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
