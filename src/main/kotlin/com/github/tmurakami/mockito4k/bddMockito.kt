package com.github.tmurakami.mockito4k

import org.mockito.Answers
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.mockito.stubbing.Stubber
import org.objenesis.ObjenesisHelper
import kotlin.reflect.KClass

/**
 * Sets the expected behavior for the given [mock] object.
 *
 * @param T the type of the given [mock]
 * @param mock the mock object you want to stub
 * @param settings the stubbing settings
 * @return the given [mock] object
 */
fun <T : Any> given(mock: T, settings: BDDStubbingSettings<T>.() -> Unit): T = mock.apply {
    var pending: Pair<BDDOngoingStubbingImpl<*>, T.() -> Any?>? = null
    fun Pair<BDDOngoingStubbingImpl<*>, T.() -> Any?>.finishStubbing(t: T) {
        first.stubber?.`when`(t) ?: return
        try {
            second(t)
        } catch (e: NullPointerException) {
            // An NPE is thrown when the MockHandler returns null for a mocked lambda expression expecting a primitive
            // return value. Therefore, we ignore this error if the mock is a `Function<*>` object.
            if (t !is Function<*>) throw e
        }
    }
    try {
        object : BDDStubbingSettings<T> {
            override fun <R> calling(function: T.() -> R): BDDOngoingStubbing<R> {
                pending?.finishStubbing(this@apply)
                return BDDOngoingStubbingImpl<R>().apply { pending = this to function }
            }
        }.settings()
        pending?.finishStubbing(this)
    } catch (e: Exception) {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        (e as java.lang.Throwable).run {
            // We do not use the `StackTraceCleaner` extension because multiple StackTraceCleaners are not allowed. If
            // we used the extension, the cleaner would not work with a library that has its own StackTraceCleaner
            // (e.g. dexmaker-mockito).
            stackTrace = stackTrace.filterNot { it.className.startsWith("com.github.tmurakami.mockito4k.") }.toTypedArray()
        }
        throw e
    }
}

/**
 * The settings for stubbing.
 *
 * @param T the type of a mock
 */
interface BDDStubbingSettings<out T : Any> {

    /**
     * Enables stubbing function.
     *
     * @param R the return type of the given [function]
     * @param function the function you want to stub
     * @return the fluent object to stub
     */
    fun <R> calling(function: T.() -> R): BDDOngoingStubbing<R>

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

    internal var stubber: Stubber? = null
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
        stubber = stubber?.doAnswer(Answers.CALLS_REAL_METHODS) ?: Mockito.doAnswer(Answers.CALLS_REAL_METHODS)
    }

    override fun willReturn(value: R, vararg values: R): BDDOngoingStubbing<R> = apply {
        stubber = arrayListOf(value, *values).fold(stubber) { s, v ->
            // We use `doAnswer` for `Unit` because `doReturn` cannot be used for void methods and `doNothing` cannot for non-void methods.
            if (v === Unit) Answer { Unit as Any }.let { s?.doAnswer(it) ?: Mockito.doAnswer(it) } else s?.doReturn(v) ?: Mockito.doReturn(v)
        }
    }

    override fun willThrow(toBeThrown: Throwable, vararg nextToBeThrown: Throwable): BDDOngoingStubbing<R> = apply {
        stubber = arrayOf(toBeThrown, *nextToBeThrown).fold(stubber) { s, t ->
            KThrowsException(t).let { s?.doAnswer(it) ?: Mockito.doAnswer(it) }
        }
    }

    override fun willThrow(toBeThrown: KClass<out Throwable>, vararg nextToBeThrown: KClass<out Throwable>): BDDOngoingStubbing<R> =
        willThrow(ObjenesisHelper.newInstance(toBeThrown.java), *nextToBeThrown.map { ObjenesisHelper.newInstance(it.java) }.toTypedArray())

}
