package com.github.tmurakami.mockito4k

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@RunWith(MockitoJUnitRunner.Strict::class)
class KThrowsExceptionTest {

    @Mock
    private lateinit var invocation: InvocationOnMock
    @Mock
    private lateinit var kotlinMock: KotlinClass
    @Mock
    private lateinit var javaMock: JavaClass

    @Test
    fun `KThrowsException should be serializable`() {
        val testTarget = KThrowsException(E())
        val bytes = ByteArrayOutputStream().apply {
            ObjectOutputStream(this).use { it.writeObject(testTarget) }
        }.toByteArray()
        val o = ObjectInputStream(ByteArrayInputStream(bytes)).use { it.readObject() }
        assertNotSame(testTarget, o)
        assertTrue(o is KThrowsException)
    }

    @Test
    fun `validateFor should not check the method signature if the invocation is for Kotlin`() {
        given(invocation) {
            calling { mock }.willReturn(kotlinMock)
        }
        KThrowsException(E()).validateFor(invocation)
        then(invocation).should(never()).method
    }

    @Test(expected = MockitoException::class)
    fun `validateFor should throw MockitoException if the given checked exception does not match the Java method signature`() {
        given(invocation) {
            calling { mock }.willReturn(javaMock)
            calling { method }.willReturn(javaMock.javaClass.getDeclaredMethod("getS"))
        }
        KThrowsException(E()).validateFor(invocation)
    }

    private open class KotlinClass

    private class E : Exception()

}
