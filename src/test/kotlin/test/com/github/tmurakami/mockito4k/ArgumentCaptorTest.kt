package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.argumentCaptor
import com.github.tmurakami.mockito4k.capture
import com.github.tmurakami.mockito4k.mock
import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ArgumentCaptorTest {

    @Test
    fun `capture should capture the argument without causing IllegalStateException`() {
        val mock = mock<I>()
        val arg = Any()
        mock.f(arg)
        val captor = argumentCaptor<Any>()
        then(mock).should().f(capture(captor))
        assertSame(arg, captor.value)
    }

    private interface I {
        fun f(arg: Any)
    }

}
