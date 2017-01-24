package com.github.tmurakami.mockito4k

import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.Serializable

class ArgumentMatchersTest {

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
    fun testIsA() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("")
        BDDMockito.then(mock).should().f(isA(Serializable::class))
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
    fun testNullable() {
        abstract class A {
            abstract fun f(arg: Any?)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f(null)
        mock.f(Any())
        BDDMockito.then(mock).should(Mockito.times(2)).f(nullable())
    }

    @Test
    fun testMatches() {
        abstract class A {
            abstract fun f(arg: String)
        }

        val mock = Mockito.mock(A::class.java)
        mock.f("foobar")
        BDDMockito.then(mock).should().f(matches(Regex("[\\w]+")))
    }

    @Test
    fun testArgThat() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat { it == arg })
    }

    @Test
    fun testArgThatArgumentMatcher() {
        abstract class A {
            abstract fun f(arg: Any)
        }

        val mock = Mockito.mock(A::class.java)
        val arg = Any()
        mock.f(arg)
        BDDMockito.then(mock).should().f(argThat(ArgumentMatcher { it == arg }))
    }

}
