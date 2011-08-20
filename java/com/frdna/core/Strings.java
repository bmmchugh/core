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

import java.util.Arrays;
import java.util.regex.Pattern;

import org.yaml.snakeyaml.Yaml;

public final class Strings {

    private Strings() { }

    /**
     * Escapes a string value for XML.
     *
     * @param value
     *     the string to be XML escaped
     * @return
     *     an XML escaped string
     */
    public static String escapeXml(final CharSequence value) {
        if (value == null) {
            return null;
        }

        return org.apache.commons.lang3.StringEscapeUtils.escapeXml(
                value.toString());
    }

    /**
     * Escapes a string value for CSV.
     *
     * @param value
     *     the string to be CSV escaped
     * @return
     *     a CSV escpaed string
     */
    public static String escapeCsv(final CharSequence value) {
        if (value == null) {
            return null;
        }

        return value.toString().replace("\"", "\"\"");
    }

    /**
     * Joins a group of values into a single delimited string.
     *
     * @param delimiter
     *     the string delimiter
     * @param values
     *     the group of values to be joined
     * @return
     *     a string with <code>values</code> delimited by <code>delimiter</code>
     */
    public static String join(
            final CharSequence delimiter,
            final Object... values) {
        return Strings.join(values, delimiter);
    }

    /**
     * Joins a group of values into a single delimited string.
     *
     * @param values
     *     the group of values to be joined
     * @param delimiter
     *     the string delimiter
     * @return
     *     a string with <code>values</code> delimited by <code>delimiter</code>
     */
    public static String join(
            final Object[] values,
            final CharSequence delimiter) {
        Assert.notNull("delimiter", delimiter);
        return org.apache.commons.lang3.StringUtils.join(
                values, delimiter.toString());
    }

    /**
     * Tests if a value is empty.
     *
     * Tests if a value is <code>null</code> or if the <code>String</code> value
     * of <code>value</code> is empty or whitespace.
     *
     * @param value
     *     the value to be tested
     * @return
     *     <code>true</code> if the value is <code>null</code> or whitespace
     */
    public static boolean isEmpty(final Object value) {
        if (value == null) {
            return true;
        }

        if (CharSequence.class.isInstance(value)) {
            return Pattern.matches("^\\s*$", (CharSequence) value);
        }

        return false;
    }

    /**
     * Tests if a value is present.
     *
     * Tests if a value is not <code>null</code> or if the <code>String</code>
     * value of <code>value</code> is not empty or whitespace.
     *
     * @param value
     *     the value to be tested
     * @return
     *     <code>true</code> if <code>value</code> is not <code>null</code> or a
     *     whitepace string
     */
    public static boolean isPresent(final Object value) {
        return !Strings.isEmpty(value);
    }

    /**
     * Generates a YAML <code>String</code> from an <code>Object</code>.
     *
     * @param o
     *     the object to be converted
     * @return
     *     a YAML formatted <code>String</code>
     */
    public static String toYaml(final Object o) {
        return new Yaml().dump(o);
    }

    /**
     * Loads an <code>Object</code> from a YAML <code>String</code>.
     *
     * @param yaml
     *     the YAML <code>String</code>
     * @return
     *     the <code>Object</code> converted from the YAML <code>String</code>
     */
    public static Object fromYaml(final CharSequence yaml) {
        Assert.notNull("yaml", yaml);
        return new Yaml().load(yaml.toString());
    }

    /**
     * Normalizes a URL.
     *
     * Converts a www.something.com URL to http://www.something.com.
     *
     * @param url
     *     the URL to normalize
     * @return
     *     <code>null</code> if <code>url</code> is empty or the normalized URL
     */
    public static String normalizeUrl(final CharSequence url) {
        if (Strings.isEmpty(url)) {
            return null;
        }

        String strUrl = url.toString();
        String forTesting = strUrl.trim().toLowerCase();
        if (forTesting.matches("^\\w+://.*")) {
            return strUrl;
        }

        return "http://" + strUrl;
    }

    /**
     * Replaces a number of characters at the beginning of a string.
     *
     * @param anyString
     *     the string to modify
     * @param replaceCount
     *     the number of characters to replace
     * @param replacement
     *     the character to replace in the string
     * @return
     *     <code>null</code> if <code>anyString</code> is empty or
     *     <code>anyString</code> with the characters replaced
     */
    public static String replaceLeft(
            final CharSequence anyString,
            final Number replaceCount,
            final Character replacement) {
        Assert.notNull("replaceCount", replaceCount);
        return Strings.replace(
                anyString,
                0,
                (replaceCount.intValue() - 1),
                replacement);
    }

