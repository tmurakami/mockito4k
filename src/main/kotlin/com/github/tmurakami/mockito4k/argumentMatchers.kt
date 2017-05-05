package com.github.tmurakami.mockito4k

import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.internal.util.Primitives
import kotlin.reflect.KClass

/**
 * Prevents causing NullPointerException when using a [matcher] for method that only accepts non-null parameter.
 *
 * @param T the type of the given argument [matcher]
 * @param matcher the argument matcher
 * @return the given argument [matcher]
 */
fun <T> by(matcher: T?): T = matcher as T

/**
 * The delegation to [ArgumentMatchers#any()](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#any&#40;&#41;).
 *
 * @param T the type of the argument matcher
 * @return the result for executing [ArgumentMatchers#any()](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#any&#40;&#41;)
 */
fun <T> anyNullable(): T? = ArgumentMatchers.any()

/**
 * The delegation to [ArgumentMatchers#any(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#any&#40;java.lang.Class&#41;).
 *
 * @param T the type of acceptable values
 * @param clazz the class of the acceptable type
 * @return the result for executing [ArgumentMatchers#any(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#any&#40;java.lang.Class&#41;)
 */
fun <T : Any> any(clazz: KClass<T>): T = by(ArgumentMatchers.any(clazz.java))

/**
 * The delegation to [ArgumentMatchers#eq(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#eq&#40;T&#41;).
 *
 * @param T the type of the given [value]
 * @param value the value to be compared
 * @return the result for executing [ArgumentMatchers#eq(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#eq&#40;T&#41;)
 */
fun <T> eq(value: T): T = ArgumentMatchers.eq(value)

/**
 * The delegation to [ArgumentMatchers#refEq(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#refEq&#40;T,&#32;java.lang.String...&#41;).
 *
 * @param T the type of the given [value]
 * @param value the value to be compared
 * @return the result for executing [ArgumentMatchers#refEq(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#refEq&#40;T,&#32;java.lang.String...&#41;)
 */
fun <T> refEq(value: T, vararg excludeFields: String): T = ArgumentMatchers.refEq(value, *excludeFields)

/**
 * The delegation to [ArgumentMatchers#same(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#same&#40;T&#41;).
 *
 * @param T the type of the given [value]
 * @param value the value to be compared
 * @return the result for executing [ArgumentMatchers#same(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#same&#40;T&#41;)
 */
fun <T> same(value: T): T = ArgumentMatchers.same(value)

/**
 * The delegation to [ArgumentMatchers#isA(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#isA&#40;java.lang.Class&#41;).
 *
 * @param T the type of acceptable values
 * @param clazz the class of the acceptable type
 * @return the result for executing [ArgumentMatchers#isA(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#isA&#40;java.lang.Class&#41;)
 */
fun <T : Any> isA(clazz: KClass<T>): T = by(ArgumentMatchers.isA(clazz.java))

/**
 * The delegation to [ArgumentMatchers#isNull()](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#isNull&#40;&#41;).
 *
 * @param T the type of the argument matcher
 * @return the result for executing [ArgumentMatchers#isNull()](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#isNull&#40;&#41;)
 */
fun <T> isNull(): T? = ArgumentMatchers.isNull()

/**
 * The delegation to [ArgumentMatchers#isNotNull(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#isNotNull&#40;&#41;).
 *
 * @param T the type of the argument matcher
 * @return the result for executing [ArgumentMatchers#isNotNull(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#isNotNull&#40;&#41;)
 */
fun <T> isNotNull(): T? = ArgumentMatchers.isNotNull()

@Deprecated("Use `nullable(KClass)`", ReplaceWith("nullable(T::class)"))
inline fun <reified T : Any> nullable(): T? = ArgumentMatchers.nullable(T::class.java)

/**
 * The delegation to [ArgumentMatchers#nullable(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#nullable&#40;java.lang.Class&#41;).
 *
 * @param T the type of the argument matcher
 * @param clazz the class of the acceptable type
 * @return the result for executing [ArgumentMatchers#nullable(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#nullable&#40;java.lang.Class&#41;)
 */
fun <T : Any> nullable(clazz: KClass<T>): T? = ArgumentMatchers.nullable(clazz.java)

/**
 * The delegation to [ArgumentMatchers#matches(Pattern)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#matches&#40;java.util.regex.Pattern&#41;).
 *
 * @param regex the regular expression
 * @return the result for executing [ArgumentMatchers#matches(Pattern)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#matches&#40;java.util.regex.Pattern&#41;)
 */
fun matches(regex: Regex): String = ArgumentMatchers.matches(regex.toPattern())

/**
 * The delegation to [ArgumentMatchers#argThat(ArgumentMatcher)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#argThat&#40;org.mockito.ArgumentMatcher&#41;).
 *
 * @param T the type of the argument [matcher]
 * @param matcher the argument matcher
 * @return the result for executing [ArgumentMatchers#argThat(ArgumentMatcher)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#argThat&#40;org.mockito.ArgumentMatcher&#41;)
 */
inline fun <reified T : Any> argThat(matcher: ArgumentMatcher<T>): T = by(ArgumentMatchers.argThat(matcher) ?: Primitives.defaultValue(T::class.java))

/**
 * The delegation to [ArgumentMatchers#argThat(ArgumentMatcher)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#argThat&#40;org.mockito.ArgumentMatcher&#41;).
 *
 * @param T the type of the argument [matcher]
 * @param matcher the argument matcher
 * @return the result for executing [ArgumentMatchers#argThat(ArgumentMatcher)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#argThat&#40;org.mockito.ArgumentMatcher&#41;)
 */
inline fun <reified T : Any> argThat(noinline matcher: (T) -> Boolean): T = by(ArgumentMatchers.argThat(matcher) ?: Primitives.defaultValue(T::class.java))
