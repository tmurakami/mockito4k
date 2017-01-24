package com.github.tmurakami.mockito4k

import org.mockito.BDDMockito
import org.mockito.invocation.InvocationOnMock
import kotlin.reflect.KClass

fun willThrow(toBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown.java)
fun willThrow(toBeThrown: KClass<out Throwable>, vararg throwableTypes: KClass<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown.java, *throwableTypes.map { it.java }.toTypedArray())

fun willAnswer(answer: (InvocationOnMock) -> Any?): BDDMockito.BDDStubber = BDDMockito.willAnswer(answer)
fun will(answer: (InvocationOnMock) -> Any?): BDDMockito.BDDStubber = BDDMockito.will(answer)

fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(throwableType: KClass<out Throwable>): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(throwableType.java)
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(throwableType: KClass<out Throwable>, vararg throwableTypes: KClass<out Throwable>): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(throwableType.java, *throwableTypes.map { it.java }.toTypedArray())

fun BDDMockito.BDDStubber.willThrow(toBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java)
fun BDDMockito.BDDStubber.willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())