    /**
     * Replaces all characters in a string.
     *
     * @param anyString
     *     the string to modify
     * @param replacement
     *     the character to replace in the string
     * @return
     *     <code>null</code> if <code>anyString</code> is empty or
     *     <code>anyString</code> with the characters replaced
     */
    public static String replaceAll(
            final CharSequence anyString, final Character replacement) {
        return Strings.replace(anyString, 0, -1, replacement);
    }

    /**
     * Replaces a number of characters at the end of a string.
     *
     * @param anyString
     *     the string to modify
     * @param replacement
     *     the character to replace in the string
     * @return
     *     <code>null</code> if <code>anyString</code> is empty or
     *     <code>anyString</code> with the characters replaced
     */
    public static String replaceRight(
            final CharSequence anyString,
            final Number replaceCount,
            final Character replacement) {
        Assert.notNull("replaceCount", replaceCount);
        return Strings.replace(
                anyString,
                -1,
                -(replaceCount.intValue()),
                replacement);
    }

    /**
     * Replaces all characters within a range.  If <code>startIndex</code> or
     * <code>endIndex</code> is negative, it is counted from the end of the
     * string.  For example:
     * <code>
     * replace("This is the String", 4, 7, '*')
     * </code> and
     * <code>
     * replace("This is the String", -11, -14, '*')
     * </code>
     * both produce the same result:
     * <code>
     * This****the String
     * </code>
     *
     * @param anyString
     *     the string to modify
     * @param startIndex
     *     the inclusive start index in the string to replace
     * @param endIndex
     *     the inclusive end index in the string to replace
     * @param replacement
     *     the character to replace in the range
     * @return
     *     <code>null</code> if <code>anyString</code> is empty or
     *     <code>anyString</code> with the characters replaced
     */
    public static String replace(
            final CharSequence anyString,
            final Number startIndex,
            final Number endIndex,
            final Character replacement) {

        if (Strings.isEmpty(anyString)) {
            return null;
        }

        Assert.notNull("startIndex",   startIndex);
        Assert.notNull("endIndex",     endIndex);
        Assert.notNull("replacement",  replacement);
        int length = anyString.length();
        int s = Strings.normalizeIndex(length, startIndex);
        int e = Strings.normalizeIndex(length, endIndex);

        int increment = 1;
        if (s > e) {
            increment = -1;
        }
        int lastIndex = e + increment;
        char[] tempArray = anyString.toString().toCharArray();
        for (int index = s; index != lastIndex; index += increment) {
            tempArray[index] = replacement;
        }

        return new String(tempArray);
    }

    /**
     * Normalizes and index so that is is not more then the last index of a
     * string or less than 0.  If the index is negative, it counted back from
     * the end of the string.
     *
     * @param length
     *     the length of the string
     * @param index
     *     the index to be normalized
     * @return
     *     the normalized index
     */
    private static int normalizeIndex(final Number length, final Number index) {
        Assert.notNull("length",  length);
        Assert.notNull("index",   index);
        int len = length.intValue();
        int idx = index.intValue();
        if (idx < 0) {
            // a negative index is subtracted from the length
            int i = len + idx;

            // zero is the minimum allowed index
            if (i < 0) {
                return 0;
            }

            return i;
        }

        // length - 1 is the maximum allowed index
        if (idx >= len) {
            return len - 1;
        }

        return idx;
    }

    /**
     * Removes any characters that are not alpha or numeric from a string.
     *
     * @param anyString
     *     the string to be modified
     * @return
     *     <code>null</code> when the string is null or has no alpha or numeric
     *     characters or the string with all non-alphanumeric characters removed
     */
    public static String alphaNumericOnly(final CharSequence anyString) {
        if (Strings.isEmpty(anyString)) {
            return null;
        }

        String justAlphaNumeric = anyString.toString().replaceAll(
                "[^a-zA-Z0-9]", "");

        if (justAlphaNumeric.equals("")) {
            return null;
        }

        return justAlphaNumeric;
    }

