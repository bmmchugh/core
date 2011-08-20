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

class StringsTest extends TestCase {

    @Test void escapeXmlShouldBeNullWhenValueIsNull() {
        assert Strings.escapeXml(null) == null
    }

    @Test void escapeXmlShouldEscapeForXml() {
        assert Strings.escapeXml("1 < 2 && 4 > 3") ==
            "1 &lt; 2 &amp;&amp; 4 &gt; 3"
    }

    @Test void escapeCsvShouldBeNullWhenValueIsNull() {
        assert Strings.escapeCsv(null) == null
    }

    @Test void escapeCsvShoudEscapeForCsv() {
        assert Strings.escapeCsv(
                "She says, \"She saw sea shells by the sea shore\"") ==
            "She says, \"\"She saw sea shells by the sea shore\"\""
    }

    @Test void joinShouldBeEmptyWithNoValues() {
        assert Strings.join(",") == ""
    }

    @Test void joinShouldBeTheSingleValueWithOneValue() {
        assert Strings.join(" : ", "Value") == "Value"
    }

    @Test void joinShouldDelimitValues() {
        assert Strings.join(" > ", "Value 1", "Value 2") == "Value 1 > Value 2"
    }

    @Test void joinShouldConvertAnObjectToString() {
        def object = new Object() {
            public String toString() {
                "Value 3";
            }
        }

        assert Strings.join("|", "Value 1", "Value 2", object) ==
        "Value 1|Value 2|Value 3"
    }

    @Test void joinShouldThrowExceptionWhenDelimiterIsNull() {
        assertException NullPointerException.class,
                        { Strings.join(null, "Value 1", "Value 2", "Value 3") }
    }

    @Test void isEmptyShouldBeTrueWhenValueIsNull() {
        assert Strings.isEmpty(null)
    }

    @Test void isEmptyShouldBeTrueWhenValueIsEmpty() {
        assert Strings.isEmpty("")
    }

    @Test void isEmptyShouldBeTrueWhenValueIsWhitespace() {
        assert Strings.isEmpty("\t")
    }

    @Test void isEmptyShouldBeFalseWhenValueHasCharacters() {
        assert !Strings.isEmpty("Value")
    }

    @Test void isEmptyShouldBeFalseWhenValueIsANonNullObject() {
        assert !Strings.isEmpty(new Object())
    }

    @Test void isPresentShouldBeFalseWhenValueIsNull() {
        assert !Strings.isPresent(null)
    }

    @Test void isPresentShouldBeFalseWhenValueIsEmpty() {
        assert !Strings.isPresent("")
    }

    @Test void isPresentShouldBeFalseWhenValueIsWhitespace() {
        assert !Strings.isPresent("\n")
    }

    @Test void isPresentShouldBeTrueWhenValueHasCharacters() {
        assert Strings.isPresent("Value")
    }

    @Test void toYamlShouldConvertAMap() {
        def map = [ "firstName" : "Joe", "lastName" : "Shmoe" ]
        assert Strings.toYaml(map) == "{firstName: Joe, lastName: Shmoe}\n"
    }

    @Test void toYamlShouldConvertAnObject() {
        def person = new Person();
        person.setFirstName('Joe')
        person.setLastName('Shmoe')
        assert Strings.toYaml(person) ==
        "!!com.frdna.core.Person {firstName: Joe, lastName: Shmoe}\n"
    }

    @Test void fromYamlShouldConvertAYamlString() {
       assert [ "Value 1", "Value 2", "Value 3" ] ==
              Strings.fromYaml('''
                  - Value 1
                  - Value 2
                  - Value 3
              ''')
    }

    @Test void fromYamlShouldLoadAnObject() {
        def person = (Person) Strings.fromYaml(
          '!!com.frdna.core.Person {firstName: Jake, lastName: Snake}')
        assert Person.class.isInstance(person)
        assert person.getFirstName() == 'Jake'
        assert person.getLastName() == 'Snake'
    }

    @Test void normalizeUrlShouldBeNullWhenValueIsNull() {
        assert Strings.normalizeUrl(null) == null
    }

    @Test void normalizeUrlShouldBeNullWhenValueIsEmpty() {
        assert Strings.normalizeUrl("") == null
    }

    @Test void normalizeUrlShouldBeNullWhenValueIsWhitespace() {
        assert Strings.normalizeUrl("\t") == null
    }

    @Test void normalizeUrlShouldAddHttpProtocol() {
        assert Strings.normalizeUrl("www.something.com") ==
               "http://www.something.com"
    }

