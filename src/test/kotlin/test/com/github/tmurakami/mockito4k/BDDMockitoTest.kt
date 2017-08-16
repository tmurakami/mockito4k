package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.any
import com.github.tmurakami.mockito4k.given
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class BDDMockitoTest {

    @Spy
    private lateinit var spied: C

    @Mock
    private lateinit var booleanFunctionMock: () -> Boolean

    @Test
    fun `given should stub the function to call the given answer object`() =
        assertEquals("foo", given(spied) {
            calling { s }.will(Answer { "foo" })
        }.s)

    @Test
    fun `given should stub the function to call the given answer function`() =
        assertEquals("foo", given(spied) {
            calling { s }.willAnswer { "foo" }
        }.s)

    @Test
    fun `given should stub the function to call the real function`() {
        given(spied) {
            calling { s = any() }.willReturn(Unit).willCallRealMethod()
        }
        spied.s = "foo"
        assertEquals("", spied.s)
        spied.s = "bar"
        assertEquals("bar", spied.s)
    }

    @Test
    fun `given should stub the function to return the given value`() =
        assertEquals("foo", given(spied) {
            calling { s }.willReturn("foo")
        }.s)

    @Test
    fun `given should stub the void function to return Unit`() {
        given(spied) {
            calling { s = any() }.willReturn(Unit)
        }
        spied.s = "foo"
        then(spied).should().s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the given exception`() {
        given(spied) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the exception of the given type`() {
        given(spied) {
            calling { s = any() }.willThrow(E::class)
        }.s = "foo"
    }

    @Test
    fun `given should not throw NullPointerException when stubbing a primitive function`() =
        assertTrue(given(booleanFunctionMock) {
            calling { invoke() }.willReturn(true)
        }())

    private open class C {
        open var s: String = ""
    }

    private class E : Exception()

}
