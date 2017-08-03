package com.github.tmurakami.mockito4k;

import org.mockito.internal.stubbing.answers.ThrowsException;
import org.mockito.invocation.InvocationOnMock;

final class Mockito4kThrowsException extends ThrowsException {

    private static final long serialVersionUID = 2358320508432788020L;

    Mockito4kThrowsException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public void validateFor(InvocationOnMock invocation) {
        if (isOfKotlinClass(invocation.getMock())) {
            // Kotlin has no checked exception.
            return;
        }
        super.validateFor(invocation);
    }

    @SuppressWarnings("KotlinInternalInJava")
    private static boolean isOfKotlinClass(Object mock) {
        // All the Kotlin classes are marked with the `kotlin.Metadata` annotation. Note that if the annotation is
        // renamed or removed by obfuscation tools (e.g. ProGuard), the code below will not work.
        return mock.getClass().isAnnotationPresent(kotlin.Metadata.class);
    }

}
