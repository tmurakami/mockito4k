package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.any
import com.github.tmurakami.mockito4k.given
import com.github.tmurakami.mockito4k.mock
import com.github.tmurakami.mockito4k.spy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.then
import org.mockito.Mockito
import org.mockito.stubbing.Answer

@ExtendWith(MockitoExtension::class)
class BDDMockitoTest {

    @Test
    fun `given should stub the function to call the given answer object`() {
        val mock = mock<C>()
        given(mock) {
            calling { s }.will(Answer { "foo" })
        }
        assertEquals("foo", mock.s)
    }

    @Test
    fun `given should stub the function to call the given answer function`() {
        val mock = mock<C>()
        given(mock) {
            calling { s }.willAnswer { "foo" }
        }
        assertEquals("foo", mock.s)
    }

    @Test
    fun `given should stub the function to call the real function`() {
        val spied = spy<C>()
        given(spied) {
            calling { s = any() }.willReturn(Unit).willCallRealMethod()
        }
        spied.s = "foo"
        assertEquals("", spied.s)
        spied.s = "bar"
        assertEquals("bar", spied.s)
    }

    @Test
    fun `given should stub the function to return the given value`() {
        val mock = mock<C>()
        given(mock) {
            calling { s }.willReturn("foo")
        }
        assertEquals("foo", mock.s)
    }

    @Test
    fun `given should stub the void function to return Unit`() {
        val mock = mock<C>()
        given(mock) {
            calling { s = any() }.willReturn(Unit)
        }
        mock.s = "foo"
        then(mock).should().s = "foo"
    }

    @Test
    fun `given should stub the function to throw the given exception`() {
        val mock = mock<C>()
        given(mock) {
            calling { s = any() }.willThrow(E())
        }
        assertThrows(E::class.java) { mock.s = "foo" }
    }

    @Test
    fun `given should stub the function to throw the exception of the given type`() {
        val mock = mock<C>()
        given(mock) {
            calling { s = any() }.willThrow(E::class)
        }
        assertThrows(E::class.java) { mock.s = "foo" }
    }

    @Test
    fun `given should not throw NullPointerException when stubbing a primitive function`() {
        val mock = mock<() -> Boolean>()
        given(mock) {
            calling { invoke() }.willReturn(true)
        }
        assertTrue(mock())
    }

    @Test
    fun `given should stub the function of the mock whose default answer is RETURNS_DEEP_STUBS`() {
        val mock = mock<C> { defaultAnswer(Mockito.RETURNS_DEEP_STUBS) }
        given(mock) {
            calling { self.s }.willReturn("test")
        }
        assertEquals("test", mock.self.s)
    }

    private open class C {
        open var s: String = ""
        open val self: C get() = this
    }

    private class E : Exception()

}
