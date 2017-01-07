package model.converters;

import model.Converter;
import model.Degree;
import model.ScaleException;

import static model.Scale.*;

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
            return Degree.ofScale(KELVIN).andTemperature((degree.temperature() + 459.67) * (5 / 9));
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
            return Degree.ofScale(CELSIUS).andTemperature(degree.temperature() - 32);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }
}
