@file:Suppress("NOTHING_TO_INLINE")

package com.github.tmurakami.mockito4k

import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.internal.util.Primitives
import java.util.regex.Pattern

/**
 * Prevents causing NullPointerException when using a [matcher] for method that only accepts non-null parameter.
 */
@Suppress("UNCHECKED_CAST")
inline fun <T> by(matcher: T?): T = matcher as T

/**
 * The delegation to [ArgumentMatchers.any].
 */
inline fun <reified T> anyNullable(): T? = ArgumentMatchers.any()

/**
 * The delegation to [ArgumentMatchers.any].
 */
inline fun <reified T : Any> any(): T = by(ArgumentMatchers.any(T::class.java))

/**
 * The delegation to [ArgumentMatchers.eq].
 */
inline fun <reified T> eq(value: T): T = ArgumentMatchers.eq(value) ?: value

/**
 * The delegation to [ArgumentMatchers.refEq].
 */
inline fun <reified T> refEq(value: T, vararg excludeFields: String): T =
    ArgumentMatchers.refEq(value, *excludeFields) ?: value

/**
 * The delegation to [ArgumentMatchers.same].
 */
inline fun <reified T> same(value: T): T = ArgumentMatchers.same(value) ?: value

/**
 * The delegation to [ArgumentMatchers.isA].
 */
inline fun <reified T : Any> isA(): T = by(ArgumentMatchers.isA(T::class.java))

/**
 * The delegation to [ArgumentMatchers.isNull].
 */
inline fun <reified T> isNull(): T? = ArgumentMatchers.isNull()

/**
 * The delegation to [ArgumentMatchers.isNotNull].
 */
inline fun <reified T> isNotNull(): T? = ArgumentMatchers.isNotNull()

/**
 * The delegation to [ArgumentMatchers.nullable].
 */
inline fun <reified T : Any> nullable(): T? = ArgumentMatchers.nullable(T::class.java)

/**
 * The delegation to [ArgumentMatchers.matches].
 */
inline fun matches(regex: String): String = ArgumentMatchers.matches(regex)

/**
 * The delegation to [ArgumentMatchers.matches].
 */
inline fun matches(regex: Regex): String = matches(regex.toPattern())

/**
 * The delegation to [ArgumentMatchers.matches].
 */
inline fun matches(pattern: Pattern): String = ArgumentMatchers.matches(pattern)

/**
 * The delegation to [ArgumentMatchers.argThat].
 */
inline fun <reified T : Any> argThat(matcher: ArgumentMatcher<T>): T =
    ArgumentMatchers.argThat(matcher) ?: by(Primitives.defaultValue(T::class.java))

/**
 * The delegation to [ArgumentMatchers.argThat].
 */
inline fun <reified T : Any> argThat(noinline matcher: (T) -> Boolean): T =
    ArgumentMatchers.argThat(matcher) ?: by(Primitives.defaultValue(T::class.java))
