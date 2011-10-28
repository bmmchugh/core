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

import org.junit.Test;

class IoTest extends TestCase {

    @Test void closeQuitelyShouldReturnWhenCloseableIsNull() {
        assertNoException {Io.closeQuietly(null)}
    }

    @Test void closeQuietlyShouldCloseClosable() {
        def closed = false
        def closable =
          [ close: { closed = true }, isClosed: false ] as Closeable
        Io.closeQuietly(closable)
        assert closed == true
    }

    @Test void readShouldBeNullWhenFileIsNull() {
        assert Io.read((File)null) == null
    }

    @Test void readShouldBeNullWhenFileIsNotFound() {
        assert Io.read(new File("file_not_found.txt")) == null
    }

    @Test void readShouldReadTheContentsOfTheFile() {
        assert Io.read(new File("test/resources/test_file.txt")).equals(
            "Test file contents.\n")
    }

    @Test void readShouldReadTheContentsOfTheFileForTheCharset() {
        assert Io.read(new File("test/resources/test_file.ebcdic"),
            "Cp037").equals("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    }

    @Test void readShouldBeNullWhenStringIsNull() {
        assert Io.read((String)null) == null
    }

    @Test void readShouldReadTheOfTheFileSpecifiedByTheName() {
        assert Io.read("test/resources/test_file.txt").equals(
            "Test file contents.\n")
    }

    @Test void readShouldReadTheOfTheFileSpecifiedByTheNameForTheCharset() {
        assert Io.read("test/resources/test_file.ebcdic", "Cp037").equals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    }
}
