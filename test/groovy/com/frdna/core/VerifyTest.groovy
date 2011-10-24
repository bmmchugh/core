package com.frdna.core;

import java.math.BigDecimal;

import org.junit.Test;

class VerifyTest extends TestCase {

    static final ALPHA_ONLY =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    @Test void alphaShouldBeFalseWhenValueIsNull() {
        assert !Verify.alpha(null)
    }

    @Test void alphaShouldBeFalseWhenValueIsEmptyString() {
        assert !Verify.alpha("")
    }

    @Test void alphaShouldBeFalseWhenValueHasWhitespace() {
        assert !Verify.alpha("\t" + ALPHA_ONLY)
    }

    @Test void alphaShouldBeFalseWhenValueHasNumeric() {
        assert !Verify.alpha("0" + ALPHA_ONLY)
    }

    @Test void alphaShouldBeTrueWhenValueIsAlphaOnly() {
        assert Verify.alpha(ALPHA_ONLY)
    }

    @Test void alphaNumericShouldBeFalseWhenValueIsNull() {
        assert !Verify.alphaNumeric(null)
    }

    @Test void alphaNumericShouldBeFalseWhenValueIsWhitespace() {
        assert !Verify.alphaNumeric(" ")
    }

    @Test void alphaNumerisShouldBeFalseWhenSpecialCharactersIncluded() {
        assert !Verify.alphaNumeric("!!!definitely not alphanumeric!!!")
    }