    /**
     * Removes starting characters from a string.
     *
     * @param str
     *     the string to be modified
     * @param trimChars
     *     the characters to trim from the start of the string
     * @return
     *     <code>str</code> with <code>trimChars</code> trimmed from the start
     */
    public static String trimLeft(final String str, final String trimChars) {
        return org.apache.commons.lang3.StringUtils.stripStart(
                str, trimChars);
    }

    /**
     * Removes starting characters from a string and returns <code>null</code>
     * if the result is an emtpy string.
     *
     * @param str
     *     ths string to be modified
     * @param trimChars
     *     the characters to trim from the start of the string
     * @return
     *     <code>str</code> with <code>trimChars</code> trimmed from the start
     *     or <code>null</code> if the result is an empty string
     */
    public static String trimLeftToNull(
            final String str,
            final String trimChars) {
        return Strings.nullIfEmpty(Strings.trimLeft(str, trimChars));
    }

    /**
     * Removes trailing characters from a string.
     *
     * @param str
     *     the string to be modified
     * @param trimChars
     *     the characters to trim from the end of the string
     * @return
     *     <code>str</code> with <code>trimChars</code> trimmed from the end
     */
    public static String trimRight(final String str, final String trimChars) {
        return org.apache.commons.lang3.StringUtils.stripEnd(
                str, trimChars);
    }

    /**
     * Removes trailing characters from a string and returns <code>null</code>
     * if the result is an emtpy string.
     *
     * @param str
     *     ths string to be modified
     * @param trimChars
     *     the characters to trim from the end of the string
     * @return
     *     <code>str</code> with <code>trimChars</code> trimmed from the end
     *     or <code>null</code> if the result is an empty string
     */
    public static String trimRightToNull(
            final String str,
            final String trimChars) {
        return Strings.nullIfEmpty(Strings.trimRight(str, trimChars));
    }

    /**
     * Removes trailing and starting whitespace and converts all alpha
     * characters to lowercase.
     *
     * @param value
     *     the value to be converted
     * @return
     *     <code>null</code> if <code>value</code> is empty or
     *     <code>value</code> with trailing and starting whitespace removed and
     *     alpha characters converted to lowercase
     */
    public static String trimLowerCase(final CharSequence value) {
        if (Strings.isEmpty(value)) {
            return null;
        }

        return value.toString().trim().toLowerCase();
    }

    /**
     * Truncates a string to a specified length.
     *
     * @param s
     *     the string to be truncated
     * @param length
     *     the length the truncated string should be
     * @return
     *     <code>null</code> if <code>s</code> is <code>null</code>,
     *     <code>s</code> if it is less than or equal to <code>length</code>, or
     *     <code>s</code> to <code>length</code> with the remainder trimmed
     */
    public static String truncate(final CharSequence s, final Number length) {
        if (s == null) {
            return null;
        }

        Assert.notNull("length", length);
        int l = length.intValue();

        if (s.length() <= l) {
            return s.toString();
        }

        return s.toString().substring(0, l);
    }

    /**
     * Joins a group of words in a string with underscores.
     *
     * @param value
     *     the group of words to join with underscores
     * @return
     *     <code>null</code> when <code>value</code> is empty or
     *     <code>value</code> with joined by underscores
     */
    public static String underscore(final CharSequence value) {
        if (Strings.isEmpty(value)) {
            return null;
        }

        String normalized = value.toString().replaceAll("([^a-zA-Z0-9])+", "_");

        String[] tokens =
                org.apache.commons.lang3.StringUtils
                        .splitByCharacterTypeCamelCase(normalized);
        int totalTokens = tokens.length;
        int counter = 1;
        StringBuffer output = new StringBuffer();
        for (String currentToken : tokens) {
            if (!currentToken.equals("_")) {
                output.append(currentToken.toLowerCase());
                if (counter < totalTokens) {
                    output.append('_');
                }
            }
            counter++;
        }
        String underscored = output.toString();
        underscored = underscored.replaceAll("_+$", "");
        underscored = underscored.replaceAll("^_+", "");
        if (Strings.isEmpty(underscored)) {
            return null;
        }

        return underscored;
    }

    /**
     * Returns <code>null</code> when <code>value</code> is empty.
     *
     * @param value
     *     the value to be tested for empty
     * @return
     *     <code>null</code> when <code>value</code> is empty or
     *     <code>value</code>
     */
    public static String nullIfEmpty(final CharSequence value) {
        if (Strings.isEmpty(value)) {
            return null;
        }

        return value.toString();
    }

