package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.CALLS_REAL_METHODS
import com.github.tmurakami.mockito4k.extraInterfaces
import com.github.tmurakami.mockito4k.mock
import com.github.tmurakami.mockito4k.spy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@WithMockito
class MockitoTest {

    @Test
    fun `mock() should make a mock object`() = assertTrue(Mockito.mockingDetails(mock<C>()).isMock)

    @Test
    fun `mock() should make a mock object with the given name`() =
        Mockito.mockingDetails(mock<C> { name("foo") }).run {
            assertTrue(isMock)
            assertEquals("foo", mockCreationSettings.mockName.toString())
        }

    @Test
    fun `spy() should make a spied object`() = assertTrue(Mockito.mockingDetails(spy(C())).isSpy)

    @Test
    fun `spy() should make a spied object of the given type`() = assertTrue(Mockito.mockingDetails(spy<C>()).isSpy)

    @Test
    fun `Mocking with extraInterfaces() should make a mock object which implements the given interfaces`() =
        assertTrue(Mockito.mock(C::class.java, Mockito.withSettings().extraInterfaces(I::class)) is I)

    @Test
    fun `The default implementation of an interface method should be called with callsRealMethods`() =
        assertEquals("test", mock<I> { defaultAnswer(CALLS_REAL_METHODS) }.doIt())

    private interface I {
        fun doIt(): String = "test"
    }

    private open class C
}