    @Test void alphaNumericShouldBeTrueWhenValueIsAlphanumeric() {
        assert Verify.alphaNumeric(
               "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
    }

    @Test void alphaNumericShouldBeFalseWhenWhitspaceBetweenValidSubstrings() {
        assert !Verify.alphaNumeric("valid except for spaces")
    }

    @Test void meaningfulShouldBeFalseWhenValueIsNull() {
        assert !Verify.meaningful(null)
    }

    @Test void meaningfulShouldBeFalseWhenValueIsEmpty() {
        assert !Verify.meaningful("")
    }

    @Test void meaningfulShouldBeTrueWhenOneAlpha() {
        assert Verify.meaningful("a")
    }

    @Test void meaningfulShouldBeTrueWhenOneNumeric() {
        assert Verify.meaningful("1")
    }

    @Test void meaningfulShouldBeFalseWhenNoAlphaOrNumeric() {
        assert !Verify.meaningful(' !&$!)*     {} ')
    }

    @Test void notAllNullShouldBeFalseWhenArgsIsSingleNullValue() {
        assert !Verify.notAllNull((Object[]) null)
    }

    @Test void notAllNullShouldBeTrueWhenAnyArgumentIsPresent() {
        assert Verify.notAllNull(null, "chickens", null)
    }

    @Test void notAllNullShouldBeTrueWhenSingleArgumentIsPresent() {
        assert Verify.notAllNull("chickens")
    }

    @Test void notAllNullShouldBeTrueWhenArgumentIsSingleWhitespace() {
        assert Verify.notAllNull(" ")
    }

    @Test void notNullShouldBeFalseWhenArgumentIsSingleNull() {
        assert !Verify.notNull((Object[]) null)
    }

    @Test void notNullShouldBeFalseWhenAnyArgumentIsNull() {
        assert !Verify.notNull("chickens", "monkeys", null)
    }

    @Test void notNullShouldBeTrueWhenArgumentIsSinglePresent() {
        assert Verify.notNull("chickens")
    }

    @Test void notNullShouldBeTrueWhenArgumentIsWhitespaceString() {
        assert Verify.notNull(" ")
    }

    @Test void presentShouldBeFalseWhenArgsIsNull() {
        assert !Verify.present(null)
    }

    @Test void presentShouldBeFalseWhenArgsIsSingleNull() {
        assert !Verify.present((Object[]) null)
    }

    @Test void presentShouldBeFalseWhenArgsHasAnyNull() {
        assert !Verify.present(new Object(), "Value", null)
    }

    @Test void presentShouldBeFalseWhenArgsHasAnyEmptyString() {
        assert !Verify.present(new Object(), "Value", "")
    }

    @Test void presentShouldBeFalseWhenArgsHasAnyWhitespace() {
        assert !Verify.present(new Object(), "Value", " \t\r\n")
    }

    @Test void presentShouldBeTrueWhenArgsHasAllPresentValues() {
        assert Verify.present(new Object(), "Value 1", "And Two")
    }

    @Test void missingShouldBeTrueWhenArgsIsNull() {
        assert Verify.missing(null)
    }

    @Test void missingShouldBeTrueWhenArgsIsSingleNull() {
        assert Verify.missing((Object[]) null)
    }

    @Test void missingShouldBeTrueWhenArgsHasAnyNull() {
        assert Verify.missing(new Object(), "Value", null)
    }

    @Test void missingShouldBeTrueWhenArgsHasAnyEmptyString() {
        assert Verify.missing(new Object(), "Value", "")
    }

    @Test void missingShouldBeTrueWhenArgsHasAnyWhitespace() {
        assert Verify.missing(new Object(), "Value", " \t\r\n")
    }

    @Test void missingShouldBeFalseWhenArgsHasAllPresentValues() {
        assert !Verify.missing(new Object(), "Value 1", "And Two")
    }

    @Test void emailShouldBeFalseWhenValueIsNull() {
        assert !Verify.email(null)
    }

    @Test void emailShouldBeFalseWhenValueIsWhitespace() {
        assert !Verify.email(" \t\r\n")
    }

    @Test void emailShouldBeFalseWhenValueIsInvalidEmail() {
        assert !Verify.email("definitelynotvalid")
    }

    @Test void emailShouldBeTrueWhenValueIsValidEmail() {
        assert Verify.email("a@b.com")
    }

    @Test void integerNumericShouldBeFalseWhenValueIsNull() {
        assert !Verify.integerNumeric(null)
    }

    @Test void integerNumericShouldBeFalseWhenValueIsEmpty() {
        assert !Verify.integerNumeric("")
    }

    @Test void integerNumericShouldBeFalseWhenValueIsWhitespace() {
        assert !Verify.integerNumeric(" \t\r\n")
    }

    @Test void integerNumericShouldBeTrueWhenValueIsOnlyNumerics() {
        assert Verify.integerNumeric("123456789")
    }

    @Test void integerNumericShouldBeTrueWhenValueIsNegativeNumerics() {
        assert Verify.integerNumeric("-123456789")
    }

    @Test void integerNumericShouldBeFalseWhenValueIsDecimal() {
        assert !Verify.integerNumeric("1234.567")
    }

    @Test void integerNumericShouldBeFalseWhenValueHasAlpha() {
        assert !Verify.integerNumeric("1234as567")
    }

    @Test void integerNumericShouldBeFalseWhenValueHasSpecialCharacters() {
        assert !Verify.integerNumeric('1234#$%^5678')
    }

    @Test void integerNumericShouldBeFalseWhenValueHasWhitespaceCharacters() {
        assert !Verify.integerNumeric("  1234567\t")
    }

    @Test void regexShouldBeFalseWhenValueAndRegexAreNull() {
        assert !Verify.regex(null, null)
    }

    @Test void regexShouldBeFalseWhenValueIsNull() {
        assert !Verify.regex(null, ".*")
    }

    @Test void regexShouldBeFalseWhenRegexIsNull() {
        assert !Verify.regex("TestMe", null)
    }

    @Test void regexShouldBeFalseWhenValueAndRegexAreEmpty() {
        assert !Verify.regex("", "")
    }

    @Test void regexShouldBeFalseWhenValueIsEmpty() {
        assert !Verify.regex("", ".*")
    }

    @Test void regexShouldBeFalseWhenRegexIsEmpty() {
        assert !Verify.regex("TestMe", "")
    }

    @Test void regexShouldBeFalseWhenValueAndRegexAreWhitespace() {
        assert !Verify.regex(" \t\r\n", " \t\r\n")
    }

    @Test void regexShouldBeFalseWhenValueIsWhitespace() {
        assert !Verify.regex(" \t\r\n", ".*")
    }

    @Test void regexShouldBeFalseWhenRegexIsWhitespace() {
        assert !Verify.regex("TestMe", " \t\r\n")
    }

    @Test void regexShouldBeFalseWhenRegexDoesntMatch() {
        assert !Verify.regex("aaaaaa", "[b]*")
    }

    @Test void regexShouldBeTrueWhenRegexMatches() {
        assert Verify.regex("abc123", "[321cba]{6}")
    }

    @Test void urlShouldBeFalseWhenValueIsNull() {
        assert !Verify.url(null)
    }

    @Test void urlShouldBeFalseWhenValueIsEmptyString() {
        assert !Verify.url("")
    }

    @Test void urlShouldBeFalseWhenValueIsWhitespace() {
        assert !Verify.url("\t \r\n")
    }

    @Test void urlShouldBeFalseWhenDomainIsMissingSuffix() {
        assert !Verify.url("definitelynotvalid")
    }

    @Test void urlShouldBeFalseWhenDomainIsReverse() {
        assert !Verify.url("com.backwards")
    }

    @Test void urlShouldBeFalseWhenDomainHasExtraPoint() {
        assert !Verify.url("www.almostgood..com")
    }

    @Test void urlShouldBeFalseWhenValueHasOneBackslash() {
        assert !Verify.url("http:/www.something.com")
    }

    @Test void urlShouldBeTrueWhenValueHasSchemeAndDomain() {
        assert Verify.url("http://www.something.com")
    }

    @Test void urlShouldBeTrueWhenValueHasDomain() {
        assert Verify.url("www.something.com")
    }

    @Test void urlShouldBeTrueWhenDomainIsUpperCase() {
        assert Verify.url("WWW.SOMETHING.COM")
    }

    @Test void urlShouldBeTrueWhenSuffixIsNet() {
        assert Verify.url("www.something.net")
    }

    @Test void urlShouldBeTrueWhenSuffixIsOrg() {
        assert Verify.url("www.something.org")
    }

    @Test void urlShouldBeTrueWhenPrefixIsMissing() {
        assert Verify.url("something.com")
    }

    @Test void urlShouldBeTrueWhenPrefixIsNotWww() {
        assert Verify.url("somerandomapp.something.com")
    }

    @Test void urlShouldBeTrueWhenValueHasPath() {
        assert Verify.url("www.something.com/some/restian/path")
    }

    @Test void hexColorShouldBeFalseWhenValueIsNull() {
        assert !Verify.hexColor(null)
    }

    @Test void hexColorShouldBeFalseWhenValueIsEmptyString() {
        assert !Verify.hexColor("")
    }

    @Test void hexColorShouldBeFalseWhenValueIsWhitespace() {
        assert !Verify.hexColor("\t \r\n")
    }

    @Test void hexColorShouldBeTrueWhenValueIs6() {
        assert Verify.hexColor("000000")
    }

    @Test void hexColorShouldBeFalseWhenValueIs5() {
        assert !Verify.hexColor("00000")
    }

    @Test void hexColorShouldBeFalseWhenValueIs7() {
        assert !Verify.hexColor("0000000")
    }

    @Test void hexColorShouldAllowAlpha() {
        assert Verify.hexColor("abcdef")
    }

    @Test void hexColorShouldAllowUpperCaseAlpha() {
        assert Verify.hexColor("ABCDEF")
    }

    @Test void hexColorShouldNotAllowAlphaAboveF() {
        assert !Verify.hexColor("bcdefg")
    }

    @Test void documentFilenameShouldBeFalseWhenValueIsNull() {
        assert !Verify.documentFilename(null)
    }

    @Test void documentFilenameShouldBeFalseWhenValueIsEmpty() {
        assert !Verify.documentFilename("");
    }

    @Test void documentFilenameShouldBeTrueWhenValueIsAlpha() {
        assert Verify.documentFilename("abc")
    }

    @Test void documentFilenameShouldBeTrueWhenValueHasNumeric() {
        assert Verify.documentFilename("abc123")
    }

    @Test void documentFilenameShouldBeTrueWhenValueHasDash() {
        assert Verify.documentFilename("abc-123")
    }

    @Test void documentFilenameShouldBeTrueWhenValueHasPoint() {
        assert Verify.documentFilename("abc.123")
    }

    @Test void documentFilenameShouldBeTrueWhenValueIs255() {
        assert Verify.documentFilename(
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    }

    @Test void documentFilenameShouldBeFalseWhenValueIsMoreThan255() {
        assert !Verify.documentFilename(
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    }

    @Test void lengthShouldBeFalseWhenValueIsNull() {
        assert !Verify.length(null, 1, 3)
    }

    @Test void lengthShouldBeFalseWhenValueIsLessThanMinLength() {
        assert !Verify.length("123", 4, 9)
    }

    @Test void lengthShouldBeTrueWhenValueIsEqualMinLength() {
        assert Verify.length("", 0, 3)
    }

    @Test void lengthShouldBeTrueWhenValueIsMoreThanMinLength() {
        assert Verify.length("123", 2, 6)
    }

    @Test void lengthShouldBeTrueWhenValueIsLessThanMaxLength() {
        assert Verify.length("1234", 2, 5)
    }

    @Test void lengthShouldBeTrueWhenValueIsEqualToMaxLength() {
        assert Verify.length("1234", 2, 4)
    }

    @Test void lengthShouldBeFalseWhenValueIsMoreThanMaxLength() {
        assert !Verify.length("12345", 2, 4)
    }

    @Test void lengthShouldThrowExceptionWhenMinValueIsNull() {
        assertException NullPointerException.class,
                        { Verify.length("123", null, 4) }
    }

    @Test void lengthShouldThrowExceptionWhenMaxValueIsNull() {
        assertException NullPointerException.class,
                        { Verify.length("123", 2, null) }
    }

    @Test void minLengthShouldBeFalseWhenValueIsNull() {
        assert !Verify.minLength(null, 1)
    }

    @Test void minLengthShouldBeFalseWhenValueIsLessThanMinLength() {
        assert !Verify.minLength("123", 4)
    }

    @Test void minLengthShouldBeTrueWhenValueIsEqualMinLength() {
        assert Verify.minLength("", 0)
    }

    @Test void minLLengthShouldBeTrueWhenValueIsMoreThanMinLength() {
        assert Verify.minLength("123", 2)
    }

    @Test void minLengthShouldThrowExceptionWhenMinValueIsNull() {
        assertException NullPointerException.class,
                        { Verify.minLength("123", null) }
    }

    @Test void maxLengthShouldBeTrueWhenValueIsLessThanMaxLength() {
        assert Verify.maxLength("1234", 5)
    }

    @Test void maxLengthShouldBeTrueWhenValueIsEqualToMaxLength() {
        assert Verify.maxLength("1234",4)
    }

    @Test void maxLengthShouldBeFalseWhenValueIsMoreThanMaxLength() {
        assert !Verify.maxLength("12345", 4)
    }

    @Test void maxLengthShouldThrowExceptionWhenMaxValueIsNull() {
        assertException NullPointerException.class,
                        { Verify.maxLength("123", null) }
    }

    @Test void zeroShouldThrowExceptionWhenValueIsNull() {
        assertException NullPointerException.class,
                        { Verify.zero(null) }
    }

    @Test void zeroShouldBeTrueWhenValueIsZero() {
        assert Verify.zero(new BigDecimal("0.00"))
    }

    @Test void zeroShouldBeFalseWhenValueIsNonZero() {
        assert !Verify.zero(0.000000001)
    }

    @Test void notZeroShouldBeTrueWhenValueIsNotZero() {
        assert Verify.notZero(new BigDecimal("0.000000000000000000000000001"))
    }

    @Test void notZeroShouldBeFalseWhenValueIsZero() {
        assert !Verify.notZero(new Float("0.0"))
    }

    @Test void greaterThanOrEqualToShouldThrowExceptionWhenLhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.greaterThanOrEqualTo(null, 5) }
    }

    @Test void greaterThanOrEqualToShouldThrowExceptionWhenRhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.greaterThanOrEqualTo(4, null) }
    }

    @Test void greaterThanOrEqualToShouldBeTrueWhenRhsIsGreater() {
        assert Verify.greaterThanOrEqualTo(4, 5)
    }

    @Test void greaterThanOrEqualToShouldBeTrueWhenRhsIsEqual() {
        assert Verify.greaterThanOrEqualTo(4, 4)
    }

    @Test void greaterThanOrEqualToShouldBeFalseWhenRhsIsLess() {
        assert !Verify.greaterThanOrEqualTo(4, 3)
    }

    @Test void lessThanOrEqualToShouldThrowExceptionWhenLhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.lessThanOrEqualTo(null, 5) }
    }

    @Test void lessThanOrEqualToShouldThrowExceptionWhenRhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.lessThanOrEqualTo(4, null) }
    }

    @Test void lessThanOrEqualToShouldBeTrueWhenRhsIsGreater() {
        assert Verify.lessThanOrEqualTo(5, 4)
    }

    @Test void lessThanOrEqualToShouldBeTrueWhenRhsIsEqual() {
        assert Verify.lessThanOrEqualTo(5, 5)
    }

    @Test void lessThanOrEqualToShouldBeFalseWhenRhsIsLess() {
        assert !Verify.lessThanOrEqualTo(5, 6)
    }

    @Test void greaterThanToShouldThrowExceptionWhenLhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.greaterThan(null, 5) }
    }

    @Test void greaterThanShouldThrowExceptionWhenRhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.greaterThan(4, null) }
    }

    @Test void greaterThanShouldBeTrueWhenRhsIsGreater() {
        assert Verify.greaterThan(4, 5)
    }

    @Test void greaterThanShouldBeFalseWhenRhsIsEqual() {
        assert !Verify.greaterThan(4, 4)
    }

    @Test void greaterThanShouldBeFalseWhenRhsIsLess() {
        assert !Verify.greaterThan(4, 3)
    }

    @Test void lessThanShouldThrowExceptionWhenLhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.lessThan(null, 5) }
    }

    @Test void lessThanShouldThrowExceptionWhenRhsIsNull() {
        assertException NullPointerException.class,
                        { Verify.lessThan(4, null) }
    }

    @Test void lessThanShouldBeTrueWhenRhsIsGreater() {
        assert Verify.lessThan(5, 4)
    }

    @Test void lessThanShouldBeFalseWhenRhsIsEqual() {
        assert !Verify.lessThan(5, 5)
    }

    @Test void lessThanToShouldBeFalseWhenRhsIsLess() {
        assert !Verify.lessThan(5, 6)
    }

    @Test void equalShouldBeFalseWhenLhsIsNull() {
        assert !Verify.equal(null, "OK")
    }

    @Test void equalShouldBeFalseWhenRhsIsNull() {
        assert !Verify.equal("OK", null)
    }

    @Test void equalShouldBeTrueWhenBothAreNull() {
        assert Verify.equal(null, null)
    }

    @Test void equalShouldBeTrueWhenEqualityOperatorIsTrue() {
        assert Verify.equal(4, 4)
    }

    @Test void equalShouldBeTrueWhenEqualsMethodIsTrue() {
        assert Verify.equal("OK", "OK")
    }

    @Test void equalShouldBeFalseWhenEqualityOperatorAndEqualsMethodFail() {
        assert !Verify.equal(4, 5)
    }

    @Test void notEqualShouldBeTrueWhenLhsIsNull() {
        assert Verify.notEqual(null, "OK")
    }

    @Test void notEqualShouldBeTrueWhenRhsIsNull() {
        assert Verify.notEqual("OK", null)
    }

    @Test void notEqualShouldBeFalseWhenBothAreNull() {
        assert !Verify.notEqual(null, null)
    }

    @Test void notEqualShouldBeFalseWhenEqualityOperatorIsTrue() {
        assert !Verify.notEqual(4, 4)
    }

    @Test void notEqualShouldBeFalseWhenEqualsMethodIsTrue() {
        assert !Verify.notEqual("OK", "OK")
    }

    @Test void notEqualShouldBeTrueWhenEqualityOperatorAndEqualsMethodFail() {
        assert Verify.notEqual(4, 5)
    }
}
