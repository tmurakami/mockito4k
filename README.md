# Mockito4k

[![CircleCI](https://circleci.com/gh/tmurakami/mockito4k.svg?style=shield)](https://circleci.com/gh/tmurakami/mockito4k)
[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)
[![KDoc](https://img.shields.io/badge/KDoc-0.9.1-brightgreen.svg)](https://jitpack.io/com/github/tmurakami/mockito4k/0.9.1/javadoc/mockito4k/com.github.tmurakami.mockito4k/)<br>
![Kotlin](https://img.shields.io/badge/Kotlin-1.1.61%2B-blue.svg)
![Mockito](https://img.shields.io/badge/Mockito-2.7.0%2B-blue.svg)

A Kotlin wrapper around [Mockito 2](http://site.mockito.org/).

## Mock creation

Use `mock` function.

```kotlin
val mock = mock<Foo>()
```

To create a mock with additional settings, use `mock(MockSettings.() -> Unit)` function.

```kotlin
val mock = mock<Foo> { name("foo") }
```

You can use the following answers defined as top-level properties.

- RETURNS_DEFAULTS
- RETURNS_SMART_NULLS
- RETURNS_MOCKS
- RETURNS_DEEP_STUBS
- RETURNS_SELF
- CALLS_REAL_METHODS (Supports calling default implementation of interfaces)

## Stubbing

Use `given` function.

```kotlin
given(mock) {
    calling { doSomething("foo") }.willReturn("bar")
}
```

This function can also be used for properties, `Unit` (`void`) functions, and spied objects.

```kotlin
given(mock) {
    calling { someProperty = "foo" }.willReturn(Unit) // Same as Mockito#doNothing()
}
```

You can use `willThrow` function to make a function throw any checked exceptions without `@Throws` annotation.

```kotlin
interface Foo {
    fun doSomething(): String
}

class SomeException : Exception()

@Test(expected = SomeException::class)
fun test() {
    given(mock<Foo>()) {
        calling { doSomething() }.willThrow(SomeException())
    }.doSomething()
}
```

To call default implementations of interface functions, you can use `willCallRealMethod` function.

```kotlin
interface Foo {
    fun doSomething(): String = "Do something"
}

@Test
fun test() {
    assertEquals("Do something", given(mock<Foo>()) {
        calling { doSomething() }.willCallRealMethod()
    }.doSomething())
}
```

## Verification

Currently this library does not provide any function for verification, so use Mockito's [`BDDMockito#then(T)`](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.html#then(T)).

```kotlin
import org.mockito.BDDMockito.then

then(mock).should().doSomething("foo")
then(mock).should().someProperty = "bar"
```

## Comparing arguments

This library provides the following matchers as top-level functions.

- anyNullable
- any
- eq
- refEq
- same
- isA
- isNull
- isNotNull
- nullable
- matches
- argThat
- and
- or
- not
- geq
- gt
- leq
- lt
- cmpEq
- find
- aryEq

Applying a matcher written in Java to a function that does not accept null may throw an `IllegalStateException` with the message `xxx must not be null`.
To prevent this, use `by` function as follows:

```kotlin
mock.doSomething(by(MatchersWrittenInJava.matchesSomething()))
```

## Capturing arguments

Use `argumentCaptor` function.

```kotlin
val captor = argumentCaptor<String>()
```

Applying `ArgumentCaptor#capture()` to a function that does not accept null will throw an `IllegalStateException` with the message `xxx.capture() must not be null`.
To prevent this, use `capture` function instead.

```kotlin
mock.doSomething(capture(captor))
```

## Installation

First, add the [JitPack](https://jitpack.io/) repository and the [JCenter](https://bintray.com/bintray/jcenter) repository to your build.gradle.

```groovy
repositories {
    maven { url 'https://jitpack.io' }
    jcenter()
}
```

And then, add this library and mockito-core as `testCompile` dependency.

```groovy
dependencies {
    testCompile 'com.github.tmurakami:mockito4k:x.y.z'
    testCompile 'org.mockito:mockito-core:x.y.z' // 2.7.0 or later
}
```

[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)

To use this with mockito-android, add them as `androidTestCompile` dependency.

```groovy
dependencies {
    androidTestCompile 'com.github.tmurakami:mockito4k:x.y.z'
    androidTestCompile 'org.mockito:mockito-android:x.y.z' // 2.7.0 or later
}
```

## Limitations

- Stubbing the following functions does not work.

  - [Extension functions](https://kotlinlang.org/docs/reference/extensions.html): They are compiled to static methods that Mockito cannot stub.
  - [Inline functions](https://kotlinlang.org/docs/reference/inline-functions.html): They are inlined into the call site by the Kotlin compiler, so stubbing them has no effect.

- Since Mockito expects each matcher to be applied in order of method arguments, even when stubbing a function with named arguments, you need to apply matchers in that order.