    @Test void normalizeUrlShouldKeepHttpProtocol() {
        assert Strings.normalizeUrl("http://www.something.com") ==
               "http://www.something.com"
    }

    @Test void normalizeUrlShouldKeepHttpsProtocol() {
        assert Strings.normalizeUrl("https://www.something.com") ==
               "https://www.something.com"
    }

    @Test void normalizeUrlShouldKeepFtpProtocol() {
        assert Strings.normalizeUrl("ftp://www.something.com") ==
               "ftp://www.something.com"
    }

    @Test void replaceLeftShouldBeNullWhenValueIsNull() {
        assert Strings.replaceLeft(null, 1, (char)'-') == null
    }

    @Test void replaceLeftShouldBeNullWhenValueIsEmpty() {
        assert Strings.replaceLeft("", 10, (char)'-') == null
    }

    @Test void replaceLeftShouldBeNullWhenValueIsWhitespace() {
        assert Strings.replaceLeft("\t", 10, (char)'-') == null
    }

    @Test void replaceLeftShouldReplaceTheSpecifiedNumberOfCharacters() {
        assert Strings.replaceLeft("This is the String", 9, (char)'-') ==
                                   "---------he String"
    }

    @Test void replaceLeftShouldReplaceAllCharactersWhenNumberIsSize() {
        assert Strings.replaceLeft("This is the String", 18, (char)'-') ==
                                   "------------------"
    }

    @Test void replaceLeftShouldReplaceAllCharactersWhenNumberIsMore() {
        assert Strings.replaceLeft("This is the String", 19, (char)'-') ==
                                   "------------------"
    }

    @Test void replaceAllShouldBeNullWhenValueIsNull() {
        assert Strings.replaceAll(null, (char)'-') == null
    }

    @Test void replaceAllShouldBeNullWhenValueIsEmpty() {
        assert Strings.replaceAll("", (char)'-') == null
    }

    @Test void replaceAllShouldBeNullWhenValueIsWhitespace() {
        assert Strings.replaceAll("\t", (char)'-') == null
    }

    @Test void replaceAllShouldReplaceAllCharacters() {
        def s = "This is the String"
        assert Strings.replaceAll(s, (char)'-') ==
                                  "------------------"
    }

    @Test void replaceRightShouldBeNullWhenValueIsNull() {
        assert Strings.replaceRight(null, 1, (char)'-') == null
    }

    @Test void replaceRightShouldBeNullWhenValueIsEmpty() {
        assert Strings.replaceRight("", 10, (char)'-') == null
    }

    @Test void replaceRightShouldBeNullWhenValueIsWhitespace() {
        assert Strings.replaceRight("\t", 10, (char)'-') == null
    }

    @Test void replaceRightShouldReplaceTheSpecifiedNumberOfCharacters() {
        assert Strings.replaceRight("This is the String", 9, (char)'-') ==
                                   "This is t---------"
    }

    @Test void replaceRightShouldReplaceAllCharactersWhenNumberIsSize() {
        assert Strings.replaceRight("This is the String", 18, (char)'-') ==
                                   "------------------"
    }

    @Test void replaceRightShouldReplaceAllCharactersWhenNumberIsMore() {
        assert Strings.replaceRight("This is the String", 19, (char)'-') ==
                                   "------------------"
    }

    @Test void replaceShouldBeNullWhenValueIsNull() {
        assert Strings.replace(null, 4, 7, (char)'-') == null
    }

    @Test void replaceShouldBeNullWhenValueIsEmpty() {
        assert Strings.replace("", 4, 7, (char)'-') == null
    }

    @Test void replaceShouldBeNullWhenValueIsWhitespace() {
        assert Strings.replace("\t", 4, 7, (char)'-') == null
    }

    @Test void replaceShouldReplaceTheSpecifiedNumberOfCharacters() {
        assert Strings.replace("This is the String", 4, 7, (char)'-') ==
                               "This----the String"
    }

    @Test void replaceShouldReplaceTheSpecifiedNumberOfCharactersWithNeg() {
        assert Strings.replace("This is the String", -11, -14, (char)'-') ==
                               "This----the String"
    }

    @Test void replaceShouldReplaceOneCharacterWhenIndexIsSame() {
        assert Strings.replace("This is the String", 4, 4, (char)'-') ==
                               "This-is the String"
    }

    @Test void replaceShouldReplaceOneCharacterPosAndNegIndexAreSame() {
        assert Strings.replace("This is the String", 4, -14, (char)'-') ==
                               "This-is the String"
    }

