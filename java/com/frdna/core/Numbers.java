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

public final class Numbers {

    private Numbers() { }

    /**
     * Generates a binary coded decimal for a <code>Number</code>.
     *
     * @param number
     *     the number to convert to a packed decimal format
     * @return
     *     <code>number</code> in a packed decimal format or <code>null</code>
     *     if <code>number</code> is <code>null</code>
     */
    public static byte[] pack(final Number number) {

        if (number == null) {
            return null;
        }

        return Numbers.pack(String.valueOf(number));
    }

    /**
     * Generates a binary coded decimal for a <code>Number</code> that will have
     * always be signed.
     *
     * @param number
     *     the number to convert to a packed decimal format
     * @return
     *     <code>number</code> in a packed decimal format or <code>null</code>
     *     if <code>number</code> is <code>null</code>
     */
    public static byte[] packSigned(final Number number) {

        if (number == null) {
            return null;
        }

        if (number.floatValue() >= 0) {
            return Numbers.pack("+" + String.valueOf(number));
        }

        return Numbers.pack(String.valueOf(number));
    }

    /**
     * Generates a binary coded decimal for a <code>String</code> representation
     * of a number.
     *
     * @param number
     *     the string value to convert to a packed decimal format
     * @return
     *     the string value in a packed decimal format or <code>null</code>
     *     if <code>number</code> is <code>null</code>
     */
    public static byte[] pack(final String number) {

        if (number == null) {
            return null;
        }

        StringBuilder n = new StringBuilder(number.trim());
        char sign = n.charAt(0);

        if (sign == '+') {
            // remove the sign
            n.deleteCharAt(0);
            // add the packed decimal sign to the end of the string
            n.append("C");
        } else if (sign == '-') {
            // remove the sign
            n.deleteCharAt(0);
            // add the packed decimal sign to the end of the string
            n.append("D");
        } else {
            // the number is unsigned
            n.append("F");
        }

        if ((n.length() % 2) != 0) {
            // padd a zero to even out the length
            n.insert(0, "0");
        }

        // the packed length will be half the current length
        int resultLength = n.length() / 2;
        byte[] result = new byte[resultLength];
        for (int i = 0; i < resultLength; i++) {
            // the current buffer offset will be twice the result offset
            int currentOffset = i * 2;
            // parse 2 characters at a time into a hex number and remove the
            // high byte value
            result[i]
                = (byte) (Integer.parseInt(
                            n.substring(currentOffset, currentOffset + 2),
                            16) & 0x00FF);
        }

        return result;
    }
}
