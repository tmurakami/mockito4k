package com.github.tmurakami.mockito4k

import org.mockito.MockSettings
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import kotlin.reflect.KClass

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
inline fun <reified T : Any> mock(name: String): T = Mockito.mock(T::class.java, name)
inline fun <reified T : Any> mock(defaultAnswer: Answer<*>): T = Mockito.mock(T::class.java, defaultAnswer)
inline fun <reified T : Any> mock(noinline defaultAnswer: (InvocationOnMock) -> Any?): T = Mockito.mock(T::class.java, defaultAnswer)
inline fun <reified T : Any> mock(mockSettings: MockSettings): T = Mockito.mock(T::class.java, mockSettings)

inline fun <reified T : Any> spy(): T = Mockito.spy(T::class.java)
fun <T> spy(instance: T): T = Mockito.spy(instance)

fun MockSettings.extraInterfaces(vararg interfaces: KClass<*>): MockSettings = extraInterfaces(*interfaces.map { it.java }.toTypedArray())
