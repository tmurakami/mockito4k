package com.github.tmurakami.mockito4k

internal val Class<*>.isCompiledByKotlinCompiler: Boolean
    // All the Kotlin classes are marked with the `kotlin.Metadata` annotation. Note that the code below will
    // not work well if the annotation is renamed or removed by shrinking tools (e.g. ProGuard).
    get() = declaredAnnotations.any { it.annotationClass.java.name == "kotlin.Metadata" }
