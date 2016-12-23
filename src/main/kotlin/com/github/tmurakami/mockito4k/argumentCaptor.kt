package com.github.tmurakami.mockito4k

import org.mockito.ArgumentCaptor

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)
inline fun <reified T : Any> capture(captor: ArgumentCaptor<T>): T = captor.capture() ?: _Class.defaultValue(T::class.java)
