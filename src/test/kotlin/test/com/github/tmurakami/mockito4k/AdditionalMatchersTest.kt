@file:Suppress("RemoveRedundantBackticks")

package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.and
import com.github.tmurakami.mockito4k.aryEq
import com.github.tmurakami.mockito4k.cmpEq
import com.github.tmurakami.mockito4k.eq
import com.github.tmurakami.mockito4k.find
import com.github.tmurakami.mockito4k.geq
import com.github.tmurakami.mockito4k.gt
import com.github.tmurakami.mockito4k.leq
import com.github.tmurakami.mockito4k.lt
import com.github.tmurakami.mockito4k.mock
import com.github.tmurakami.mockito4k.not
import com.github.tmurakami.mockito4k.or
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito.times

@WithMockito
class AdditionalMatchersTest {

    @Test
    fun `and() should make a primitive matcher that matches both of the given matchers`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(3)).i(and(geq(1), leq(3)))
    }

    @Test
    fun `and() should make a object matcher that matches both of the given matchers`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(3)).s(and(geq("1"), leq("3")))
    }

    @Test
    fun `or() should make a primitive matcher that matcher either of the given matchers`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(2)).i(or(lt(1), gt(3)))
    }

    @Test
    fun `or() should make a object matcher that matcher either of the given matchers`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(2)).s(or(lt("1"), gt("3")))
    }

    @Test
    fun `not() should make a primitive matcher that does not match the given matcher`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(4)).i(not(eq(2)))
    }

    @Test
    fun `not() should make a object matcher that does not match the given matcher`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(4)).s(not(eq("2")))
    }

    @Test
    fun `geq() should make a primitive matcher that is greater than or equal to the given value`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(3)).i(geq(2))
    }

    @Test
    fun `geq() should make a object matcher that is greater than or equal to the given value`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(3)).s(geq("2"))
    }

    @Test
    fun `gt() should make a primitive matcher that is greater than the given value`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(2)).i(gt(2))
    }

    @Test
    fun `gt() should make a object matcher that is greater than the given value`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(2)).s(gt("2"))
    }

    @Test
    fun `leq() should make a primitive matcher that is less than or equal to the given value`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(3)).i(leq(2))
    }

    @Test
    fun `leq() should make a object matcher that is less than or equal to the given value`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(3)).s(leq("2"))
    }

    @Test
    fun `lt() should make a primitive matcher that is less than the given value`() {
        val mock = mock<I>()
        mock.i(0)
        mock.i(1)
        mock.i(2)
        mock.i(3)
        mock.i(4)
        then(mock).should(times(2)).i(lt(2))
    }

    @Test
    fun `lt() should make a object matcher that is less than the given value`() {
        val mock = mock<I>()
        mock.s("0")
        mock.s("1")
        mock.s("2")
        mock.s("3")
        mock.s("4")
        then(mock).should(times(2)).s(lt("2"))
    }

    @Test
    fun `cmpEq() should make a primitive matcher that is comparable-equal to the given value`() {
        val mock = mock<I>()
        mock.i(0)
        then(mock).should().i(cmpEq(0))
    }

    @Test
    fun `cmpEq() should make a object matcher that is comparable-equal to the given value`() {
        val mock = mock<I>()
        mock.s("0")
        then(mock).should().s(cmpEq("0"))
    }

    @Test
    fun `find() should make a matcher that matches the given regular expression`() {
        val mock = mock<I>()
        mock.s("foo")
        then(mock).should().s(find("o"))
    }

    @Test
    fun `aryEq(BooleanArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = booleanArrayOf()
        mock.booleans(array)
        then(mock).should().booleans(aryEq(array))
    }

    @Test
    fun `aryEq(ByteArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = byteArrayOf()
        mock.bytes(array)
        then(mock).should().bytes(aryEq(array))
    }

    @Test
    fun `aryEq(CharArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = charArrayOf()
        mock.chars(array)
        then(mock).should().chars(aryEq(array))
    }

    @Test
    fun `aryEq(DoubleArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = doubleArrayOf()
        mock.doubles(array)
        then(mock).should().doubles(aryEq(array))
    }

    @Test
    fun `aryEq(FloatArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = floatArrayOf()
        mock.floats(array)
        then(mock).should().floats(aryEq(array))
    }

    @Test
    fun `aryEq(IntArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = intArrayOf()
        mock.ints(array)
        then(mock).should().ints(aryEq(array))
    }

    @Test
    fun `aryEq(LongArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = longArrayOf()
        mock.longs(array)
        then(mock).should().longs(aryEq(array))
    }

    @Test
    fun `aryEq(ShortArray) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = shortArrayOf()
        mock.shorts(array)
        then(mock).should().shorts(aryEq(array))
    }

    @Test
    fun `aryEq(Array) should make a matcher that is equals to the given array`() {
        val mock = mock<I>()
        val array = arrayOf<Any>()
        mock.objects(array)
        then(mock).should().objects(aryEq(array))
    }

    private interface I {
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
