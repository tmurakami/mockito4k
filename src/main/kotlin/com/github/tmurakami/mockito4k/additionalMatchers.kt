package com.github.tmurakami.mockito4k

import org.mockito.AdditionalMatchers

fun <T : Comparable<T>> geq(value: T): T = AdditionalMatchers.geq(value) ?: value
fun <T : Comparable<T>> leq(value: T): T = AdditionalMatchers.leq(value) ?: value

fun <T : Comparable<T>> gt(value: T): T = AdditionalMatchers.gt(value) ?: value
fun <T : Comparable<T>> lt(value: T): T = AdditionalMatchers.lt(value) ?: value

fun <T : Comparable<T>> cmpEq(value: T): T = AdditionalMatchers.cmpEq(value) ?: value

fun find(regex: String): String = AdditionalMatchers.find(regex) ?: regex

fun aryEq(value: BooleanArray): BooleanArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: ByteArray): ByteArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: CharArray): CharArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: DoubleArray): DoubleArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: FloatArray): FloatArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: IntArray): IntArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: LongArray): LongArray = AdditionalMatchers.aryEq(value) ?: value
fun aryEq(value: ShortArray): ShortArray = AdditionalMatchers.aryEq(value) ?: value
fun <T> aryEq(value: Array<T>): Array<T> = AdditionalMatchers.aryEq(value) ?: value

fun <T> and(first: T, second: T): T = AdditionalMatchers.and(first, second) ?: first
fun <T> or(first: T, second: T): T = AdditionalMatchers.or(first, second) ?: first

fun <T> not(first: T): T = AdditionalMatchers.not(first) ?: first

fun eq(value: Double, delta: Double): Double = AdditionalMatchers.eq(value, delta)
fun eq(value: Float, delta: Float): Float = AdditionalMatchers.eq(value, delta)
