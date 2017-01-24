package com.github.tmurakami.mockito4k

import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import kotlin.reflect.KClass

fun <T> by(matcher: T?): T = matcher ?: Fake.nonNull()

fun <T> any(): T = ArgumentMatchers.any()

fun <T> eq(value: T): T = ArgumentMatchers.eq(value)
fun <T> same(value: T): T = ArgumentMatchers.same(value)

fun <T : Any> isA(clazz: KClass<T>): T = isA(clazz.java)
fun <T> isA(clazz: Class<T>): T = ArgumentMatchers.isA(clazz)

fun <T> isNull(): T? = ArgumentMatchers.isNull()
fun <T> isNotNull(): T? = ArgumentMatchers.isNotNull()

inline fun <reified T : Any> nullable(): T? = ArgumentMatchers.nullable(T::class.java)

fun matches(regex: Regex): String = ArgumentMatchers.matches(regex.toPattern())

fun <T> argThat(matcher: ArgumentMatcher<T>): T = ArgumentMatchers.argThat(matcher)
fun <T> argThat(matcher: (T) -> Boolean): T = ArgumentMatchers.argThat(matcher)
