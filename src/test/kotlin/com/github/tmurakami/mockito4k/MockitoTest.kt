package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.internal.util.MockUtil
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

class MockitoTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Test
    fun `mock should make a mock object`() = assertTrue(MockUtil.isMock(mock<C>()))

    @Test
    fun `mock should make a mock object with the specified name`() {
        val mock = mock<C> { name("foo") }
        assertTrue(MockUtil.isMock(mock))
        assertEquals("foo", MockUtil.getMockName(mock).toString())
    }

    @Test
    fun `spy should make a spied object`() = assertTrue(MockUtil.isMock(spy(C())))

    @Test
    fun `spy should make a spied object of the given type`() = assertTrue(MockUtil.isMock(spy<C>()))

    @Test
    fun `mock using extraInterfaces should make a mock object which implements the specified interfaces`() =
            assertTrue(Mockito.mock(C::class.java, Mockito.withSettings().extraInterfaces(I::class)) is I)

    interface I
    open class C

}
