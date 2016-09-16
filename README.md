# Mockito4k

[![CircleCI](https://circleci.com/gh/tmurakami/mockito4k.svg?style=shield)](https://circleci.com/gh/tmurakami/mockito4k)
[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)

A Kotlin wrapper around [Mockito 1](https://github.com/mockito/mockito).

## Installation

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    testCompile 'com.github.tmurakami:mockito4k:x.y.z'
}
```

## Mock creation

```kotlin
val mockedList = mock<MutableList<String>>()
```

## Stubbing

```kotlin
given(mockedList[any()]).willReturn("test")
willThrow(RuntimeException()).given(mockedList).clear()
```

## Verification

```kotlin
then(mockedList).should()[0]
then(mockedList).should(never())[1]
```

## Capturing arguments

```kotlin
val captor = argumentCaptor<Int>()
then(mockedList).should()[capture(captor)]
assertEquals(0, captor.value)
```
