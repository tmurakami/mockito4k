package com.github.tmurakami.mockito4k

import org.mockito.AdditionalAnswers
import org.mockito.Incubating
import org.mockito.stubbing.Answer

fun <T> returnsFirstArg(): Answer<T> = AdditionalAnswers.returnsFirstArg()
fun <T> returnsSecondArg(): Answer<T> = AdditionalAnswers.returnsSecondArg()
fun <T> returnsLastArg(): Answer<T> = AdditionalAnswers.returnsLastArg()
fun <T> returnsArgAt(position: Int): Answer<T> = AdditionalAnswers.returnsArgAt(position)
fun <T> delegatesTo(delegate: Any): Answer<T> = AdditionalAnswers.delegatesTo(delegate)
fun <T> returnsElementsOf(elements: Collection<*>): Answer<T> = AdditionalAnswers.returnsElementsOf(elements)

@Incubating
fun <T, A> answer(answer: A.() -> T): Answer<T> = AdditionalAnswers.answer(answer)

@Incubating
fun <T, A, B> answer(answer: (A, B) -> T): Answer<T> = AdditionalAnswers.answer(answer)

@Incubating
fun <T, A, B, C> answer(answer: (A, B, C) -> T): Answer<T> = AdditionalAnswers.answer(answer)

@Incubating
fun <T, A, B, C, D> answer(answer: (A, B, C, D) -> T): Answer<T> = AdditionalAnswers.answer(answer)

@Incubating
fun <T, A, B, C, D, E> answer(answer: (A, B, C, D, E) -> T): Answer<T> = AdditionalAnswers.answer(answer)
