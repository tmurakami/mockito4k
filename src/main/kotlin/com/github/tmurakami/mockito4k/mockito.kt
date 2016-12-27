package com.github.tmurakami.mockito4k

import org.mockito.*
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.mockito.verification.VerificationAfterDelay
import org.mockito.verification.VerificationMode
import org.mockito.verification.VerificationWithTimeout
import kotlin.reflect.KClass

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
inline fun <reified T : Any> mock(name: String): T = Mockito.mock(T::class.java, name)
inline fun <reified T : Any> mock(defaultAnswer: Answer<*>): T = Mockito.mock(T::class.java, defaultAnswer)
inline fun <reified T : Any> mock(noinline defaultAnswer: InvocationOnMock.() -> Any?): T = Mockito.mock(T::class.java, defaultAnswer)
inline fun <reified T : Any> mock(mockSettings: MockSettings): T = Mockito.mock(T::class.java, mockSettings)

@Incubating
inline fun <reified T : Any> spy(): T = Mockito.spy(T::class.java)

fun <T> spy(instance: T): T = Mockito.spy(instance)

fun mockingDetails(toInspect: Any): MockingDetails = Mockito.mockingDetails(toInspect)

fun <T> reset(vararg mocks: T) = Mockito.reset(*mocks)
fun <T> clearInvocations(vararg mocks: T) = Mockito.clearInvocations(*mocks)

fun verifyNoMoreInteractions(vararg mocks: Any) = Mockito.verifyNoMoreInteractions(*mocks)
fun verifyZeroInteractions(vararg mocks: Any) = Mockito.verifyZeroInteractions(*mocks)

fun inOrder(vararg mocks: Any): InOrder = Mockito.inOrder(*mocks)
fun ignoreStubs(vararg mocks: Any): Array<Any> = Mockito.ignoreStubs(*mocks)

fun times(wantedNumberOfInvocations: Int): VerificationMode = Mockito.times(wantedNumberOfInvocations)
fun never(): VerificationMode = Mockito.never()
fun atLeastOnce(): VerificationMode = Mockito.atLeastOnce()
fun atLeast(minNumberOfInvocations: Int): VerificationMode = Mockito.atLeast(minNumberOfInvocations)
fun atMost(maxNumberOfInvocations: Int): VerificationMode = Mockito.atMost(maxNumberOfInvocations)
fun calls(wantedNumberOfInvocations: Int): VerificationMode = Mockito.calls(wantedNumberOfInvocations)
fun only(): VerificationMode = Mockito.only()

fun timeout(millis: Long): VerificationWithTimeout = Mockito.timeout(millis)
fun after(millis: Long): VerificationAfterDelay = Mockito.after(millis)

fun validateMockitoUsage() = Mockito.validateMockitoUsage()

fun withSettings(): MockSettings = Mockito.withSettings()

fun description(description: String): VerificationMode = Mockito.description(description)

@Incubating
fun framework(): MockitoFramework = Mockito.framework()

fun MockSettings.extraInterfaces(vararg interfaces: KClass<*>): MockSettings = extraInterfaces(*interfaces.map { it.java }.toTypedArray())
