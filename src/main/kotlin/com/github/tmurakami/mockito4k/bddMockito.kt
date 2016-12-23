package com.github.tmurakami.mockito4k

import org.mockito.BDDMockito
import org.mockito.invocation.InvocationOnMock
import kotlin.reflect.KClass

fun <T> given(methodCall: T): BDDMockito.BDDMyOngoingStubbing<T> = BDDMockito.given(methodCall)

fun <T> then(mock: T): BDDMockito.Then<T> = BDDMockito.then(mock)

fun willThrow(vararg toBeThrown: Throwable): BDDMockito.BDDStubber = BDDMockito.willThrow(*toBeThrown)
fun willThrow(toBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java)
fun willThrow(toBeThrown: Class<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown)
fun willThrow(toBeThrown: KClass<out Throwable>, vararg throwableTypes: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java, *throwableTypes.map { it.java }.toTypedArray())
fun willThrow(toBeThrown: Class<out Throwable>, vararg throwableTypes: Class<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown, *throwableTypes)

fun willAnswer(answer: InvocationOnMock.() -> Any?): BDDMockito.BDDStubber = BDDMockito.willAnswer(answer)
fun will(answer: InvocationOnMock.() -> Any?): BDDMockito.BDDStubber = BDDMockito.will(answer)

fun willDoNothing(): BDDMockito.BDDStubber = BDDMockito.willDoNothing()

fun willReturn(toBeReturned: Any?): BDDMockito.BDDStubber = BDDMockito.willReturn(toBeReturned)
fun willReturn(toBeReturned: Any?, vararg toBeReturnedNext: Any?): BDDMockito.BDDStubber = BDDMockito.willReturn(toBeReturned, *toBeReturnedNext)

fun willCallRealMethod(): BDDMockito.BDDStubber = BDDMockito.willCallRealMethod()

fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(throwableType: KClass<out Throwable>): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(throwableType.java)
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(throwableType: KClass<out Throwable>, vararg throwableTypes: KClass<out Throwable>): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(throwableType.java, *throwableTypes.map { it.java }.toTypedArray())

fun BDDMockito.BDDStubber.willThrow(toBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java)
fun BDDMockito.BDDStubber.willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())
