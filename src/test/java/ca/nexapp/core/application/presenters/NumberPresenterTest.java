package ca.nexapp.core.application.presenters;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class NumberPresenterTest {

    @Test
    public void givenNoDecimal_WhenPresenting_ShouldReturnOnlyTheIntegerPart() {
        String number = new NumberPresenter(50).present();

        assertThat(number).isEqualTo("50");
    }

    @Test
    public void givenNoDecimal_WhenPresentingWithTwoDecimals_ShouldNotAppendDecimals() {
        String number = new NumberPresenter(50).numberOfDecimals(2).present();

        assertThat(number).isEqualTo("50");
    }

    @Test
    public void givenNoDecimal_WhenPresentingWithRounding_ShouldOnlyReturnTheIntegerPartUnmodified() {
        String number = new NumberPresenter(50).round().present();

        assertThat(number).isEqualTo("50");
    }

    @Test
    public void givenDecimals_WhenPresentingWithoutDecimal_ShouldReturnOnlyTheIntegerPart() {
        String number = new NumberPresenter(1.3333).present();

        assertThat(number).isEqualTo("1");
    }

    @Test
    public void givenTwoDecimals_WhenPresentingWithOneDecimal_ShouldReturnTheIntegerPartWithOneDecimal() {
        String number = new NumberPresenter(1.33).numberOfDecimals(1).present();

        assertThat(number).isEqualTo("1.3");
    }

    @Test
    public void givenTwoDecimals_WhenPresentingWithTwoDecimals_ShouldReturnTwoDecimals() {
        String number = new NumberPresenter(1.33).numberOfDecimals(2).present();

        assertThat(number).isEqualTo("1.33");
    }

    @Test
    public void givenTwoDecimalsThatCouldBeRoundedUp_WhenPresentingWithOneDecimal_ShouldReturnTheIntegerPartWithOneDecimalUnmodified() {
        String number = new NumberPresenter(1.39).numberOfDecimals(1).present();

        assertThat(number).isEqualTo("1.3");
    }

    @Test
    public void givenDecimalsVeryNearToTheNextInteger_WhenPresentingWithRounding_ShouldReturnTheNextInteger() {
        String number = new NumberPresenter(4.999999).round().present();

        assertThat(number).isEqualTo("5");
    }

    @Test
    public void givenDecimalsVeryNearToTheNextInteger_WhenPresentingWithoutDecimal_ShouldOnlyReturnTheInteger() {
        String number = new NumberPresenter(4.999999).present();

        assertThat(number).isEqualTo("4");
    }

    @Test
    public void givenDecimalsVeryNearToTheNextInteger_WhenPresentingWithTwoDecimals_ShouldReturnTheNumberWithTwoDecimals() {
        String number = new NumberPresenter(4.999999).numberOfDecimals(2).present();

        assertThat(number).isEqualTo("4.99");
    }

}
