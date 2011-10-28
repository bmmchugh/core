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

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import com.frdna.loginator.Log;

public final class Io {

    private static final long serialVersionUID = 6163685685362618031L;

    private Io() { }

    /**
     * Closes a <code>Closable</code> object and logs a warning if an exception
     * is thrown.
     *
     * @param closable
     *      the <code>Closable</code> object to be closed
     */
    public static void closeQuietly(final Closeable closeable) {

        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            Log.warn(Io.class, e, "Failed to close %s", closeable);
        }
    }

    /**
     * Reads the contents of the file identified by <code>fileName</code> to a
     * string.
     *
     * @param fileName
     *     the name of the file to read
     * @return
     *     the <code>String</code> contents of the file, or <code>null</code> if
     *     the file is not found
     *  @see #read(File)
     */
    public static String read(final String fileName) {
        return Io.read(fileName, Charset.defaultCharset());
    }

    /**
     * Reads the contents of the file identified by <code>fileName</code> to a
     * string.
     *
     * @param fileName
     *     the name of the file to read
     * @param charset
     *     the name of the charset to use to decode the contents
     * @return
     *     the <code>String</code> contents of the file, or <code>null</code> if
     *     the file is not found
     *  @see #read(File)
     */
    public static String read(final String fileName, final String charset) {
        return Io.read(fileName, Charset.forName(charset));
    }

    /**
     * Reads the contents of the file identified by <code>fileName</code> to a
     * string.
     *
     * @param fileName
     *     the name of the file to read
     * @param charset
     *     the charset to use to decode the content
     * @return
     *     the <code>String</code> contents of the file, or <code>null</code> if
     *     the file is not found
     * @throws NullPointerException
     *     if <code>charset</code> is <code>null</code>
     * @see #read(File,Charset)
     */
    public static String read(final String fileName, final Charset charset) {

        if (fileName == null) {
            return null;
        }

        return Io.read(new File(fileName), charset);
    }

    /**
     * Reads the contents of the file to a string.
     *
     * @param file
     *     the file to be read
     * @return
     *     the <code>String</code> contents fo the file, or <code>null</code> if
     *     the file is not found or is <code>null</code>
     * @see #read(File,Charset)
     */
    public static String read(final File file) {

        return Io.read(file, Charset.defaultCharset());
    }

    /**
     * Reads the contents of the file to a string.
     *
     * @param file
     *     the file to be read
     * @param charset
     *     the name of the charset to use to decode the content
     * @return
     *     the <code>String</code> contents of the file, or <code>null</code> if
     *     the file is not found
     * @throws NullPointerException
     *     if <code>charset</code> is <code>null</code>
     * @see #read(File,Charset)
     */
    public static String read(final File file, final String charset) {
        return Io.read(file, Charset.forName(charset));
    }

    /**
     * Reads the contents of the file to a string.
     *
     * @param file
     *     the file to be read
     * @param charset
     *     the charset to use to decode the content
     * @return
     *     the <code>String</code> contents fo the file, or <code>null</code> if
     *     the file is not found or is <code>null</code>
     * @throws NullPointerException
     *     if <code>charset</code> is <code>null</code>
     */
    public static String read(final File file, final Charset charset) {

        Assert.notNull("charset", charset);

        if (file == null) {
            return null;
        }

        if (!file.exists()) {
            return null;
        }

        Reader reader = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            reader = Channels.newReader(
                    fileChannel, charset.newDecoder(), 1024);
            return Io.read(reader);
        } catch (IOException e) {
            throw new IoException("Unable to read " + file, e);
        } finally {
            Io.closeQuietly(reader);
        }
    }

    /**
     * Reads the contents from <code>reader</code> into a <code>String</code>.
     *
     * @param reader
     *     the reader the contents will be read from
     * @return
     *     the <code>String</code> value read from <code>reader</code>
     * @throws IOException
     *     if <code>reader</code> throws an exception
     */
    private static String read(final Reader reader) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        int read = 0;
        char[] buffer = new char[1024];

        do {
            read = reader.read(buffer);
            if (read > 0) {
                stringBuilder.append(buffer, 0, read);
            }
        } while (read != -1);

        return stringBuilder.toString();
    }
}
