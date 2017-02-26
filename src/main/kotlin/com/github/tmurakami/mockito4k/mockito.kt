package com.github.tmurakami.mockito4k

import org.mockito.MockSettings
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import kotlin.reflect.KClass

/**
 * The delegation to [Mockito#mock(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class&#41;).
 *
 * @param T the type of mock
 * @return the result for executing [Mockito#mock(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class&#41;)
 */
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

/**
 * The delegation to [Mockito#mock(Class, String)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;java.lang.String&#41;).
 *
 * @param T the type of mock
 * @param name the name of mock
 * @return the result for executing [Mockito#mock(Class, String)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;java.lang.String&#41;)
 */
inline fun <reified T : Any> mock(name: String): T = Mockito.mock(T::class.java, name)

/**
 * The delegation to [Mockito#mock(Class, org.mockito.stubbing.Answer)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.stubbing.Answer&#41;).
 *
 * @param T the type of mock
 * @param defaultAnswer the default answer for unstubbed functions
 * @return the result for executing [Mockito#mock(Class, org.mockito.stubbing.Answer)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.stubbing.Answer&#41;)
 */
inline fun <reified T : Any> mock(defaultAnswer: Answer<*>): T = Mockito.mock(T::class.java, defaultAnswer)

/**
 * The delegation to [Mockito#mock(Class, org.mockito.stubbing.Answer)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.stubbing.Answer&#41;).
 *
 * @param T the type of mock
 * @param defaultAnswer the default answer for unstubbed functions
 * @return the result for executing [Mockito#mock(Class, org.mockito.stubbing.Answer)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.stubbing.Answer&#41;)
 */
inline fun <reified T : Any> mock(noinline defaultAnswer: (InvocationOnMock) -> Any?): T = Mockito.mock(T::class.java, defaultAnswer)

/**
 * The delegation to [Mockito#mock(Class, org.mockito.MockSettings)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.MockSettings&#41;).
 *
 * @param T the type of mock
 * @param mockSettings the additional settings for making mock
 * @return the result for executing [Mockito#mock(Class, org.mockito.MockSettings)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#mock&#40;java.lang.Class,&#32;org.mockito.MockSettings&#41;)
 */
inline fun <reified T : Any> mock(mockSettings: MockSettings): T = Mockito.mock(T::class.java, mockSettings)

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

/**
 * The delegation to [MockSettings#extraInterfaces(Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/MockSettings.html#extraInterfaces&#40;java.lang.Class...&#41;).
 *
 * @param interfaces interfaces to be implemented
 * @return the result for executing [MockSettings#extraInterfaces(Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/MockSettings.html#extraInterfaces&#40;java.lang.Class...&#41;)
 */
fun MockSettings.extraInterfaces(vararg interfaces: KClass<*>): MockSettings = extraInterfaces(*interfaces.map { it.java }.toTypedArray())
