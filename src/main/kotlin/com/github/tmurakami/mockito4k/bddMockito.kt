package com.github.tmurakami.mockito4k

import org.mockito.BDDMockito
import org.mockito.invocation.InvocationOnMock
import kotlin.reflect.KClass

/**
 * The delegation to [BDDMockito#willThrow(Class, Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.html#willThrow&#40;java.lang.Class,&#32;java.lang.Class...&#41;).
 *
 * @param toBeThrown the class of error to be thrown when the stubbed function is called
 * @param nextToBeThrown the class of next to be thrown when the stubbed function is called
 * @return the result for executing [BDDMockito#willThrow(Class, Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.html#willThrow&#40;java.lang.Class,&#32;java.lang.Class...&#41;)
 */
fun willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())

@Deprecated("Use BDDMockito#willAnswer(Answer) directly", ReplaceWith(""))
fun willAnswer(answer: (InvocationOnMock) -> Any?): BDDMockito.BDDStubber = BDDMockito.willAnswer(answer)

@Deprecated("Use BDDMockito#will(Answer) directly", ReplaceWith(""))
fun will(answer: (InvocationOnMock) -> Any?): BDDMockito.BDDStubber = BDDMockito.will(answer)

/**
 * The delegation to [BDDMockito.BDDMyOngoingStubbing#willThrow(Class, Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.BDDMyOngoingStubbing.html#willThrow&#40;java.lang.Class,&#32;java.lang.Class...&#41;).
 *
 * @param toBeThrown the class of error to be thrown when the stubbed function is called
 * @param nextToBeThrown the class of next to be thrown when the stubbed function is called
 * @return the result for executing [BDDMockito.BDDMyOngoingStubbing#willThrow(Class, Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.BDDMyOngoingStubbing.html#willThrow&#40;java.lang.Class,&#32;java.lang.Class...&#41;)
 */
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())

/**
 * The delegation to [BDDMockito.BDDStubber#willThrow(Class, Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.BDDStubber.html#willThrow&#40;java.lang.Class,&#32;java.lang.Class...&#41;).
 *
 * @param toBeThrown the class of error to be thrown when the stubbed function is called
 * @param nextToBeThrown the class of next to be thrown when the stubbed function is called
 * @return the result for executing [BDDMockito.BDDStubber#willThrow(Class, Class...)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.BDDStubber.html#willThrow&#40;java.lang.Class,&#32;java.lang.Class...&#41;)
 */
fun BDDMockito.BDDStubber.willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())
