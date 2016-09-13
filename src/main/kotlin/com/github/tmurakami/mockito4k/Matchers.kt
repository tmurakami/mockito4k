package com.github.tmurakami.mockito4k

import org.mockito.ArgumentMatcher
import org.mockito.Matchers

inline fun <reified T : Any> any(): T = Matchers.any() ?: _Class.defaultValue(T::class.java)
inline fun <reified T : Any> anyVararg(): T = Matchers.anyVararg() ?: _Class.defaultValue(T::class.java)

fun <T> isA(clazz: Class<T>): T = Matchers.isA(clazz) ?: _Class.defaultValue(clazz)

fun <T> eq(value: T): T = Matchers.eq(value) ?: value
fun <T> refEq(value: T, vararg excludeFields: String): T = Matchers.refEq(value, *excludeFields) ?: value
fun <T> same(value: T): T = Matchers.same(value) ?: value

inline fun <reified T : Any> isNull(): T? = Matchers.isNull(T::class.java)
inline fun <reified T : Any> isNotNull(): T? = Matchers.isNotNull(T::class.java)

fun startsWith(prefix: String): String = Matchers.startsWith(prefix)
fun contains(substring: String): String = Matchers.contains(substring)
fun endsWith(suffix: String): String = Matchers.endsWith(suffix)
fun matches(regex: String): String = Matchers.matches(regex)

inline fun <reified T : Any> argThat(crossinline matcher: Any?.() -> Boolean): T = Matchers.argThat(object : ArgumentMatcher<T>() {
    override fun matches(argument: Any?): Boolean = argument.matcher()
}) ?: _Class.defaultValue(T::class.java)
