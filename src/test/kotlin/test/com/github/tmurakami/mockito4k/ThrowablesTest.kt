package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.filterStackTrace
import com.github.tmurakami.mockito4k.given
import com.github.tmurakami.mockito4k.mock
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.configuration.IMockitoConfiguration
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ThrowablesTest {

    @Test
    fun `filterStackTrace should remove stack trace elements belonging to the library package`() {
        val configuration = mock<IMockitoConfiguration>()
        given(configuration) {
            calling { cleansStackTrace() }.willReturn(true)
        }
        try {
            filterStackTrace(configuration) { throw MyException() }
        } catch (e: Exception) {
            assertTrue(e.stackTrace.none { it.className.startsWith("com.github.tmurakami.mockito4k.") })
        }
    }

    @Test
    fun `filterStackTrace should not remove any stack trace elements if IMockitoConfiguration#cleansStackTrace is false`() {
        val configuration = mock<IMockitoConfiguration>()
        try {
            filterStackTrace(configuration) { throw MyException() }
        } catch (e: Exception) {
            assertTrue(e.stackTrace.any { it.className.startsWith("com.github.tmurakami.mockito4k.") })
        }
    }

    private class MyException : Exception() {
        init {
            @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
            (this as java.lang.Throwable).stackTrace = arrayOf(StackTraceElement("com.github.tmurakami.mockito4k.C", "foo", null, -1))
        }
    }

}
