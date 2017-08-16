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
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class BDDMockitoTest {

    @Spy
    private lateinit var kotlinMock: KotlinClass

    @Mock
    private lateinit var javaMock: JavaClass

    @Mock
    private lateinit var booleanFunctionMock: () -> Boolean

    @Test
    fun `given should stub the function to call the given answer object`() =
        assertEquals("foo", given(kotlinMock) {
            calling { s }.will(Answer { "foo" })
        }.s)

    @Test
    fun `given should stub the function to call the given answer function`() =
        assertEquals("foo", given(kotlinMock) {
            calling { s }.willAnswer { "foo" }
        }.s)

    @Test
    fun `given should stub the function to call the real function`() {
        given(kotlinMock) {
            calling { s = any() }.willReturn(Unit).willCallRealMethod()
        }
        kotlinMock.s = "foo"
        assertEquals("", kotlinMock.s)
        kotlinMock.s = "bar"
        assertEquals("bar", kotlinMock.s)
    }

    @Test
    fun `given should stub the function to return the given value`() =
        assertEquals("foo", given(kotlinMock) {
            calling { s }.willReturn("foo")
        }.s)

    @Test
    fun `given should stub the void function to return Unit`() {
        given(kotlinMock) {
            calling { s = any() }.willReturn(Unit)
        }
        kotlinMock.s = "foo"
        then(kotlinMock).should().s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the given error`() {
        given(kotlinMock) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the error of the given type`() {
        given(kotlinMock) {
            calling { s = any() }.willThrow(E::class)
        }.s = "foo"
    }

    @Test(expected = MockitoException::class)
    fun `given should throw MockitoException if the given checked exception does not match the stubbed Java method signature`() {
        given(javaMock) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    @Test(expected = MockitoException::class)
    fun `given should throw MockitoException if the given checked exception type does not match the stubbed Java method signature`() {
        given(javaMock) {
            calling { s = any() }.willThrow(E::class)
        }.s = "foo"
    }

    @Test
    fun `given should not throw NullPointerException when stubbing a primitive function`() =
        assertTrue(given(booleanFunctionMock) {
            calling { invoke() }.willReturn(true)
        }())

    private open class KotlinClass {
        open var s: String = ""
    }

    private class E : Exception()

}
