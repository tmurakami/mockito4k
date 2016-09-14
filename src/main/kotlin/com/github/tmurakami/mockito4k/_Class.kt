package com.github.tmurakami.mockito4k

import org.mockito.internal.util.Primitives

object _Class {
    fun <T> defaultValue(clazz: Class<T>): T = Primitives.defaultValue(clazz)
}
