# Mockito4k

[![CircleCI](https://circleci.com/gh/tmurakami/mockito4k.svg?style=shield)](https://circleci.com/gh/tmurakami/mockito4k)
[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)
[![KDoc](https://img.shields.io/badge/KDoc-0.8.4-brightgreen.svg)](https://jitpack.io/com/github/tmurakami/mockito4k/0.8.4/javadoc/mockito4k/com.github.tmurakami.mockito4k/)<br>
![Kotlin](https://img.shields.io/badge/Kotlin-1.0.7%2B-blue.svg)
![Mockito](https://img.shields.io/badge/Mockito-2.7.0%2B-blue.svg)

A Kotlin wrapper around [Mockito 2](http://site.mockito.org/).

## Mock creation

Use the `mock` function.

```kotlin
val mock = mock<Foo>()
```

To create a mock with additional settings, use the `mock(MockSettings.() -> Unit)` function.

```kotlin
val mock = mock<Foo> { name("foo") }
```

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

Although Kotlin does not have checked exceptions, Mockito cannot stub the function to throw a checked exception that does not match the method signature.
Therefore, we extended `willThrow` function to be able to throw any exceptions without `@Throws` annotation.

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

Also `willCallRealMethod` function has been extended to call the default implementation of interface functions.

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

Currently we do not provide any function for verification, so use Mockito's [`BDDMockito#then(T)`](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/BDDMockito.html#then(T)).

```kotlin
import org.mockito.BDDMockito.then

then(mock).should().doSomething("foo")
then(mock).should().someProperty = "bar"
```

## Comparing arguments

We provide the following matchers as top-level functions.

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
In that case, use `by` function as follows:

```kotlin
mock.doSomething(by(MatchersWrittenInJava.matchesSomething()))
```

## Capturing arguments

Use `argumentCaptor` function.

```kotlin
val captor = argumentCaptor<String>()
```

Applying `ArgumentCaptor#capture()` to a function that does not accept null will throw an `IllegalStateException` with the message `xxx.capture() must not be null`.
To avoid it, use `capture` extension function instead.

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

- Do not use [`org.mockito.plugins.PluginSwitch`](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/plugins/PluginSwitch.html) extension because this library has its own `PluginSwitch` which replaces [`Answers#CALLS_REAL_METHODS`](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Answers.html#CALLS_REAL_METHODS) to support calling the default implementation of functions in interfaces.
