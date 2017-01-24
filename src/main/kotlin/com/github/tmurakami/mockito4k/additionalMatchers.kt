package com.github.tmurakami.mockito4k

import org.mockito.AdditionalMatchers

fun <T> and(first: T?, second: T?): T = by(AdditionalMatchers.and(first, second))
fun <T> or(first: T?, second: T?): T = by(AdditionalMatchers.or(first, second))
fun <T> not(first: T?): T = by(AdditionalMatchers.not(first))
