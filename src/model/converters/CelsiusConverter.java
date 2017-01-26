package model.converters;

import model.Converter;
import model.Degree;
import model.ScaleException;

import static model.Scale.CELSIUS;

public class CelsiusConverter implements Converter {
    private Degree degree;

    @Override
    public Converter convert(Degree degree) throws ScaleException {
        if (degree.scale() != CELSIUS)
            throw ScaleException.expected(CELSIUS).butWas(degree.scale());
        this.degree = degree;
        return this;
    }

    @Override
    public Degree toFahrenheit() {
        try {
            return Degree.fahrenheit(degree.temperature() * 1.8 + 32);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }

    @Override
    public Degree toCelsius() {
        return degree;
    }

    @Override
    public Degree toKelvin() {
        try {
            return Degree.kelvin(degree.temperature() + 273.15);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }
}
