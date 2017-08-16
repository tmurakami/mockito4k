package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.extraInterfaces
import com.github.tmurakami.mockito4k.mock
import com.github.tmurakami.mockito4k.spy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MockitoTest {

    @Test
    fun `mock should make a mock object`() = assertTrue(Mockito.mockingDetails(mock<C>()).isMock)

    @Test
    fun `mock should make a mock object with the given name`() =
        Mockito.mockingDetails(mock<C> { name("foo") }).run {
            assertTrue(isMock)
            assertEquals("foo", mockCreationSettings.mockName.toString())
        }

    @Test
    fun `spy should make a spied object`() = assertTrue(Mockito.mockingDetails(spy(C())).isSpy)

    @Test
    fun `spy should make a spied object of the given type`() = assertTrue(Mockito.mockingDetails(spy<C>()).isSpy)

    @Test
    fun `mock using extraInterfaces should make a mock object which implements the given interfaces`() =
        assertTrue(Mockito.mock(C::class.java, Mockito.withSettings().extraInterfaces(I::class)) is I)

    @Test
    fun `The default implementation of an interface method should be called with Answers#CALLS_REAL_METHODS`() =
        assertEquals("test", mock<I> { defaultAnswer(Answers.CALLS_REAL_METHODS) }.doIt())

    private interface I {
        fun doIt(): String = "test"
    }

    private open class C

}
