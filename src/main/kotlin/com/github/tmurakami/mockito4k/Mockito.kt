package com.github.tmurakami.mockito4k

import org.mockito.MockSettings
import org.mockito.Mockito
import kotlin.reflect.KClass

/**
 * The delegation to [Mockito.mock].
 */
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

/**
 * The delegation to [Mockito.mock].
 */
inline fun <reified T : Any> mock(settings: MockSettings.() -> Unit): T =
    Mockito.mock(T::class.java, Mockito.withSettings().apply { settings() })

/**
 * The delegation to [Mockito.spy].
 */
inline fun <reified T : Any> spy(): T = Mockito.spy(T::class.java)

/**
 * The delegation to [Mockito.spy].
 */
inline fun <reified T : Any> spy(instance: T): T = Mockito.spy(instance)

/**
 * The delegation to [MockSettings.extraInterfaces].
 */
@Suppress("NOTHING_TO_INLINE")
inline fun MockSettings.extraInterfaces(vararg interfaces: KClass<*>): MockSettings =
    extraInterfaces(*interfaces.map { it.java }.toTypedArray())
