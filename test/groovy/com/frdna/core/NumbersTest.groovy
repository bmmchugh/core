/* Copyright (c) 2011 Free Range Data, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.frdna.core;

import org.junit.Assert;
import org.junit.Test;

class NumbersTest extends TestCase {

    @Test void packSignedShouldBeNullWhenTheValueIsNull() {
        assert Numbers.packSigned((Number) null) == null
    }

    @Test void packSignedShouldBePositiveWhenValueIsNotNegative() {
        Assert.assertArrayEquals(
                [ (byte) 0x12, (byte) 0x3C ] as byte[],
                Numbers.packSigned(123))
    }

    @Test void packSignedShouldBeNegativeWhenValueIsNegative(){ 
        Assert.assertArrayEquals(
                [ (byte) 0x12, (byte) 0x3D ] as byte[],
                Numbers.packSigned(-123))
    }

    @Test void packShouldBeNullWhenNumberValueIsNull() {
        assert Numbers.pack((Number)null) == null
    }

    @Test void packShouldPackAnUnsignedNumber() {
        Assert.assertArrayEquals(
                [ (byte) 0x12, (byte) 0x3F ] as byte[],
                Numbers.pack(123))
    }

    @Test void packShouldPackANegativeSignedNumber() {
        Assert.assertArrayEquals(
                [ (byte) 0x34, (byte) 0x5D ] as byte[],
                Numbers.pack(-345))
    }

    @Test void packShouldBeNullWhenNumberStringIsNull() {
        assert Numbers.pack((String) null) == null
    }

    @Test void packShouldPackAPositiveSignedNumber() {
        Assert.assertArrayEquals(
                [ (byte) 0x56, (byte) 0x7C ] as byte[],
                Numbers.pack("+567"))
    }

    @Test void packShouldPackANegativeSignedNumberForAString() {
        Assert.assertArrayEquals(
                [ (byte) 0x76, (byte) 0x5D ] as byte[],
                Numbers.pack("-765"))
    }

    @Test void packShouldPackANumberThatIsUnsigned() {
        Assert.assertArrayEquals(
                [ (byte) 0x12,
                  (byte) 0x34,
                  (byte) 0x56,
                  (byte) 0x7F ] as byte[],
                Numbers.pack("1234567"))
    }

    @Test void packShouldPadAZeroWhenValueIsOddLength() {
        Assert.assertArrayEquals(
                [ (byte) 0x01,
                  (byte) 0x23,
                  (byte) 0x34,
                  (byte) 0x56,
                  (byte) 0x78,
                  (byte) 0x9C ] as byte[],
                Numbers.pack("+1233456789"))
    }
}
