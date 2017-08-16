package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.KThrowsException
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.then
import org.mockito.Mock
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
    fun `validateFor should not check the method signature`() {
        KThrowsException(E()).validateFor(invocation)
        then(invocation).should(never()).method
    }

    private open class C

    private class E : Exception()

}
