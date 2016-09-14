package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.Serializable

class MatchersTest {

    @Test
    fun testAny() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(Any())
        BDDMockito.then(mock).should().f(any())
    }

    @Test
    fun testEq() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = Any()
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(eq(arg))
    }

    @Test
    fun testRefEq() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = "" to Any()
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(refEq("" to null, "second"))
    }

    @Test
    fun testSame() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val arg = Any()
        val mock = Mockito.mock(A::class.java)
        mock.f(arg)
        BDDMockito.then(mock).should().f(same(arg))
    }

    @Test
    fun testIsAKClass() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("")
        BDDMockito.then(mock).should().f(isA(Serializable::class))
    }

    @Test
    fun testIsAClass() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("")
        BDDMockito.then(mock).should().f(isA(Serializable::class.java))
    }

    @Test
    fun testIsNull() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(null)
        BDDMockito.then(mock).should().f(isNull())
    }

    @Test
    fun testIsNotNull() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(Any())
        BDDMockito.then(mock).should().f(isNotNull())
    }

    @Test
    fun testIsStartsWith() {
        abstract class A {
            abstract fun f(s: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(startsWith("fo"))
    }

    @Test
    fun testIsContains() {
        abstract class A {
            abstract fun f(s: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(contains("ob"))
    }

    @Test
    fun testIsEndsWith() {
        abstract class A {
            abstract fun f(s: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(endsWith("ar"))
    }

    @Test
    fun testIsMatches() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(matches("[\\w]+"))
    }

    @Test
    fun testArgThat() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat { this == arg })
    }

}
