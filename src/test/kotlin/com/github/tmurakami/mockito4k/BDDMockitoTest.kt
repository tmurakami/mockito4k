package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
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
    private lateinit var kotlinClass: KotlinClass

    @Mock
    private lateinit var javaInterface: JavaClass

    @Test
    fun `given should stub the function to call the given answer object`() =
        assertEquals("foo", given(kotlinClass) {
            calling { s }.will(Answer { "foo" })
        }.s)

    @Test
    fun `given should stub the function to call the given answer function`() =
        assertEquals("foo", given(kotlinClass) {
            calling { s }.willAnswer { "foo" }
        }.s)

    @Test
    fun `given should stub the function to call the real function`() {
        given(kotlinClass) {
            calling { s = any() }.willReturn(Unit).willCallRealMethod()
        }
        kotlinClass.s = "foo"
        assertEquals("", kotlinClass.s)
        kotlinClass.s = "bar"
        assertEquals("bar", kotlinClass.s)
    }

    @Test
    fun `given should stub the function to return the given value`() =
        assertEquals("foo", given(kotlinClass) {
            calling { s }.willReturn("foo")
        }.s)

    @Test
    fun `given should stub the void function to return Unit`() {
        given(kotlinClass) {
            calling { s = any() }.willReturn(Unit)
        }
        kotlinClass.s = "foo"
        then(kotlinClass).should().s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the given error`() {
        given(kotlinClass) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    @Test(expected = E::class)
    fun `given should stub the function to throw the error of the given type`() {
        given(kotlinClass) {
            calling { s = any() }.willThrow(E::class)
        }.s = "foo"
    }

    @Test(expected = MockitoException::class)
    fun `given should throw MockitoException if the given checked exception does not match the stubbed Java method signature`() {
        given(javaInterface) {
            calling { s = any() }.willThrow(E())
        }.s = "foo"
    }

    private open class KotlinClass {
        open var s: String = ""
    }

    private class E : Exception()

}
