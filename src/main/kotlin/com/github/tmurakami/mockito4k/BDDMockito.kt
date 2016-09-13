package com.github.tmurakami.mockito4k

import org.mockito.BDDMockito
import org.mockito.invocation.InvocationOnMock

fun <T> given(methodCall: T): BDDMockito.BDDMyOngoingStubbing<T> = BDDMockito.given(methodCall)

fun <T> then(mock: T): BDDMockito.Then<T> = BDDMockito.then(mock)

fun willThrow(toBeThrown: Throwable): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown)
fun willThrow(toBeThrown: Class<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown)

fun willAnswer(answer: InvocationOnMock.() -> Any?): BDDMockito.BDDStubber = BDDMockito.willAnswer(answer)

fun willDoNothing(): BDDMockito.BDDStubber = BDDMockito.willDoNothing()

fun willReturn(toBeReturned: Any?): BDDMockito.BDDStubber = BDDMockito.willReturn(toBeReturned)

fun willCallRealMethod(): BDDMockito.BDDStubber = BDDMockito.willCallRealMethod()