    /**
     * Masks the value of a string with a specified character leaving a
     * specified number of characters clear.
     *
     * @param unmasked
     *     the unmasked value to be modified
     * @param maskCharacter
     *     the character to replace the hidden characters
     * @param unmakedLength
     *     the number of characters to be left clear
     * @return
     *     <code>unmasked</code> with characters replaced with
     *     <code>maskCharacter</code>
     */
    public static String mask(
            final CharSequence unmasked,
            final Character maskCharacter,
            final Number unmaskedLength) {
        if (unmasked == null) {
            return null;
        }

        Assert.notNull("maskCharacter",   maskCharacter);
        Assert.notNull("unmaskedLength",  unmaskedLength);

        int uml = unmaskedLength.intValue();

        if (unmasked.length() <= uml) {
            return unmasked.toString();
        }

        return Strings.replaceLeft(
                unmasked,
                unmasked.length() - uml,
                maskCharacter);
    }

    /**
     * Right justifies a string to a specified length.
     *
     * @param unjustified
     *     the string to be justified
     * @param justifyCharacter
     *     the character to pad
     * @param length
     *     the specified length of the justified string
     * @return
     *     <code>null</code> when <code>unjustified</code> is <code>null</code>
     *     <code>unjustified</code> when it is longer than <code>length</code>
     *     or <code>unjustified</code> right justified to <code>length</code>
     */
    public static String rightJustify(
            final CharSequence unjustified,
            final Character justifyCharacter,
            final Number length) {
        //if (unjustified == null) {
        //    return null;
        //}

        //if (unjustified.length() > length) {
        //    return unjustified.toString();
        //}

        //char[] pad = new char[length - unjustified.length()];
        //Arrays.fill(pad, justifyCharacter);
        //return new String(pad) + unjustified;
        return Strings.justify(unjustified, justifyCharacter, length, false);
    }

    /**
     * Left justifies a string to a specified length.
     *
     * @param unjustified
     *     the string to be justified
     * @param justifyCharacter
     *     the character to pad
     * @param length
     *     the specified length of the justified string
     * @return
     *     <code>null</code> when <code>unjustified</code> is <code>null</code>
     *     <code>unjustified</code> when it is longer than <code>length</code>
     *     or <code>unjustified</code> left justified to <code>length</code>
     */
    public static String leftJustify(
            final CharSequence unjustified,
            final Character justifyCharacter,
            final Number length) {
        //if (unjustified == null) {
        //    return null;
        //}

        //if (unjustified.length() > length) {
        //    return unjustified.toString();
        //}

        //char[] pad = new char[length - unjustified.length()];
        //Arrays.fill(pad, justifyCharacter);
        //return unjustified + new String(pad);
        return Strings.justify(unjustified, justifyCharacter, length, true);
    }

    /**
     * Justifies a string to a specified length.
     *
     * @param unjustified
     *     the string to be justified
     * @param justifyCharacter
     *     the character to pad
     * @param length
     *     the specified length of the justified string
     * @param leftJustify
     *     <code>true</code> when the string should be left justified
     * @return
     *     <code>null</code> when <code>unjustified</code> is <code>null</code>
     *     <code>unjustified</code> when it is longer than <code>length</code>
     *     or <code>unjustified</code> justified to <code>length</code>
     */
    private static String justify(
            final CharSequence unjustified,
            final Character justifyCharacter,
            final Number length,
            final Boolean leftJustify) {
        if (unjustified == null) {
            return null;
        }

        Assert.notNull("justifyCharacter",  justifyCharacter);
        Assert.notNull("length",            length);
        Assert.notNull("leftJustify",       leftJustify);

        int l = length.intValue();
        if (unjustified.length() > l) {
            return unjustified.toString();
        }

        char[] pad = new char[l - unjustified.length()];
        Arrays.fill(pad, justifyCharacter);
        if (leftJustify) {
            return unjustified + new String(pad);
        }

        return new String(pad) + unjustified;
    }

    /**
     * Left justifies a string with spaces.
     *
     * @param value
     *     the string to be padded
     * @param length
     *     the length of the padded string
     * @return
     *     <code>value</code> right padded with spaces
     * @see #leftJustify(CharSequence,Character,Number)
     */
    public static String padRight(CharSequence value, int length) {
        return Strings.leftJustify(value, ' ', length);
    }
}
