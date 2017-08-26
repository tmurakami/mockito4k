package org.mockito.configuration

@Suppress("unused")
class MockitoConfiguration : DefaultMockitoConfiguration() {

    override fun cleansStackTrace(): Boolean = isStackTraceCleaned

    companion object {

        private val CLEANS_STACK_TRACE = ThreadLocal<Boolean>().apply { set(false) }

        var isStackTraceCleaned: Boolean
            get() = CLEANS_STACK_TRACE.get()
            set(value) = CLEANS_STACK_TRACE.set(value)

    }

}
