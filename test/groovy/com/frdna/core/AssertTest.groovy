package com.frdna.core;

import org.junit.Test;

class AssertTest extends TestCase {

    @Test void shouldThrowExceptionWhenValueIsNull() {
        assertException NullPointerException.class,
                        { Assert.notNull("value", null) }
    }

    @Test void shouldNotThrowExceptionWhenValueIsNotNull() {
        assertNoException { Assert.notNull("value", "not null") }
    }
}
