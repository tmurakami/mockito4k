@file:Suppress("NOTHING_TO_INLINE")

package com.github.tmurakami.mockito4k

import org.mockito.AdditionalMatchers

/**
 * The delegation to [AdditionalMatchers.and].
 */
inline fun <reified T> and(first: T?, second: T?): T = AdditionalMatchers.and(first, second) ?: first ?: by(second)

/**
 * The delegation to [AdditionalMatchers.or].
 */
inline fun <reified T> or(first: T?, second: T?): T = AdditionalMatchers.or(first, second) ?: first ?: by(second)

/**
 * The delegation to [AdditionalMatchers.not].
 */
inline fun <reified T> not(matcher: T?): T = AdditionalMatchers.not(matcher) ?: by(matcher)

/**
 * The delegation to [AdditionalMatchers.geq].
 */
inline fun <reified T : Comparable<T>> geq(value: T): T = AdditionalMatchers.geq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.gt].
 */
inline fun <reified T : Comparable<T>> gt(value: T): T = AdditionalMatchers.gt(value) ?: value

/**
 * The delegation to [AdditionalMatchers.leq].
 */
inline fun <reified T : Comparable<T>> leq(value: T): T = AdditionalMatchers.leq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.lt].
 */
inline fun <reified T : Comparable<T>> lt(value: T): T = AdditionalMatchers.lt(value) ?: value

/**
 * The delegation to [AdditionalMatchers.cmpEq].
 */
inline fun <reified T : Comparable<T>> cmpEq(value: T): T = AdditionalMatchers.cmpEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.find].
 */
inline fun find(regex: String): String = AdditionalMatchers.find(regex) ?: regex

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: BooleanArray): BooleanArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: ByteArray): ByteArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: CharArray): CharArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: DoubleArray): DoubleArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: FloatArray): FloatArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: IntArray): IntArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: LongArray): LongArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun aryEq(value: ShortArray): ShortArray = AdditionalMatchers.aryEq(value) ?: value

/**
 * The delegation to [AdditionalMatchers.aryEq].
 */
inline fun <reified T> aryEq(value: Array<T>): Array<T> = AdditionalMatchers.aryEq(value) ?: value
