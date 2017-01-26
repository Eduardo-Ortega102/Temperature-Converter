package model.converters;

import model.Converter;
import model.Degree;
import model.ScaleException;

import static model.Degree.*;
import static model.Scale.FAHRENHEIT;


public class FahrenheitConverter implements Converter {

    private Degree degree;

    @Override
    public Converter convert(Degree degree) throws ScaleException {
        if (degree.scale() != FAHRENHEIT)
            throw ScaleException.expected(FAHRENHEIT).butWas(degree.scale());
        this.degree = degree;
        return this;
    }

    @Override
    public Degree toKelvin() {
        try {
            return kelvin((degree.temperature() + 459.67) * (5.0 / 9.0));
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }

    @Override
    public Degree toFahrenheit() {
        return degree;
    }

    @Override
    public Degree toCelsius() {
        try {
            return celsius((degree.temperature() - 32) / 1.8);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }
}
