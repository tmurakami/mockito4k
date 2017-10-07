package test.com.github.tmurakami.mockito4k

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockito.Mockito
import org.mockito.MockitoSession

class MockitoExtension : BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private lateinit var session: MockitoSession

    override fun beforeTestExecution(context: ExtensionContext) {
        session = Mockito.mockitoSession().startMocking()
    }

    override fun afterTestExecution(context: ExtensionContext) = session.finishMocking()

}
