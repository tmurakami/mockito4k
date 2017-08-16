package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.KThrowsException
import com.github.tmurakami.mockito4k.mock
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.then
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@RunWith(MockitoJUnitRunner.Strict::class)
class KThrowsExceptionTest {

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
        val mock = mock<InvocationOnMock>()
        KThrowsException(E()).validateFor(mock)
        then(mock).should(never()).method
    }

    private class E : Exception()

}
