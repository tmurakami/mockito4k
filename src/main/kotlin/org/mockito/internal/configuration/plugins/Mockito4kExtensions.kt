package org.mockito.internal.configuration.plugins

import com.github.tmurakami.mockito4k.KCallsRealMethods
import org.mockito.Answers
import org.mockito.exceptions.stacktrace.StackTraceCleaner
import org.mockito.plugins.AnnotationEngine
import org.mockito.plugins.InstantiatorProvider
import org.mockito.plugins.MockMaker
import org.mockito.plugins.PluginSwitch
import org.mockito.plugins.StackTraceCleanerProvider

internal class Mockito4kExtensions :
    PluginSwitch,
    MockMaker by pluginLoader
        .withAlias("mock-maker-inline",
                   "org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker")
        .loadPlugin(MockMaker::class.java,
                    "org.mockito.internal.creation.bytebuddy.ByteBuddyMockMaker"),
    StackTraceCleanerProvider,
    InstantiatorProvider by pluginLoader
        .loadPlugin(InstantiatorProvider::class.java,
                    "org.mockito.internal.creation.instance.DefaultInstantiatorProvider"),
    AnnotationEngine by pluginLoader
        .loadPlugin(AnnotationEngine::class.java,
                    "org.mockito.internal.configuration.InjectingAnnotationEngine") {

    private val stackTraceCleanerProvider = pluginLoader
        .loadPlugin(StackTraceCleanerProvider::class.java,
                    "org.mockito.internal.exceptions.stacktrace.DefaultStackTraceCleanerProvider")

    init {
        // Replace the `implementation` field of `Answers#CALL_REAL_METHODS` to support calling the default
        // implementation of methods in interfaces
        val f = Answers::class.java.getDeclaredField("implementation")
        f.isAccessible = true
        f.set(Answers.CALLS_REAL_METHODS, KCallsRealMethods)
    }

    override fun isEnabled(pluginClassName: String): Boolean = pluginClassName == javaClass.name

    override fun getStackTraceCleaner(defaultCleaner: StackTraceCleaner): StackTraceCleaner =
        stackTraceCleanerProvider.getStackTraceCleaner(defaultCleaner).run {
            StackTraceCleaner { isIn(it) && !it.className.startsWith("com.github.tmurakami.mockito4k.") }
        }

    companion object {
        private val pluginLoader: PluginLoader get() = PluginLoader { it != Mockito4kExtensions::class.java.name }
    }

}
