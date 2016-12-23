package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class AdditionalMatchersTest {

    @Test
    fun testGeq() {
        abstract class A {
            abstract fun f(arg: Int)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(0)
        mock.f(1)
        BDDMockito.then(mock).should(Mockito.times(2)).f(geq(0))
    }

    @Test
    fun testLeq() {
        abstract class A {
            abstract fun f(arg: Int)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(0)
        mock.f(-1)
        BDDMockito.then(mock).should(Mockito.times(2)).f(leq(0))
    }

    @Test
    fun testGt() {
        abstract class A {
            abstract fun f(arg: Int)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(1)
        BDDMockito.then(mock).should().f(gt(0))
    }

    @Test
    fun testLt() {
        abstract class A {
            abstract fun f(arg: Int)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(-1)
        BDDMockito.then(mock).should().f(lt(0))
    }

    @Test
    fun testCmpEq() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("")
        BDDMockito.then(mock).should().f(cmpEq(""))
    }

    @Test
    fun testFind() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(find("a"))
    }

    @Test
    fun testAryEqBooleanArray() {
        abstract class A {
            abstract fun f(arg: BooleanArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(booleanArrayOf(false))
        BDDMockito.then(mock).should().f(aryEq(booleanArrayOf(false)))
    }

    @Test
    fun testAryEqByteArray() {
        abstract class A {
            abstract fun f(arg: ByteArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("".toByteArray())
        BDDMockito.then(mock).should().f(aryEq("".toByteArray()))
    }

    @Test
    fun testAryEqCharArray() {
        abstract class A {
            abstract fun f(arg: CharArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("".toCharArray())
        BDDMockito.then(mock).should().f(aryEq("".toCharArray()))
    }

    @Test
    fun testAryEqDoubleArray() {
        abstract class A {
            abstract fun f(arg: DoubleArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(doubleArrayOf(0.0))
        BDDMockito.then(mock).should().f(aryEq(doubleArrayOf(0.0)))
    }

    @Test
    fun testAryEqFloatArray() {
        abstract class A {
            abstract fun f(arg: FloatArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(floatArrayOf(0.0f))
        BDDMockito.then(mock).should().f(aryEq(floatArrayOf(0.0f)))
    }

    @Test
    fun testAryEqLongArray() {
        abstract class A {
            abstract fun f(arg: LongArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(longArrayOf(0L))
        BDDMockito.then(mock).should().f(aryEq(longArrayOf(0L)))
    }

    @Test
    fun testAryEqIntArray() {
        abstract class A {
            abstract fun f(arg: IntArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(intArrayOf(0))
        BDDMockito.then(mock).should().f(aryEq(intArrayOf(0)))
    }

    @Test
    fun testAryEqShortArray() {
        abstract class A {
            abstract fun f(arg: ShortArray)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(shortArrayOf(0))
        BDDMockito.then(mock).should().f(aryEq(shortArrayOf(0)))
    }

    @Test
    fun testAryEqArray() {
        abstract class A {
            abstract fun f(arg: Array<Unit>)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(arrayOf(Unit))
        BDDMockito.then(mock).should().f(aryEq(arrayOf(Unit)))
    }

    @Test
    fun testAnd() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("ab")
        BDDMockito.then(mock).should().f(and(startsWith("a"), endsWith("b")))
    }

    @Test
    fun testOr() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(or(startsWith("a"), endsWith("b")))
    }

    @Test
    fun testNot() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("b")
        BDDMockito.then(mock).should().f(not(eq("a")))
    }

    @Test
    fun testEqDouble() {
        abstract class A {
            abstract fun f(arg: Double)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(0.11)
        BDDMockito.then(mock).should().f(eq(0.1, 0.01))
    }

    @Test
    fun testEqFloat() {
        abstract class A {
            abstract fun f(arg: Float)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(0.11f)
        BDDMockito.then(mock).should().f(eq(0.1f, 0.01f))
    }

}
