package model;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static model.Scale.*;

public class Degree {
    private final Scale scale;
    private final double temperature;

    private Degree(Scale scale, double temperature) throws ScaleException {
        if (temperature < scale.absoluteZero())
            throw ScaleException.temperature(temperature).isLowerThanAbsoluteZeroInScale(scale);
        this.scale = scale;
        this.temperature = trimToTwoDecimals(temperature);
    }

    private double trimToTwoDecimals(double temperature) {
        return parseDouble(new DecimalFormat(".##").format((temperature)).replace(",","."));
    }

    public Scale scale() {
        return scale;
    }

    public double temperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object obj) {
        Degree degree = (Degree) obj;
        return temperature == degree.temperature && scale == degree.scale;
    }

    @Override
    public String toString() {
        return temperature + " " + scale;
    }

    public static Degree celsius(double temperature) throws ScaleException {
        return new Degree(CELSIUS, temperature);
    }

    public static Degree kelvin(double temperature) throws ScaleException {
        return new Degree(KELVIN, temperature);
    }

    public static Degree fahrenheit(double temperature) throws ScaleException {
        return new Degree(FAHRENHEIT, temperature);
    }

}
