package com.github.tmurakami.mockito4k

import org.mockito.Answers
import org.mockito.stubbing.Answer

/**
 * The default answer.
 */
val RETURNS_DEFAULTS: Answer<Any?> = Answers.RETURNS_DEFAULTS

/**
 * An answer that returns smart-nulls.
 */
val RETURNS_SMART_NULLS: Answer<Any?> = Answers.RETURNS_SMART_NULLS

/**
 * An answer that returns mocks.
 */
val RETURNS_MOCKS: Answer<Any?> = Answers.RETURNS_MOCKS

/**
 * Returns an answer that returns deep stubs.
 */
val RETURNS_DEEP_STUBS: Answer<Any?> = Answers.RETURNS_DEEP_STUBS

/**
 * Returns an answer that returns itself.
 */
val RETURNS_SELF: Answer<Any?> = Answers.RETURNS_SELF

/**
 * Returns an answer that calls real methods.
 */
val CALLS_REAL_METHODS: Answer<Any?> = KCallsRealMethods
