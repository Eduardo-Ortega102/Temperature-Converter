package model.converters;

import model.Converter;
import model.Degree;
import model.ScaleException;

import static model.Scale.*;

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
            return Degree.ofScale(FAHRENHEIT).andTemperature(degree.temperature() * (9 / 5) - 459.67);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }

    @Override
    public Degree toCelsius() {
        try {
            return Degree.ofScale(CELSIUS).andTemperature(degree.temperature() - 273.15);
        } catch (ScaleException e) {
            throw new RuntimeException("This never happen");
        }
    }
}
