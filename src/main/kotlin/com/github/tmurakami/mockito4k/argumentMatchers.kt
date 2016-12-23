package com.github.tmurakami.mockito4k

import org.mockito.ArgumentMatchers
import java.util.regex.Pattern
import kotlin.reflect.KClass

inline fun <reified T : Any> any(): T = ArgumentMatchers.any() ?: _Class.defaultValue(T::class.java)

fun <T : Any> isA(clazz: KClass<T>): T = isA(clazz.java)
fun <T> isA(clazz: Class<T>): T = ArgumentMatchers.isA(clazz) ?: _Class.defaultValue(clazz)

fun <T> eq(value: T): T = ArgumentMatchers.eq(value) ?: value
fun <T> refEq(value: T, vararg excludeFields: String): T = ArgumentMatchers.refEq(value, *excludeFields) ?: value
fun <T> same(value: T): T = ArgumentMatchers.same(value) ?: value

fun <T> isNull(): T? = ArgumentMatchers.isNull()
fun <T> isNotNull(): T? = ArgumentMatchers.isNotNull()

inline fun <reified T : Any> nullable(): T? = ArgumentMatchers.nullable(T::class.java)

fun startsWith(prefix: String): String = ArgumentMatchers.startsWith(prefix)
fun contains(substring: String): String = ArgumentMatchers.contains(substring)
fun endsWith(suffix: String): String = ArgumentMatchers.endsWith(suffix)
fun matches(regex: String): String = ArgumentMatchers.matches(regex)

fun matches(regex: Regex): String = matches(regex.toPattern())
fun matches(pattern: Pattern): String = ArgumentMatchers.matches(pattern)

inline fun <reified T : Any> argThat(noinline matcher: T.() -> Boolean): T = ArgumentMatchers.argThat(matcher) ?: _Class.defaultValue(T::class.java)
