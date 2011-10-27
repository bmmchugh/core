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

import java.util.regex.Pattern;

public final class Verify {
    public static final char[] LOWERCASE_ALPHA_CHARS = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static final char[] UPPERCASE_ALPHA_CHARS = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static final char[] NUMERIC_CHARS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public static final char[] SPECIAL_CHARS = {
        '`', '~', '!', '@', '#', '$', '%', '^',  '&', '*', '(', ')', '-',
        '_', '=', '+', '{', '[', '}', ']', '\\', ':', ';', '"', '|', '\'',
        ',', '.', '<', '>', '/', '?' };

    public static final String REGEX_ALPHA = "^[a-zA-Z]+$";

    public static final String REGEX_ALPHA_NUMERIC = "^[a-zA-Z0-9]+$";

    public static final String REGEX_DOCUMENT_FILENAME =
        "^[a-zA-Z0-9_\\-\\.]{1,255}$";

    public static final String REGEX_EMAIL =
            "^([_a-zA-Z0-9\\+\\.\\-]+\\@[_a-z"
                    + "A-Z0-9\\-]+\\.[_a-zA-Z0-9\\.\\-]+)?$";

    public static final String REGEX_HEX_COLOR = "^[0-9a-fA-F]{6}$";

    public static final String REGEX_MEANINGFUL = "^.*[a-zA-Z0-9]+.*$";

    public static final String REGEX_PHONE = "^(1\\s*[-\\/\\.]?)?(\\((\\d{3})"
        + "\\)|(\\d{3}))\\s*[-\\/\\.]?\\s*(\\d{3})\\s*[-\\/\\.]?\\s*(\\d{4})"
        + "\\s*(([xX]|[eE][xX][tT])\\.?\\s*(\\d+))*$";

    public static final String REGEX_URL = "^(([hH][tT][tT][pP][sS]?):\\/\\/)?"
        + "[a-zA-Z0-9]+([\\-\\.]{1}[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,5}"
        + "(([0-9]{1,5})?\\/.*)?$";

    private Verify() {
    }

    /**
     * Tests if all characters in a sequence are alpha characters.
     *
     * @param value
     *     the character sequence to test
     * @return
     *     <code>true</code> if all characters in <code>value</code> are alpha
     *     characters
     */
    public static boolean alpha(final CharSequence value) {
        if (Verify.missing(value)) {
            return false;
        }

        return Pattern.matches(Verify.REGEX_ALPHA, value);
    }

    /**
     * Tests if all characters in a sequence are alpha or numeric.
     *
     * @param value
     *     the character sequence to test
     * @return
     *     <code>true</code> if all characters in <code>value</code> are alpha
     *     or numeric characters
     */
    public static boolean alphaNumeric(final CharSequence value) {
        if (Verify.missing(value)) {
            return false;
        }

        return Pattern.matches(Verify.REGEX_ALPHA_NUMERIC, value);
    }

    /**
     * Tests if a string is meaningful.
     *
     * @param value
     *     the string to be tested
     * @return
     *     <code>true</code> if the string has meaning
     */
    public static boolean meaningful(final CharSequence value) {
        if (Verify.missing(value)) {
            return false;
        }

        return Pattern.matches(Verify.REGEX_MEANINGFUL, value);
    }

    /**
     * Tests if any value is <code>null</code>.
     *
     * @param args
     *     the values to test
     * @return
     *     <code>true</code> if any value in <code>args</code> is not
     *     <code>null</code>
     */
    public static boolean notAllNull(final Object... args) {
        if (args == null) {
            return false;
        }

        for (Object current : args) {
            if (current != null) {
                return true;
            }
        }

        return false;
    }

