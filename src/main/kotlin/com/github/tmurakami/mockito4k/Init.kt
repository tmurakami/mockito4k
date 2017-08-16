package com.github.tmurakami.mockito4k

import org.mockito.Answers
import org.mockito.plugins.PluginSwitch

internal class Init : PluginSwitch {

    init {
        // Replace the `implementation` field of `Answers#CALL_REAL_METHODS` to support calling the default
        // implementation of methods in interfaces
        val f = Answers::class.java.getDeclaredField("implementation")
        f.isAccessible = true
        f.set(Answers.CALLS_REAL_METHODS, KCallsRealMethods)
    }

    override fun isEnabled(pluginClassName: String): Boolean = true

}
