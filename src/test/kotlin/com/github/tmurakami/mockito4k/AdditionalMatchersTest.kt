package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito.times

class AdditionalMatchersTest {

    @Test
    fun `and should make a primitive matcher that matches both of the given matchers`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(3)).i(and(geq(1), leq(3)))
    }

    @Test
    fun `and should make a object matcher that matches both of the given matchers`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(3)).s(and(geq("1"), leq("3")))
    }

    @Test
    fun `or should make a primitive matcher that matcher either of the given matchers`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(2)).i(or(lt(1), gt(3)))
    }

    @Test
    fun `or should make a object matcher that matcher either of the given matchers`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(2)).s(or(lt("1"), gt("3")))
    }

    @Test
    fun `not should make a primitive matcher that does not match the given matcher`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(4)).i(not(eq(2)))
    }

    @Test
    fun `not should make a object matcher that does not match the given matcher`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(4)).s(not(eq("2")))
    }

    @Test
    fun `find should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.s("foo")
        then(mock).should().s(find("o"))
    }

    @Test
    fun `aryEq_BooleanArray should make a matcher that is equals to the given array`() {
        val array = booleanArrayOf()
        val mock = mock<I>()
        mock.booleans(array)
        then(mock).should().booleans(aryEq(array))
    }

    @Test
    fun `aryEq_ByteArray should make a matcher that is equals to the given array`() {
        val array = byteArrayOf()
        val mock = mock<I>()
        mock.bytes(array)
        then(mock).should().bytes(aryEq(array))
    }

    @Test
    fun `aryEq_CharArray should make a matcher that is equals to the given array`() {
        val array = charArrayOf()
        val mock = mock<I>()
        mock.chars(array)
        then(mock).should().chars(aryEq(array))
    }

    @Test
    fun `aryEq_DoubleArray should make a matcher that is equals to the given array`() {
        val array = doubleArrayOf()
        val mock = mock<I>()
        mock.doubles(array)
        then(mock).should().doubles(aryEq(array))
    }

    @Test
    fun `aryEq_FloatArray should make a matcher that is equals to the given array`() {
        val array = floatArrayOf()
        val mock = mock<I>()
        mock.floats(array)
        then(mock).should().floats(aryEq(array))
    }

    @Test
    fun `aryEq_IntArray should make a matcher that is equals to the given array`() {
        val array = intArrayOf()
        val mock = mock<I>()
        mock.ints(array)
        then(mock).should().ints(aryEq(array))
    }

    @Test
    fun `aryEq_LongArray should make a matcher that is equals to the given array`() {
        val array = longArrayOf()
        val mock = mock<I>()
        mock.longs(array)
        then(mock).should().longs(aryEq(array))
    }

    @Test
    fun `aryEq_ShortArray should make a matcher that is equals to the given array`() {
        val array = shortArrayOf()
        val mock = mock<I>()
        mock.shorts(array)
        then(mock).should().shorts(aryEq(array))
    }

    @Test
    fun `aryEq_Array should make a matcher that is equals to the given array`() {
        val array = arrayOf<Any>()
        val mock = mock<I>()
        mock.objects(array)
        then(mock).should().objects(aryEq(array))
    }

    interface I {
        fun i(arg: Int)
        fun s(arg: String)
        fun booleans(arg: BooleanArray)
        fun bytes(arg: ByteArray)
        fun chars(arg: CharArray)
        fun doubles(arg: DoubleArray)
        fun floats(arg: FloatArray)
        fun ints(arg: IntArray)
        fun longs(arg: LongArray)
        fun shorts(arg: ShortArray)
        fun <T> objects(arg: Array<T>)
    }

}
