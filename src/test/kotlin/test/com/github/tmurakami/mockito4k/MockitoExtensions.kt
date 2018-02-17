package test.com.github.tmurakami.mockito4k

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockito.Mockito
import org.mockito.MockitoSession

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@ExtendWith(MockitoExtension::class)
annotation class WithMockito

private class MockitoExtension : BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private val ExtensionContext.store: ExtensionContext.Store
        get() = getStore(ExtensionContext.Namespace.create(this@MockitoExtension.javaClass, this))

    override fun beforeTestExecution(context: ExtensionContext) =
        context.store.put(context.requiredTestMethod, Mockito.mockitoSession().startMocking())

    override fun afterTestExecution(context: ExtensionContext) =
        context.store.remove(context.requiredTestMethod, MockitoSession::class.java).finishMocking()
}
