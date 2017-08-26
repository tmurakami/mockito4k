package com.github.tmurakami.mockito4k

import org.mockito.internal.configuration.GlobalConfiguration

private val myPackage = Init::class.java.run { name.removeSuffix(simpleName) }
private val configuration = GlobalConfiguration()

internal fun <T> filterStackTrace(function: () -> T): T =
    try {
        function()
    } catch (e: Exception) {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        if (configuration.cleansStackTrace()) {
            // We do not use the `StackTraceCleaner` extension because multiple StackTraceCleaners are not allowed. If
            // we used the extension, libraries with their own StackTraceCleaner (e.g. dexmaker-mockito) might not work
            // with this library.
            (e as java.lang.Throwable).run {
                stackTrace = stackTrace.filterNot { it.className.startsWith(myPackage) }.toTypedArray()
            }
        }
        throw e
    }
