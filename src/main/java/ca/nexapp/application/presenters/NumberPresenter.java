package ca.nexapp.application.presenters;

import java.math.RoundingMode;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberPresenter {

    private static final int PRECISION = 10_000_000;

    private int numberOfDecimals;
    private double number;
    private final Locale locale;

    public NumberPresenter(double number) {
        this(number, Locale.getDefault());
    }

    public NumberPresenter(double number, Locale locale) {
        this.number = number;
        this.locale = locale;
    }

    public NumberPresenter numberOfDecimals(int numberOfDecimals) {
        this.numberOfDecimals = numberOfDecimals;
        return this;
    }

    public NumberPresenter round() {
        number = minimalRound(number);
        return this;
    }

    public String present() {
        Format formatter = createFormatter(numberOfDecimals);
        return formatter.format(number);
    }

    private Format createFormatter(int numberOfDecimals) {
        NumberFormat formatter = NumberFormat.getInstance(locale);

        formatter.setMinimumIntegerDigits(1);
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(numberOfDecimals);
        formatter.setRoundingMode(RoundingMode.DOWN);

        return formatter;
    }

    private double minimalRound(double value) {
        double minimalValue = value * PRECISION;
        minimalValue = Math.round(minimalValue);
        minimalValue = minimalValue / PRECISION;
        return minimalValue;
    }
}
