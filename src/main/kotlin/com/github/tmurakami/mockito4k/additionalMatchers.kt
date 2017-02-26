package com.github.tmurakami.mockito4k

import org.mockito.AdditionalMatchers

/**
 * The delegation to [AdditionalMatchers#and(T!, T!)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/AdditionalMatchers.html#and&#40;T,&#32;T&#41;).
 *
 * @param T the type of the given argument matchers
 * @param first the first argument matcher
 * @param second the second argument matcher
 * @return the result for executing [AdditionalMatchers#and(T!, T!)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/AdditionalMatchers.html#and&#40;T,&#32;T&#41;)
 */
fun <T> and(first: T?, second: T?): T = by(AdditionalMatchers.and(first, second))

/**
 * The delegation to [AdditionalMatchers#or(T!, T!)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/AdditionalMatchers.html#or&#40;T,&#32;T&#41;).
 *
 * @param T the type of the given argument matchers
 * @param first the first argument matcher
 * @param second the second argument matcher
 * @return the result for executing [AdditionalMatchers#or(T!, T!)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/AdditionalMatchers.html#or&#40;T,&#32;T&#41;)
 */
fun <T> or(first: T?, second: T?): T = by(AdditionalMatchers.or(first, second))

/**
 * The delegation to [AdditionalMatchers#not(T!)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/AdditionalMatchers.html#not&#40;T&#41;).
 *
 * @param T the type of the given argument matcher
 * @param matcher the argument matcher
 * @return the result for executing [AdditionalMatchers#not(T!)](https://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/AdditionalMatchers.html#not&#40;T&#41;)
 */
fun <T> not(matcher: T?): T = by(AdditionalMatchers.not(matcher))