    @Test void replaceShouldReplaceAllCharactersWhenIndexesAreSize() {
        assert Strings.replace("This is the String", 0, 17, (char)'-') ==
                               "------------------"
    }

    @Test void replaceShouldReplaceAllCharactersWhenNegIndexesAreSize() {
        assert Strings.replace("This is the String", -1, -18, (char)'-') ==
                               "------------------"
    }

    @Test void replaceShouldReplaceAllCharactersWhenIndexesAreMore() {
        assert Strings.replace("This is the String", -19, 19, (char)'-') ==
                               "------------------"
    }

    @Test void alphaNumericOnlyShouldBeNullWhenValueIsNull() {
        assert Strings.alphaNumericOnly(null) == null
    }

    @Test void alphaNumericOnlyShouldBeNullWhenValueIsEmpty() {
        assert Strings.alphaNumericOnly("") == null
    }

    @Test void alphaNumericOnlyShouldBeNullWhenValueIsWhitespace() {
        assert Strings.alphaNumericOnly(" ") == null
    }

    @Test void alphaNumericOnlyShouldBeNullForNoAlphanumericFound() {
        assert Strings.alphaNumericOnly('!@#$%^&*()-+=') == null
    }

    @Test void alphaNumericOnlyShouldRemoveNonAlphaNumerics() {
        assert Strings.alphaNumericOnly("testing 1, 2, 3...") == "testing123"
    }

    @Test void trimLeftShouldBeNullWhenTheValueIsNull() {
        assert Strings.trimLeft(null, 'xyz') == null
    }

    @Test void trimLeftShouldBeNullWhenValueAndTrimCharsAreNull() {
        assert Strings.trimLeft(null, null) == null
    }

    @Test void trimLeftShouldBeNoChangeWhenTrimCharsAreNull() {
        assert Strings.trimLeft('000123', null) == '000123'
    }

    @Test void trimLeftShouldTrimCharactersFromTheStartOfAString() {
        assert Strings.trimLeft("000123", "0") == "123"
    }

    @Test void trimLeftShouldTrimToEmpty() {
        assert Strings.trimLeft("0000", "0") == ""
    }

    @Test void trimLeftToNullShouldTrimCharactersFromTheStartOfAString() {
        assert Strings.trimLeftToNull("000123", "0") == "123"
    }

    @Test void trimLeftToNullShouldTrimToNull() {
        assert Strings.trimLeftToNull("0000", "0") == null
    }

    @Test void trimRightShouldBeNullWhenTheValueIsNull() {
        assert Strings.trimRight(null, 'xyz') == null
    }

    @Test void trimRightShouldBeNullWhenValueAndTrimCharsAreNull() {
        assert Strings.trimRight(null, null) == null
    }

    @Test void trimRightShouldBeNoChangeWhenTrimCharsAreNull() {
        assert Strings.trimRight('123000', null) == '123000'
    }

    @Test void trimRightShouldTrimCharactersFromTheEndOfAString() {
        assert  Strings.trimRight("123000", "0") == "123"
    }

    @Test void trimRightShouldTrimToEmpty() {
        assert Strings.trimRight("0000", "0" )== ""
    }

    @Test void trimRightToNullShouldTrimCharactersFromTheEndOfAString() {
        assert Strings.trimRightToNull("123000", "0") == "123"
    }

    @Test void trimRightToNullShouldTrimToNull() {
        assert Strings.trimRightToNull("0000", "0") == null
    }

    @Test void trimLowerCaseShouldBeNullWhenValueIsNull() {
        assert Strings.trimLowerCase(null) == null
    }

    @Test void trimLowerCaseShouldBeNullWhenValueIsEmpty() {
        assert Strings.trimLowerCase("") == null
    }

    @Test void trimLowerCaseShouldBeNullWhenValueIsWhitespace() {
        assert Strings.trimLowerCase(" \t\r\n") == null
    }

    @Test void trimLowerCaseShouldBeTheValueWhenNoWsOrUpperCase() {
        assert Strings.trimLowerCase('nothing to change here') ==
               'nothing to change here'
    }

    @Test void trimLowerCaseShouldTrimStartingWhitespace() {
        assert Strings.trimLowerCase(" \t\r\ntrim starting.") ==
               'trim starting.'
    }

    @Test void trimLowerCaseShouldTrimEndingWhitespace() {
        assert Strings.trimLowerCase("trim ending. \t\r\n") ==
               'trim ending.'
    }

    @Test void trimLowerCaseShouldConvertToLowerCase() {
        assert Strings.trimLowerCase('cOnVeRt To LoWeR cAse!') ==
               'convert to lower case!'
    }

