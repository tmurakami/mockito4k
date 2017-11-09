package com.github.tmurakami.mockito4k

import org.mockito.Mockito
import org.mockito.internal.stubbing.answers.Returns
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
fun <T : Any> given(mock: T, settings: BDDStubbingSettings<T>.() -> Unit): T =
    mock.apply { filterStackTrace { BDDStubbingSettingsImpl(mock).apply(settings).finishStubbing() } }

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
    fun willThrow(toBeThrown: KClass<out Throwable>,
                  vararg nextToBeThrown: KClass<out Throwable>): BDDOngoingStubbing<R>

}

private class BDDStubbingSettingsImpl<out T : Any>(private val mock: T) : BDDStubbingSettings<T> {

    private var current: InternalStubbing? = null

    fun finishStubbing() {
        current?.finish()
    }

    override fun <R> calling(function: T.() -> R): BDDOngoingStubbing<R> {
        finishStubbing()
        val f: T.() -> Any? = {
            try {
                function()
            } catch (e: NullPointerException) {
                // An NPE is thrown when the MockHandler returns null for a mocked lambda expression expecting a
                // primitive return value. Therefore, we ignore this error if the mock is a `Function<*>` object.
                if (this is Function<*>) null else throw e
            }
        }
        current = if (Mockito.mockingDetails(mock).isSpy) SpiedStubbing(mock, f) else MockStubbing(mock, f)
        return BDDOngoingStubbingImpl(current!!)
    }

}

private interface InternalStubbing {
    fun finish()
    operator fun plusAssign(answer: Answer<*>)
}

private class MockStubbing<T : Any>(mock: T, function: T.() -> Any?) : InternalStubbing {

    private var stubbing = Mockito.`when`(mock.function())

    override fun finish() = Unit

    override fun plusAssign(answer: Answer<*>) {
        stubbing = stubbing.thenAnswer(answer)
    }

}

private class SpiedStubbing<T : Any>(private val spied: T, private val function: T.() -> Any?) : InternalStubbing {

    private var stubbing: Stubber? = null

    override fun finish() {
        stubbing?.`when`(spied)?.function()
    }

    override fun plusAssign(answer: Answer<*>) {
        stubbing = stubbing?.doAnswer(answer) ?: Mockito.doAnswer(answer)
    }

}

private class BDDOngoingStubbingImpl<R>(private val stubbing: InternalStubbing) : BDDOngoingStubbing<R> {

    override fun will(answer: Answer<R>): BDDOngoingStubbing<R> = apply { stubbing += answer }

    override fun will(answer: (InvocationOnMock) -> R): BDDOngoingStubbing<R> = will(Answer { answer(it) })

    override fun willAnswer(answer: Answer<R>): BDDOngoingStubbing<R> = will(answer)

    override fun willAnswer(answer: (InvocationOnMock) -> R): BDDOngoingStubbing<R> = will(answer)

    override fun willCallRealMethod(): BDDOngoingStubbing<R> = apply { stubbing += KCallsRealMethods }

    override fun willReturn(value: R, vararg values: R): BDDOngoingStubbing<R> = apply {
        thenReturn(value)
        for (v in values) thenReturn(v)
    }

    override fun willThrow(toBeThrown: Throwable, vararg nextToBeThrown: Throwable): BDDOngoingStubbing<R> = apply {
        thenThrow(toBeThrown)
        for (t in nextToBeThrown) thenThrow(t)
    }

    override fun willThrow(toBeThrown: KClass<out Throwable>,
                           vararg nextToBeThrown: KClass<out Throwable>): BDDOngoingStubbing<R> = apply {
        thenThrow(toBeThrown)
        for (c in nextToBeThrown) thenThrow(c)
    }

    private fun thenReturn(value: R) {
        stubbing += if (value === Unit) Answer { Unit } else Returns(value)
    }

    private fun thenThrow(toBeThrown: Throwable) {
        stubbing += KThrowsException(toBeThrown)
    }

    private fun thenThrow(toBeThrown: KClass<out Throwable>) = thenThrow(ObjenesisHelper.newInstance(toBeThrown.java))

}
