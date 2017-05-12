package com.github.tmurakami.mockito4k

import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.internal.stubbing.answers.ThrowsException
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.mockito.stubbing.Stubber
import kotlin.reflect.KClass

@Deprecated("Use 'given(T, BDDStubbing<T>.() -> Unit)' for stubbing", ReplaceWith(""))
fun willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = BDDMockito.willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())

@Deprecated("Use 'given(T, BDDStubbing<T>.() -> Unit)' for stubbing", ReplaceWith(""))
fun willAnswer(answer: (InvocationOnMock) -> Any?): BDDMockito.BDDStubber = BDDMockito.willAnswer(answer)

@Deprecated("Use 'given(T, BDDStubbing<T>.() -> Unit)' for stubbing", ReplaceWith(""))
fun will(answer: (InvocationOnMock) -> Any?): BDDMockito.BDDStubber = BDDMockito.will(answer)

@Deprecated("Use 'given(T, BDDStubbing<T>.() -> Unit)' for stubbing", ReplaceWith(""))
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())

@Deprecated("Use 'given(T, BDDStubbing<T>.() -> Unit)' for stubbing", ReplaceWith(""))
fun BDDMockito.BDDStubber.willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDMockito.BDDStubber = willThrow(toBeThrown.java, *nextToBeThrown.map { it.java }.toTypedArray())

/**
 * Sets the expected behavior for the given [mock] object.
 *
 * @param T the type of the given [mock]
 * @param mock the mock object you want to stub
 * @param settings the stubbing settings
 * @return the given [mock] object
 */
fun <T> given(mock: T, settings: BDDStubbingSettings<T>.() -> Unit): T = mock.apply {
    var pending: Pair<T.() -> Any?, BDDOngoingStubbingImpl<*>>? = null
    val finishStubbing: Pair<T.() -> Any?, BDDOngoingStubbingImpl<*>>.() -> Unit = { second.stubber?.`when`(mock)?.let { first(mock) } }
    object : BDDStubbingSettings<T> {
        override fun <R> calling(function: T.() -> R): BDDOngoingStubbing<R> {
            pending?.finishStubbing()
            return BDDOngoingStubbingImpl<R>().apply { pending = function to this }
        }
    }.settings()
    pending?.finishStubbing()
}

/**
 * The settings for stubbing.
 *
 * @param T the type of a mock
 */
interface BDDStubbingSettings<out T> {

    /**
     * Enables stubbing function.
     *
     * @param R the return type of the given [function]
     * @param function the function you want to stub
     * @return the fluent object to stub
     */
    fun <R> calling(function: T.() -> R): BDDOngoingStubbing<R>

    @Deprecated("Use calling(T.() -> R)", ReplaceWith("calling(function)"))
    fun <R> running(function: T.() -> R): BDDOngoingStubbing<R> = calling(function)

}

/**
 * The fluent API for stubbing.
 *
 * @param R the return type of the function you want to stub
 */
interface BDDOngoingStubbing<R> {

    /**
     * Sets to call the given [answer] when the function is called.
     *
     * @param answer the answer to be called
     * @return this object
     */
    fun will(answer: Answer<R>): BDDOngoingStubbing<R>

    /**
     * Sets to call the given [answer] when the function is called.
     *
     * @param answer the answer to be called
     * @return this object
     */
    fun will(answer: (InvocationOnMock) -> R): BDDOngoingStubbing<R>

    /**
     * Sets to call the given [answer] when the function is called.
     *
     * @param answer the answer to be called
     * @return this object
     */
    fun willAnswer(answer: Answer<R>): BDDOngoingStubbing<R>

    /**
     * Sets to call the given [answer] when the function is called.
     *
     * @param answer the answer to be called
     * @return this object
     */
    fun willAnswer(answer: (InvocationOnMock) -> R): BDDOngoingStubbing<R>

    /**
     * Sets to call the actual function when the function is called.
     *
     * @return this object
     */
    fun willCallRealMethod(): BDDOngoingStubbing<R>

    /**
     * Sets to return values to be returned when the function is called.
     *
     * @param value the value to be returned
     * @param values the next value to be returned
     * @return this object
     */
    fun willReturn(value: R, vararg values: R): BDDOngoingStubbing<R>

    /**
     * Sets to throw errors when the function is called.
     *
     * @param toBeThrown the error to be thrown
     * @param nextToBeThrown the next error to be thrown
     * @return this object
     */
    fun willThrow(toBeThrown: Throwable, vararg nextToBeThrown: Throwable): BDDOngoingStubbing<R>

    /**
     * Sets to throw errors to be thrown when the function is called.
     *
     * @param toBeThrown the type of the error to be thrown
     * @param nextToBeThrown the type of the next error to be thrown
     * @return this object
     */
    fun willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDOngoingStubbing<R>

}

private class BDDOngoingStubbingImpl<R> : BDDOngoingStubbing<R> {

    companion object {
        val UNIT: (InvocationOnMock) -> Any? = { Unit }
    }

    var stubber: Stubber? = null
        private set

    override fun will(answer: Answer<R>): BDDOngoingStubbing<R> = willAnswer(answer)

    override fun will(answer: (InvocationOnMock) -> R): BDDOngoingStubbing<R> = willAnswer(answer)

    override fun willAnswer(answer: Answer<R>): BDDOngoingStubbing<R> = apply {
        stubber = stubber?.doAnswer(answer) ?: Mockito.doAnswer(answer)
    }

    override fun willAnswer(answer: (InvocationOnMock) -> R): BDDOngoingStubbing<R> = apply {
        stubber = stubber?.doAnswer(answer) ?: Mockito.doAnswer(answer)
    }

    override fun willCallRealMethod(): BDDOngoingStubbing<R> = apply {
        stubber = stubber?.doCallRealMethod() ?: Mockito.doCallRealMethod()
    }

    override fun willReturn(value: R, vararg values: R): BDDOngoingStubbing<R> = apply {
        stubber = arrayListOf(value, *values).fold(stubber) { s, v ->
            if (v === Unit) s?.doAnswer(UNIT) ?: Mockito.doAnswer(UNIT) else s?.doReturn(v) ?: Mockito.doReturn(v)
        }
    }

    override fun willThrow(toBeThrown: Throwable, vararg nextToBeThrown: Throwable): BDDOngoingStubbing<R> = apply {
        stubber = arrayOf(toBeThrown, *nextToBeThrown).fold(stubber) { s, v ->
            object : ThrowsException(v) {
                // Kotlin has no checked exception.
                override fun validateFor(invocation: InvocationOnMock?): Unit = Unit
            }.let { s?.doAnswer(it) ?: Mockito.doAnswer(it) }
        }
    }

    override fun willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDOngoingStubbing<R> = apply {
        stubber = toBeThrown.java.let { stubber?.doThrow(it) ?: Mockito.doThrow(it) }.let { nextToBeThrown.fold(it) { s, c -> s.doThrow(c.java) } }
    }

}
