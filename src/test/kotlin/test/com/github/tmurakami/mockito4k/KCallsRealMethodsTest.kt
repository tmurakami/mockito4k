package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.KCallsRealMethods
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class KCallsRealMethodsTest {
    @Test
    fun `KCallsRealMethods should be serializable`() {
        val testTarget = KCallsRealMethods
        val bytes = ByteArrayOutputStream().apply {
            ObjectOutputStream(this).use { it.writeObject(testTarget) }
        }.toByteArray()
        val o = ObjectInputStream(ByteArrayInputStream(bytes)).use { it.readObject() }
        assertNotSame(testTarget, o)
        assertTrue(o is KCallsRealMethods)
    }
}