    /**
     * Test if all values are not <code>null</code>.
     *
     * @param args
     *     the values to test for null
     * @return
     *     <code>true</code> if none of the values in <code>args</code> is
     *     <code>null</code>
     */
    public static boolean notNull(final Object... args) {
        if (args == null) {
            return false;
        }

        for (Object current : args) {
            if (current == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Tests if all values are not null or non-empty strings.
     *
     * @param args
     *     the values to test for present
     * @return
     *     <code>true</code> if all values in <code>args</code> are not empty
     * @see Strings#isEmpty(Object)
     */
    public static boolean present(final Object... args) {
        if (args == null) {
            return false;
        }

        for (Object current : args) {
            if (Strings.isEmpty(current)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Tests if any value is null or non-empty string.
     *
     * @param args
     *     the values to test for missing
     * @return
     *     <code>true</code> if any value in <code>args</code> is empty
     * @see Strings#isEmpty(Object)
     */
    public static boolean missing(final Object... args) {
        return !Verify.present(args);
    }

    /**
     * Tests if a string is a valid e-mail.
     *
     * @param value
     *     the value to test
     * @return
     *     <code>true</code> if <code>value</code> is a valid e-mail address
     */
    public static boolean email(final CharSequence value) {
        if (!Verify.present(value)) {
            return false;
        }
        return Pattern.matches(Verify.REGEX_EMAIL, value);
    }

    /**
     * Tests if a string represents a whole number.
     *
     * @param value
     *     the value to test
     * @return
     *     <code>true</code> if <code>value</code> is a whole number
     */
    public static boolean integerNumeric(final CharSequence value) {
        return Verify.regex(value, "-?\\d+");
    }

    /**
     * Tests if a string matches a regex expression.
     *
     * @param value
     *     the value to test
     * @param regex
     *     the regex to test value
     * @return
     *     <code>true</code> if <code>value</code> matches <code>regex</code>
     */
    public static boolean regex(final CharSequence value, final String regex) {
        if (Verify.missing(value, regex)) {
            return false;
        }

        return Pattern.matches(regex, value);
    }

    /**
     * Tests if a string is a valid URL.
     *
     * @param value
     *     the value to test
     * @return
     *     <code>true</code> if <code>value</code> is a valid URL
     */
    public static boolean url(final CharSequence value) {
        return Verify.regex(value, Verify.REGEX_URL);
    }

    /**
     * Tests if a string is a valid hex color scheme.
     *
     * @param value
     *     the value to test
     * @return
     *     <code>true</code> if <code>value</code> is a valid hex color
     */
    public static boolean hexColor(final CharSequence value) {
        return Verify.regex(value, Verify.REGEX_HEX_COLOR);
    }

    /**
     * Tests if a string is a valid file name.
     *
     * @param value
     *     the value to test
     * @return
     *     <code>true</code> if <code>value</code> is a valid file name
     */
    public static boolean documentFilename(final CharSequence value) {
        return Verify.regex(value, Verify.REGEX_DOCUMENT_FILENAME);
    }

    /**
     * Tests if a string is within a specified range.
     *
     * @param value
     *     the value to test for length
     * @param minLength
     *     the minimum inclusive length allowed
     * @param maxLength
     *     the maximum inclusive length allowed
     * @return
     *     <code>true</code> if <code>value</code> is between
     *     <code>minLength</code> and <code>maxLength</code>
     * @throws NullPointerException
     *     if <code>minLength</code> or <code>maxLength</code> is
     *     <code>null</code>
     */
    public static boolean length(
            final CharSequence value,
            final Number minLength,
            final Number maxLength) {

        return (Verify.minLength(value, minLength)
                && Verify.maxLength(value, maxLength));
    }

    /**
     * Tests if a string is less then a specified length.
     *
     * @param value
     *     the value to test for length
     * @param maxLength
     *     the maximum inclusive length allowed
     * @return
     *     <code>true</code> if <code>value</code> less than or equal to
     *     <code>maxLength</code>
     * @throws NullPointerException
     *     if <code>maxLength</code> is <code>null</code>
     */
    public static boolean maxLength(
            final CharSequence value,
            final Number maxLength) {
        Assert.notNull("maxLength", maxLength);
        if (value == null) {
            return false;
        }

        return (value.length() <= maxLength.intValue());
    }

    /**
     * Tests if a string is within a specified range.
     *
     * @param value
     *     the value to test for length
     * @param minLength
     *     the minimum inclusive length allowed
     * @return
     *     <code>true</code> if <code>value</code> is greater than or equal to
     *     <code>minLength</code>
     * @throws NullPointerException
     *     if <code>minLength</code> is <code>null</code>
     */
    public static boolean minLength(
            final CharSequence value,
            final Number minLength) {
        Assert.notNull("minLength", minLength);
        if (value == null) {
            return false;
        }

        return (value.length() >= minLength.intValue());
    }

    /**
     * Tests if a value is zero.
     *
     * @param value
     *     the value to test for zero
     * @return
     *     <code>true</code> if <code>value</code> is zero
     * @throws NullPointerException
     *     if <code>value</code> is <code>null</code>
     */
    public static boolean zero(final Number value) {
        Assert.notNull("value", value);

        return (value.floatValue() == 0);
    }

    /**
     * Tests if a value is not zero.
     *
     * @param value
     *     the value to test for non-zero
     * @return
     *     <code>true</code> if <code>value</code> is not zero
     * @throws NullPointerException
     *     if <code>value</code> is <code>null</code>
     */
    public static boolean notZero(final Number value) {
        return !Verify.zero(value);
    }

    /**
     * Tests if <code>rhs</code> is greater than or equal to <code>lhs</code>.
     *
     * @param lhs
     *     the comparable value
     * @param rhs
     *     the value to compare
     * @return
     *     true if <code>rhs</code> is greater than or equal to
     *     <code>lhs</code>
     * @throws NullPointerException
     *     if <code>lhs</code> or <code>rhs</code> is <code>null</code>
     */
    public static <T> boolean greaterThanOrEqualTo(
            final Comparable<T> lhs, final T rhs) {
        Assert.notNull("lhs", lhs);
        Assert.notNull("rhs", rhs);

        return lhs.compareTo(rhs) <= 0;
    }


    /**
     * Tests if <code>rhs</code> is less than or equal to <code>lhs</code>.
     *
     * @param lhs
     *     the comparable value
     * @param rhs
     *     the value to compare
     * @return
     *     true if <code>rhs</code> is less than or equal to
     *     <code>lhs</code>
     * @throws NullPointerException
     *     if <code>lhs</code> or <code>rhs</code> is <code>null</code>
     */
    public static <T> boolean lessThanOrEqualTo(
            final Comparable<T> lhs, final T rhs) {
        Assert.notNull("lhs", lhs);
        Assert.notNull("rhs", rhs);

        return lhs.compareTo(rhs) >= 0;
    }

    /**
     * Tests if <code>rhs</code> is greater than <code>lhs</code>.
     *
     * @param lhs
     *     the comparable value
     * @param rhs
     *     the value to compare
     * @return
     *     true if <code>rhs</code> is greater than <code>lhs</code>
     * @throws NullPointerException
     *     if <code>lhs</code> or <code>rhs</code> is <code>null</code>
     */
    public static <T> boolean greaterThan(
            final Comparable<T> lhs, final T rhs) {
        Assert.notNull("lhs", lhs);
        Assert.notNull("rhs", rhs);

        return lhs.compareTo(rhs) < 0;
    }

    /**
     * Tests if <code>rhs</code> is less than <code>lhs</code>.
     *
     * @param lhs
     *     the comparable value
     * @param rhs
     *     the value to compare
     * @return
     *     true if <code>rhs</code> is less than <code>lhs</code>
     * @throws NullPointerException
     *     if <code>lhs</code> or <code>rhs</code> is <code>null</code>
     */
    public static <T> boolean lessThan(
            final Comparable<T> lhs, final T rhs) {
        Assert.notNull("lhs", lhs);
        Assert.notNull("rhs", rhs);

        return lhs.compareTo(rhs) > 0;
    }

    /**
     * Null safe test for equals.  Tests if <code>rhs</code> is equal to
     * <code>lhs</code>.
     *
     * @param lhs
     *     left hand side comparison for equality
     * @param rhs
     *     right hand side comparison for equality
     * @return
     *     <code>true</code> if <code>lhs</code> and <code>rhs</code> test for
     *     equal with the equality operator or the <code>equals</code> method
     */
    public static boolean equal(final Object lhs, final Object rhs) {
        if (lhs == rhs) {
            return true;
        }

        if (lhs == null) {
            return false;
        }

        return lhs.equals(rhs);
    }

    /**
     * Null safe test for not equal.  Tests if <code>rhs</code> is not equal to
     * <code>lhs</code>.
     *
     * @param lhs
     *     left hand side comparison for equality
     * @param rhs
     *     right hand side comparison for equality
     * @return
     *     <code>true</code> if <code>lhs</code> and <code>rhs</code> do not
     *     test for equal with the equality operator or the <code>equals</code>
     *     method
     * @see #equal(Object,Object)
     */
    public static boolean notEqual(final Object lhs, final Object rhs) {
        return !Verify.equal(lhs, rhs);
    }
}
