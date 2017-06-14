package com.github.tmurakami.mockito4k

import org.mockito.ArgumentCaptor

/**
 * The delegation to [ArgumentCaptor#forClass(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html#forClass&#40;java.lang.Class&#41;).
 *
 * @param T the type of an [ArgumentCaptor](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html)
 * @return the result for executing [ArgumentCaptor#forClass(Class)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html#forClass&#40;java.lang.Class&#41;)
 */
inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

/**
 * The delegation to [ArgumentCaptor#capture()](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html#capture&#40;&#41;).
 *
 * @param T the type of the given [ArgumentCaptor](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html)
 * @param captor the [ArgumentCaptor](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html)
 * @return the result for executing [ArgumentCaptor#capture()](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/ArgumentCaptor.html#capture&#40;&#41;)
 */
inline fun <reified T> capture(captor: ArgumentCaptor<T>): T = captor.capture()
