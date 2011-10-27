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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public final class Xml {

    private Xml() { }

    /**
     * Given a string containing an XML document, returns <code>true</code>
     * if the XML is well-formed, <code>false</code> otherwise.
     *
     * @param xml
     *     the XML document
     * @return
     *     <code>true</code> if well-formed XML, <code>false</code> otherwise.
     */
    public static boolean isWellFormed(final String xml) {
        if (Verify.missing(xml)) {
            return false;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            builder.setErrorHandler(new DefaultHandler() {
                public void fatalError(final SAXParseException e) {
                }
            });
            Document doc =
                builder.parse(new ByteArrayInputStream(xml.getBytes()));
            return (doc != null);
        } catch (ParserConfigurationException e) {
            Xml.throwXmlException(e);
        } catch (SAXException e) {
            // Invalid XML, let it fall through
        } catch (IOException ioe) {
            Xml.throwXmlException(ioe);
        }

        return false;
    }

    private static void throwXmlException(final Exception cause) {
        throw new XmlException("Unable to parse the XML", cause);
    }
}
