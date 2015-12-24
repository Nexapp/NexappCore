package ca.nexapp.core.application.presenters;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;

public class NumberPresenter {

    private int numberOfDecimals;
    private double number;

    public NumberPresenter(double number) {
        this.number = number;
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
        DecimalFormat formatter = new DecimalFormat();

        formatter.setMinimumIntegerDigits(1);
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(numberOfDecimals);
        formatter.setRoundingMode(RoundingMode.DOWN);

        return formatter;
    }

    private double minimalRound(double value) {
        double precision = Math.pow(10, numberOfDecimals);

        double minimalValue = value * precision;
        minimalValue = Math.round(minimalValue);
        minimalValue = minimalValue / precision;
        return minimalValue;
    }
}
