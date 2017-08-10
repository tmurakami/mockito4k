package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
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

    private interface I
    private open class C

}
