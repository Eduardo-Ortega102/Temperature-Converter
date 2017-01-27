package model;

import model.conversion.Conversion;

import java.util.ArrayList;
import java.util.List;

import static model.Degree.*;
import static model.Scale.*;

public class Converter {

    private Degree degree;
    private final List<Conversion> conversions;

    public Converter() {
        conversions = new ArrayList<>();
        conversions.add(new Conversion().from(KELVIN).to(KELVIN).as(    () -> kelvin(degree.temperature())));
        conversions.add(new Conversion().from(KELVIN).to(CELSIUS).as(   () -> celsius(degree.temperature() - 273.15)));
        conversions.add(new Conversion().from(KELVIN).to(FAHRENHEIT).as(() -> fahrenheit(degree.temperature() * (9.0 / 5.0) - 459.67)));
        conversions.add(new Conversion().from(CELSIUS).to(KELVIN).as(   () -> kelvin(degree.temperature() + 273.15)));
        conversions.add(new Conversion().from(CELSIUS).to(CELSIUS).as(  () -> celsius(degree.temperature())));
        conversions.add(new Conversion().from(CELSIUS).to(FAHRENHEIT).as(   () -> fahrenheit(degree.temperature() * 1.8 + 32)));
        conversions.add(new Conversion().from(FAHRENHEIT).to(KELVIN).as(    () -> kelvin((degree.temperature() + 459.67) * (5.0 / 9.0))));
        conversions.add(new Conversion().from(FAHRENHEIT).to(CELSIUS).as(   () -> celsius((degree.temperature() - 32) / 1.8)));
        conversions.add(new Conversion().from(FAHRENHEIT).to(FAHRENHEIT).as(() -> fahrenheit(degree.temperature())));
    }

    public Converter convert(Degree degree) throws ScaleException {
        this.degree = degree;
        return this;
    }

    public Degree toKelvin() {
        return get(conversion().to(KELVIN)).execute();
    }

    public Degree toFahrenheit() {
        return get(conversion().to(FAHRENHEIT)).execute();
    }

    public Degree toCelsius() {
        return get(conversion().to(CELSIUS)).execute();
    }

    private Conversion conversion() {
        return new Conversion().from(degree.scale());
    }

    private Conversion get(Conversion conversion) {
        return conversions.get(conversions.indexOf(conversion));
    }
}