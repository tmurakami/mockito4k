# Mockito4k

[![CircleCI](https://circleci.com/gh/tmurakami/mockito4k.svg?style=shield)](https://circleci.com/gh/tmurakami/mockito4k)
[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)  
![Kotlin](https://img.shields.io/badge/Kotlin-1.0.6%2B-blue.svg)
![Mockito](https://img.shields.io/badge/Mockito-2.6.0%2B-blue.svg)

A Kotlin wrapper around [Mockito2](https://github.com/mockito/mockito).

## Mock creation

```kotlin
val mockedList = mock<MutableList<String>>()
```

## Stubbing/Verification

```kotlin
BDDMockito.given(mockedList[any()]).willReturn("test")
```

These matchers are defined as top-level functions.
- any()
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

To directly use ArgumentMatchers/AdditionalMatchers methods, use 'by' function.
This function prevents causing NullPointerException when using these matchers for method that only accepts non-null parameter.
```kotlin
BDDMockito.given(mock.doSomething(by(AdditionalMatchers.geq("a"))))
```

## Capturing arguments

```kotlin
val captor = argumentCaptor<Int>()
```

Use 'capture' function to avoid causing NullPointerException.
```kotlin
BDDMockito.then(mockedList).should()[capture(captor)]
assertEquals(0, captor.value)
```

## Installation

First, add the [JitPack](https://jitpack.io/) repository and the [JCenter](https://bintray.com/bintray/jcenter) repository to your build.gradle.
```groovy
repositories {
    maven { url 'https://jitpack.io' }
    jcenter()
}
```

And then, add this library and mockito-core as 'testCompile' dependency.
```groovy
dependencies {
    testCompile 'org.mockito:mockito-core:2.6+'
    testCompile 'com.github.tmurakami:mockito4k:x.y.z'
}
```
