package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.any
import com.github.tmurakami.mockito4k.given
import com.github.tmurakami.mockito4k.mock
import com.github.tmurakami.mockito4k.spy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class BDDMockitoTest {

    @Test
    fun `given should stub the function to call the given answer object`() {
        val spied = spy<C>()
        given(spied) {
            calling { s }.will(Answer { "foo" })
        }
        assertEquals("foo", spied.s)
    }

    @Test
    fun `given should stub the function to call the given answer function`() {
        val spied = spy<C>()
        given(spied) {
            calling { s }.willAnswer { "foo" }
        }
        assertEquals("foo", spied.s)
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
        val spied = spy<C>()
        given(spied) {
            calling { s }.willReturn("foo")
        }
        assertEquals("foo", spied.s)
    }

    @Test
    fun `given should stub the void function to return Unit`() {
        val spied = spy<C>()
        given(spied) {
            calling { s = any() }.willReturn(Unit)
        }
        spied.s = "foo"
        then(spied).should().s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the given exception`() {
        val spied = spy<C>()
        given(spied) {
            calling { s = any() }.willThrow(E())
        }
        spied.s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the exception of the given type`() {
        val spied = spy<C>()
        given(spied) {
            calling { s = any() }.willThrow(E::class)
        }
        spied.s = "foo"
    }

    @Test
    fun `given should not throw NullPointerException when stubbing a primitive function`() {
        val mock = mock<() -> Boolean>()
        given(mock) {
            calling { invoke() }.willReturn(true)
        }
        assertTrue(mock())
    }

    private open class C {
        open var s: String = ""
    }

    private class E : Exception()

}
