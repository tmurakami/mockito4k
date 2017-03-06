package com.github.tmurakami.mockito4k

import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

class AdditionalMatchersTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    lateinit var mock: I

    @Test
    fun `and should make a primitive matcher that matches both of the given matchers`() {
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(3)).i(and(geq(1), leq(3)))
    }

    @Test
    fun `and should make a object matcher that matches both of the given matchers`() {
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(3)).s(and(geq("1"), leq("3")))
    }

    @Test
    fun `or should make a primitive matcher that matcher either of the given matchers`() {
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(2)).i(or(lt(1), gt(3)))
    }

    @Test
    fun `or should make a object matcher that matcher either of the given matchers`() {
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(2)).s(or(lt("1"), gt("3")))
    }

    @Test
    fun `not should make a primitive matcher that does not match the given matcher`() {
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(4)).i(not(eq(2)))
    }

    @Test
    fun `not should make a object matcher that does not match the given matcher`() {
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(4)).s(not(eq("2")))
    }

    @Test
    fun `find should make a matcher that matches the given regular expression`() {
        mock.s("foo")
        then(mock).should().s(find("o"))
    }

    @Test
    fun `aryEq_BooleanArray should make a matcher that is equals to the given array`() {
        val array = booleanArrayOf()
        mock.booleans(array)
        then(mock).should().booleans(aryEq(array))
    }

    @Test
    fun `aryEq_ByteArray should make a matcher that is equals to the given array`() {
        val array = byteArrayOf()
        mock.bytes(array)
        then(mock).should().bytes(aryEq(array))
    }

    @Test
    fun `aryEq_CharArray should make a matcher that is equals to the given array`() {
        val array = charArrayOf()
        mock.chars(array)
        then(mock).should().chars(aryEq(array))
    }

    @Test
    fun `aryEq_DoubleArray should make a matcher that is equals to the given array`() {
        val array = doubleArrayOf()
        mock.doubles(array)
        then(mock).should().doubles(aryEq(array))
    }

    @Test
    fun `aryEq_FloatArray should make a matcher that is equals to the given array`() {
        val array = floatArrayOf()
        mock.floats(array)
        then(mock).should().floats(aryEq(array))
    }

    @Test
    fun `aryEq_IntArray should make a matcher that is equals to the given array`() {
        val array = intArrayOf()
        mock.ints(array)
        then(mock).should().ints(aryEq(array))
    }

    @Test
    fun `aryEq_LongArray should make a matcher that is equals to the given array`() {
        val array = longArrayOf()
        mock.longs(array)
        then(mock).should().longs(aryEq(array))
    }

    @Test
    fun `aryEq_ShortArray should make a matcher that is equals to the given array`() {
        val array = shortArrayOf()
        mock.shorts(array)
        then(mock).should().shorts(aryEq(array))
    }

    @Test
    fun `aryEq_Array should make a matcher that is equals to the given array`() {
        val array = arrayOf<Any>()
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
