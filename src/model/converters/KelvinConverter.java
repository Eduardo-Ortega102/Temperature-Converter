package model.converters;

import model.Converter;
import model.Degree;
import model.ScaleException;

import static model.Scale.KELVIN;

public class KelvinConverter implements Converter {

    private Degree degree;

    @Override
    public Converter convert(Degree degree) throws ScaleException {
        if (degree.scale() != KELVIN)
            throw ScaleException.expected(KELVIN).butWas(degree.scale());
        this.degree = degree;
        return this;
    }

    @Override
    public Degree toKelvin() {
        return degree;
    }

    @Override
    public Degree toFahrenheit() {
        try {
            return Degree.fahrenheit(degree.temperature() * (9 / 5) - 459.67);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }

    @Override
    public Degree toCelsius() {
        try {
            return Degree.celsius(degree.temperature() - 273.15);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }
}
