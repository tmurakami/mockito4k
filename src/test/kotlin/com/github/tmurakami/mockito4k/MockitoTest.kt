package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.internal.util.MockUtil
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MockitoTest {

    @Test
    fun `mock should make a mock object`() = assertTrue(MockUtil.isMock(mock<C>()))

    @Test
    fun `mock should make a mock object with the given name`() {
        val mock = mock<C> { name("foo") }
        assertTrue(MockUtil.isMock(mock))
        assertEquals("foo", MockUtil.getMockName(mock).toString())
    }

    @Test
    fun `spy should make a spied object`() = assertTrue(MockUtil.isMock(spy(C())))

    @Test
    fun `spy should make a spied object of the given type`() = assertTrue(MockUtil.isMock(spy<C>()))

    @Test
    fun `mock using extraInterfaces should make a mock object which implements the given interfaces`() =
        assertTrue(Mockito.mock(C::class.java, Mockito.withSettings().extraInterfaces(I::class)) is I)

    private interface I
    private open class C

}