    @Test void truncateShouldTruncateToTheSpecifiedLength() {
        assert Strings.truncate("1234567890123456789", 10) == "1234567890"
    }

    @Test void truncateShouldNotTruncateWhenTheSpecifiedLengthIsLess() {
        assert Strings.truncate("1234567890", 11) == "1234567890"
    }

    @Test void truncateShouldBeNullWhenStringIsNull() {
        assert Strings.truncate(null, 11) == null
    }

    @Test void underscoreShouldReturnNulWhenfNullProvided() {
        assert Strings.underscore(null) == null
    }

    @Test void underscoreShouldReturnNullWhenWhitespaceProvided() {
        assert Strings.underscore(" ") == null
    }

    @Test void underscoreShouldNotChangeStringsWithNoSpecialCharactersOrCaps() {
        assert Strings.underscore("nothingtochangehere") =="nothingtochangehere"
    }

    @Test void underscoreShouldReturnALowercaseString() {
        assert Strings.underscore("SHOULDBELOWERCASE") == "shouldbelowercase"
    }

    @Test void underscoreShouldAddUnderscoresPrecedingInternalCaps() {
        assert Strings.underscore("ThisHasBeenProperlyUnderscored") ==
               "this_has_been_properly_underscored"
    }

    @Test void underscoreShouldReplaceSpecialCharacterSequences() {
        assert Strings.underscore("This has been properly underscored!!!") ==
               "this_has_been_properly_underscored"
    }

    @Test void underscoreShouldReturnNullWhenOnlySpecialCharactersProvided() {
        assert Strings.underscore('!#$!  __') == null
    }

    @Test void underscoreShouldNotChangeProperlyUnderscoredStrings() {
        assert Strings.underscore("this_is_properly_underscored") ==
               "this_is_properly_underscored"
    }

    @Test void nullIfEmptyShouldReturnTheStringWhenItIsNotEmpty() {
        def str = "  str  ";
        assert Strings.nullIfEmpty(str) == str
    }

    @Test void nullIfEmptyShouldReturnNullWhenTheStringIsEmpty() {
        assert Strings.nullIfEmpty("       ") == null
    }

    @Test void nullIfEmptyShouldReturnNullWhenTheStringIsNull() {
        assert Strings.nullIfEmpty(null) == null
    } 

    @Test void maskShouldReturnNullWhenUnmaskedIsNull() {
        assert Strings.mask(null, (char)'*', 4) == null
    }

    @Test void maskShouldReturnUnmaskedValueWhenLessThanUnmaskedChars() {
        assert Strings.mask("xyz", (char)'*', 4) == "xyz"
    }

    @Test void maskShouldMaskWithSpecifiedCharacter() {
        assert Strings.mask("xyz123", (char)'*', 4) == "**z123"
    }

    @Test void rightJustifyShouldReturnNullWhenNull() {
        assert Strings.rightJustify(null, (char)'>', 20) == null
    }

    @Test void rightJustifyShouldReturnUnjustifiedWhenLongerThanLength() {
        assert Strings.rightJustify("xyz123", (char)'>', 3) == "xyz123"
    }

    @Test void rightJustifyShouldJustifyWithCharacter() {
        assert Strings.rightJustify("xyz123", (char)'>', 20) ==
                                    ">>>>>>>>>>>>>>xyz123"
    }

    @Test void leftJustifyShouldReturnNullWhenNull() {
        assert Strings.leftJustify(null, (char)'>', 20) == null
    }

    @Test void leftJustifyShouldReturnUnjustifiedWhenLongerThanLength() {
        assert Strings.leftJustify("xyz123", (char)'>', 3) == "xyz123"
    }

    @Test void leftJustifyShouldJustifyWithCharacter() {
        assert Strings.leftJustify("xyz123", (char)'>', 20) ==
                                    "xyz123>>>>>>>>>>>>>>"
    }

    @Test void padRightShouldPadAValueToASpecifiedNumberOfSpaces() {
        assert Strings.padRight("xyz123", 9) ==  "xyz123   "
    }

    @Test void padRightShouldReturnTheValueWhenEqualSpecifiedLength() {
        assert Strings.padRight("xyz123", 6) == "xyz123"
    }

    @Test void padRightShouldReturnTheValueIfLessSpecifiedLength() {
        assert Strings.padRight("xyz123", 5) == "xyz123"
    }

    @Test void padRightShouldReturnNullWhenValueIsNull() {
        assert Strings.padRight(null, 5) == null
    }
}
