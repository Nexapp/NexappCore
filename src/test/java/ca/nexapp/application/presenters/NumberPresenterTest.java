package ca.nexapp.application.presenters;

import static com.google.common.truth.Truth.*;

import java.util.Locale;

import org.junit.Test;

public class NumberPresenterTest {

    private static final double A_NUMBER_WITHOUT_DECIMAL = 50.00;
    private static final double A_NUMBER_WITH_TWO_DECIMALS = 1.33;
    private static final double A_NUMBER_WITH_SEVERAL_DECIMALS_NEAR_NEXT_INTEGER = 4.9999999999999;

    private static final int ONE_DECIMAL = 1;
    private static final int TWO_DECIMALS = 2;

    @Test
    public void givenNoDecimalWhenPresentingShouldReturnOnlyTheIntegerPart() {
        String expected = "50";
        String actual = new NumberPresenter(A_NUMBER_WITHOUT_DECIMAL, Locale.US).present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenNoDecimalWhenPresentingWithTwoDecimalsShouldNotAppendDecimals() {
        String expected = "50";
        String actual = new NumberPresenter(A_NUMBER_WITHOUT_DECIMAL, Locale.US).numberOfDecimals(TWO_DECIMALS).present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenNoDecimalWhenPresentingWithRoundingShouldOnlyReturnTheIntegerPartUnmodified() {
        String expected = "50";
        String actual = new NumberPresenter(A_NUMBER_WITHOUT_DECIMAL, Locale.US).round().present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenDecimalsWhenPresentingWithoutDecimalShouldReturnOnlyTheIntegerPart() {
        String expected = "1";
        String actual = new NumberPresenter(A_NUMBER_WITH_TWO_DECIMALS, Locale.US).present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenTwoDecimalsWhenPresentingWithOneDecimalShouldReturnTheIntegerPartWithOneDecimal() {
        String expected = "1.3";
        String actual = new NumberPresenter(A_NUMBER_WITH_TWO_DECIMALS, Locale.US).numberOfDecimals(ONE_DECIMAL).present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenTwoDecimalsWhenPresentingWithTwoDecimalsShouldReturnTwoDecimals() {
        String expected = "1.33";
        String actual = new NumberPresenter(A_NUMBER_WITH_TWO_DECIMALS, Locale.US).numberOfDecimals(TWO_DECIMALS).present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenDecimalsVeryNearToTheNextIntegerWhenPresentingWithRoundingShouldReturnTheNextInteger() {
        String expected = "5";
        String actual = new NumberPresenter(A_NUMBER_WITH_SEVERAL_DECIMALS_NEAR_NEXT_INTEGER, Locale.US).round().present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenDecimalsVeryNearToTheNextIntegerWhenPresentingWithoutDecimalShouldOnlyReturnTheInteger() {
        String expected = "4";
        String actual = new NumberPresenter(A_NUMBER_WITH_SEVERAL_DECIMALS_NEAR_NEXT_INTEGER, Locale.US).present();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenDecimalsVeryNearToTheNextIntegerWhenPresentingWithTwoDecimalsDecimalShouldReturnTheNumberWithTwoDecimals() {
        String expected = "4.99";
        String actual = new NumberPresenter(A_NUMBER_WITH_SEVERAL_DECIMALS_NEAR_NEXT_INTEGER, Locale.US).numberOfDecimals(TWO_DECIMALS).present();
        assertThat(actual).isEqualTo(expected);
    }

}
