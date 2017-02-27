# Mockito4k

[![CircleCI](https://circleci.com/gh/tmurakami/mockito4k.svg?style=shield)](https://circleci.com/gh/tmurakami/mockito4k)
[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)  
![Kotlin](https://img.shields.io/badge/Kotlin-1.0.6%2B-blue.svg)
![Mockito](https://img.shields.io/badge/Mockito-2.6%2B-blue.svg)

A Kotlin wrapper around [Mockito 2](http://site.mockito.org/).

## Mock creation

```kotlin
val mock = mock<Foo>()
```

To create a mock with additional settings, use `MockSettings.mock` function.
```kotlin
import org.mockito.Mockito.withSettings

val mock = withSettings().name("foo").mock<Foo>()
```

## Stubbing/Verification

Use `BDDMockito#given(T)` instead of `Mockito#when(T)` because the `when` is reserved as a keyword in Kotlin.
```kotlin
import org.mockito.BDDMockito.given

given(mock.doSomething(any())).willReturn("test")
```
```kotlin
import org.mockito.BDDMockito.then

then(mock).should().doSomething(or(eq("foo"), eq("bar")))
```

These matchers are defined as top-level functions.
- any()
- any(KClass)
- eq(T)
- same(T)
- isA(KClass<T>)
- isNull()
- isNotNull()
- nullable()
- matches(Regex)
- argThat(ArgumentMatcher<T>)
- argThat((T) -> Boolean)
- and(T?, T?)
- or(T?, T?)
- not(T?)

To directly use ArgumentMatchers/AdditionalMatchers methods, use `by` function.
This function prevents causing NullPointerException when using these matchers for function that only accepts non-null parameter.
```kotlin
import org.mockito.AdditionalMatchers.geq
import org.mockito.BDDMockito.given

given(mock.doSomething(by(geq("a")))).will...
```

## Capturing arguments

```kotlin
val captor = argumentCaptor<String>()
```

Use 'capture' function to avoid causing NullPointerException.
```kotlin
import org.mockito.BDDMockito.then

given(mock.doSomething(capture(captor))).will...
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
    testCompile 'org.mockito:mockito-core:latest.release'
}
```

To use this library with mockito-android, add these libraries as `androidTestCompile` dependency.
```groovy
dependencies {
    androidTestCompile 'com.github.tmurakami:mockito4k:x.y.z'
    androidTestCompile 'org.mockito:mockito-android:latest.release'
}
```
