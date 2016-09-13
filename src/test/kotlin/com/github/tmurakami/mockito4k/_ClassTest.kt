package com.github.tmurakami.mockito4k

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class _ClassTest {

    @Test
    fun testDefaultValue_boolean() {
        assertFalse(_Class.defaultValue(Boolean::class.java))
    }

    @Test
    fun testDefaultValue_byte() {
        assertEquals(0.toByte(), _Class.defaultValue(Byte::class.java))
    }

    @Test
    fun testDefaultValue_char() {
        assertEquals(0.toChar(), _Class.defaultValue(Char::class.java))
    }

    @Test
    fun testDefaultValue_double() {
        assertEquals(0.0, _Class.defaultValue(Double::class.java), 0.0)
    }

    @Test
    fun testDefaultValue_float() {
        assertEquals(0.0f, _Class.defaultValue(Float::class.java))
    }

    @Test
    fun testDefaultValue_int() {
        assertEquals(0, _Class.defaultValue(Int::class.java))
    }

    @Test
    fun testDefaultValue_long() {
        assertEquals(0L, _Class.defaultValue(Long::class.java))
    }

    @Test
    fun testDefaultValue_short() {
        assertEquals(0.toShort(), _Class.defaultValue(Short::class.java))
    }

    @Test
    fun testDefaultValue_any() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = _Class.defaultValue(Any::class.java)
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(arg)
    }

}
