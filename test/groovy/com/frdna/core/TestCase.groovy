package com.frdna.core;

import org.junit.Assert;

class TestCase {

    void assertException(closure) {
        assertFail(null, closure)
    }

    void assertException(c, closure) {
        try {
            closure()
            Assert.fail("An exception should have been thrown")
        } catch (Exception e) {
            if (c != null) {
                assert c.isInstance(e)
            }
        }
    }

    void assertNoException(closure) {
        try {
            closure()
        } catch (Exception e) {
            Assert.fail("No exception was expected but caught " + e)
        }
    }

}
