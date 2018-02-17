package com.github.tmurakami.mockito4k

import org.mockito.ArgumentCaptor

/**
 * The delegation to [ArgumentCaptor.forClass].
 */
inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

/**
 * The delegation to [ArgumentCaptor.capture].
 */
inline fun <reified T> capture(captor: ArgumentCaptor<T>): T = captor.capture()
