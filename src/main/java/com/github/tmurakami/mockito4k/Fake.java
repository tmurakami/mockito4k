package com.github.tmurakami.mockito4k;

final class Fake {

    private Fake() {
        throw new AssertionError("Do not instantiate");
    }

    static <T> T nonNull() {
        return null;
    }

}
