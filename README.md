# Mockito4k

[![Release](https://jitpack.io/v/tmurakami/mockito4k.svg)](https://jitpack.io/#tmurakami/mockito4k)
[![CircleCI](https://circleci.com/gh/tmurakami/mockito4k.svg?style=shield&circle-token=b6013808a25875faa3e6dd253e6c5eb7ee447f9d)](https://circleci.com/gh/tmurakami/mockito4k)

A Kotlin wrapper around [Mockito](https://github.com/mockito/mockito).

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
