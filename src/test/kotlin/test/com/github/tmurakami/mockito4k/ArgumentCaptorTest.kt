package test.com.github.tmurakami.mockito4k

import com.github.tmurakami.mockito4k.argumentCaptor
import com.github.tmurakami.mockito4k.capture
import com.github.tmurakami.mockito4k.mock
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.then

@ExtendWith(MockitoExtension::class)
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
