package com.github.tmurakami.mockito4k

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer
import org.mockito.stubbing.ValidableAnswer
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@RunWith(MockitoJUnitRunner.Strict::class)
class AnswerDelegateTest {

    @Mock(extraInterfaces = arrayOf(ValidableAnswer::class), serializable = true)
    private lateinit var answer: Answer<Any>
    @Mock(serializable = true)
    private lateinit var shouldBeValidated: InvocationOnMock.() -> Boolean
    @Mock
    private lateinit var invocation: InvocationOnMock

    @Test
    fun `AnswerDelegate should be serializable`() {
        val testTarget = AnswerDelegate(answer, shouldBeValidated)
        val bytes = ByteArrayOutputStream().apply {
            ObjectOutputStream(this).use { it.writeObject(testTarget) }
        }.toByteArray()
        val o = ObjectInputStream(ByteArrayInputStream(bytes)).use { it.readObject() }
        assertNotSame(testTarget, o)
        assertTrue(o is AnswerDelegate<*>)
    }

    @Test
    fun `answer should delegate to the given Answer`() {
        val result = Any()
        given(answer) {
            calling { answer(invocation) }.willReturn(result)
        }
        assertSame(result, AnswerDelegate(answer, shouldBeValidated).answer(invocation))
        then(shouldBeValidated).should(never()).invoke(invocation)
    }

    @Test
    fun `validateFor should delegate to the given Answer if the invocation should be validated`() {
        given(shouldBeValidated) {
            calling { invoke(invocation) }.willReturn(true)
        }
        AnswerDelegate(answer, shouldBeValidated).validateFor(invocation)
        then(answer as ValidableAnswer).should().validateFor(invocation)
    }

    @Test
    fun `validateFor should not delegate to the given Answer if the invocation should not be validated`() {
        given(shouldBeValidated) {
            calling { invoke(invocation) }.willReturn(false)
        }
        AnswerDelegate(answer, shouldBeValidated).validateFor(invocation)
        then(answer as ValidableAnswer).should(never()).validateFor(invocation)
    }

}
