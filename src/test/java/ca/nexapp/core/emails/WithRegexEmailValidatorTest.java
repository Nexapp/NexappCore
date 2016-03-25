package ca.nexapp.core.emails;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WithRegexEmailValidatorTest {

    private EmailValidator validator;

    @Before
    public void setUp() {
        validator = new WithRegexEmailValidator();
    }

    @Test
    public void cannotHaveANullString() {
        assertThat(validator.isValid(null)).isFalse();
    }

    @Test
    public void cannotHaveAnEmptyString() {
        assertThat(validator.isValid("")).isFalse();
    }

    @Test
    public void cannotOmitTheAtSign() {
        assertThat(validator.isValid("email")).isFalse();
    }

    @Test
    public void cannotOmitTheLocalPart() {
        assertThat(validator.isValid("@example.com")).isFalse();
    }

    @Test
    public void cannotContainsTwoAtSign() {
        assertThat(validator.isValid("john@smith@example.com")).isFalse();
    }

    @Test
    public void cannotStartWithADot() {
        assertThat(validator.isValid(".john.smith@example.com")).isFalse();
    }

    @Test
    public void cannotHaveADotBeforeTheAtSign() {
        assertThat(validator.isValid("john.smith.@example.com")).isFalse();
    }

    @Test
    public void cannotHaveTwoConsecutiveDotsInLocalPart() {
        assertThat(validator.isValid("john..smith@example.com")).isFalse();
    }

    @Test
    public void cannotOmitExtension() {
        assertThat(validator.isValid("john.smith@example")).isFalse();
    }

    @Test
    @Ignore
    public void cannotStartTheDomainWithAHyphen() {
        assertThat(validator.isValid("john.smith@-example.com")).isFalse();
    }

    @Test
    @Ignore
    public void cannotEndTheDomainWithAHyphen() {
        assertThat(validator.isValid("john.smith@example-.com")).isFalse();
    }

    @Test
    public void cannotHaveDigitsAsDomain() {
        assertThat(validator.isValid("john.smith@111.222.333.444")).isFalse();
    }

    @Test
    public void cannotHaveTwoDotsInDomain() {
        assertThat(validator.isValid("john.smith@example..com")).isFalse();
    }

    @Test
    public void canHaveAPlainTextAsDomainAndExtension() {
        assertThat(validator.isValid("email@example.com")).isTrue();
    }

    @Test
    public void canHaveADotInLocalPart() {
        assertThat(validator.isValid("firstname.lastname@example.com")).isTrue();
    }

    @Test
    public void canHaveASubdomain() {
        assertThat(validator.isValid("email@subdomain.example.com")).isTrue();
    }

    @Test
    public void canContainsDigitsInLocalPart() {
        assertThat(validator.isValid("1234567890@example.com")).isTrue();
    }

    @Test
    public void canContainsAHyphenInDomain() {
        assertThat(validator.isValid("email@example-one.com")).isTrue();
    }

    @Test
    public void canContainsAHyphenInLocalPart() {
        assertThat(validator.isValid("firstname-lastname@example.com")).isTrue();
    }

    @Test
    public void canHaveTwoExtensions() {
        assertThat(validator.isValid("email@example.co.jp")).isTrue();
    }

    @Test
    public void canContainsOnlyUnderscoresInLocalPart() {
        assertThat(validator.isValid("__________@example.com")).isTrue();
    }

    @Test
    public void canContainsAnExclamationPointInLocalPart() {
        assertThat(validator.isValid("ema!l@example.com")).isTrue();
    }

    @Test
    public void canContainsANumberSignInLocalPart() {
        assertThat(validator.isValid("john#123@example.com")).isTrue();
    }

    @Test
    public void canContainsADollarSignInLocalPart() {
        assertThat(validator.isValid("john100$@example.com")).isTrue();
    }

    @Test
    public void canContainsAPercentageSignInLocalPart() {
        assertThat(validator.isValid("john100%@example.com")).isTrue();
    }

    @Test
    public void canContainsAnAmpersandInLocalPart() {
        assertThat(validator.isValid("john&doe@example.com")).isTrue();
    }

    @Test
    public void canContainsASingleQuoteInLocalPart() {
        assertThat(validator.isValid("john'doe@example.com")).isTrue();
    }

    @Test
    public void canContainsAnAsteriskInLocalPart() {
        assertThat(validator.isValid("super*important@example.com")).isTrue();
    }

    @Test
    public void canContainsAPlusSignInLocalPart() {
        assertThat(validator.isValid("john+doe@example.com")).isTrue();
    }

    @Test
    public void canContainsAMinusSignInLocalPart() {
        assertThat(validator.isValid("john-doe@example.com")).isTrue();
    }

    @Test
    public void canContainsASlashInLocalPart() {
        assertThat(validator.isValid("john/doe@example.com")).isTrue();
    }

    @Test
    public void canContainsAnEqualSignInLocalPart() {
        assertThat(validator.isValid("john=doe@example.com")).isTrue();
    }

    @Test
    public void canContainsAQuestionMarkInLocalPart() {
        assertThat(validator.isValid("john?doe@example.com")).isTrue();
    }

    @Test
    public void canContainsACaretInLocalPart() {
        assertThat(validator.isValid("john^doe@example.com")).isTrue();
    }

    @Test
    public void canContainsAGraveAccentInLocalPart() {
        assertThat(validator.isValid("john`doe@example.com")).isTrue();
    }

    @Test
    public void canContainsOpeningAndClosingBracesInLocalPart() {
        assertThat(validator.isValid("john{doe}@example.com")).isTrue();
    }

    @Test
    public void canContainsAPipeInLocalPart() {
        assertThat(validator.isValid("john|doe@example.com")).isTrue();
    }

    @Test
    public void canContainsATildeInLocalPart() {
        assertThat(validator.isValid("john~doe@example.com")).isTrue();
    }

    @Test
    public void canHaveALongExtension() {
        assertThat(validator.isValid("email@domain.extension")).isTrue();
    }

    @Test
    public void cannotHaveAnExtensionWithOneCharacter() {
        assertThat(validator.isValid("email@domain.e")).isFalse();
    }

    @Test
    public void cannotHaveADomainWithOneCharacter() {
        assertThat(validator.isValid("email@d.com")).isTrue();
    }
}
