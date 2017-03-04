package com.github.tmurakami.mockito4k

import org.mockito.MockSettings
import org.mockito.Mockito
import kotlin.reflect.KClass

/**
 * Creates a mock object without additional settings.
 *
 * @param T the type of mock
 * @return the mock object
 */
inline fun <reified T : Any> mock(): T = mock { }

/**
 * The delegation to [Mockito#mock(Class, MockSettings)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.MockSettings&#41;).
 *
 * @param T the type of mock
 * @return the result for executing [Mockito#mock(Class, MockSettings)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.MockSettings&#41;)
 */
inline fun <reified T : Any> mock(settings: MockSettings.() -> Unit): T = Mockito.mock(T::class.java, Mockito.withSettings().apply { settings() })

/**
 * The delegation to [Mockito#spy(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy&#40;java.lang.Class&#41;).
 *
 * @param T the type of spy
 * @return the result for executing [Mockito#spy(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy&#40;java.lang.Class&#41;)
 */
inline fun <reified T : Any> spy(): T = Mockito.spy(T::class.java)

/**
 * The delegation to [Mockito#spy(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy&#40;T&#41;).
 *
 * @param T the type of spy
 * @param instance the object to spy on
 * @return the result for executing [Mockito#spy(T)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy&#40;T&#41;)
 */
fun <T> spy(instance: T): T = Mockito.spy(instance)

@Deprecated("use 'mock(MockSettings.() -> Unit)' instead", ReplaceWith("mock<T> { this }"))
inline fun <reified T : Any> MockSettings.mock(): T = Mockito.mock(T::class.java, this)

/**
 * The delegation to [MockSettings#extraInterfaces(Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/MockSettings.html#extraInterfaces&#40;java.lang.Class...&#41;).
 *
 * @param interfaces interfaces to be implemented
 * @return the result for executing [MockSettings#extraInterfaces(Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/MockSettings.html#extraInterfaces&#40;java.lang.Class...&#41;)
 */
fun MockSettings.extraInterfaces(vararg interfaces: KClass<*>): MockSettings = extraInterfaces(*interfaces.map { it.java }.toTypedArray())
