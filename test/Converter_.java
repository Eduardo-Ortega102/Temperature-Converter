import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import model.Converter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static model.Degree.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class Converter_ {
    private Converter converter;

    @Before
    public void setUp() throws Exception {
        converter = new Converter();
    }

    @Test @Parameters({"56.35", "-273.15", "3"})
    public void should_convert_celsius_to_celsius(double temperature) throws Exception {
        assertThat(converter.convert(celsius(temperature)).toCelsius(), is(equalTo(celsius(temperature))));
    }

    @Test @Parameters({"136.9", "-459.67", "3"})
    public void should_convert_fahrenheit_to_fahrenheit(double temperature) throws Exception {
        assertThat(converter.convert(fahrenheit(temperature)).toFahrenheit(), is(equalTo(fahrenheit(temperature))));
    }

    @Test @Parameters({"789.23", "0", "3"})
    public void should_convert_kelvin_to_kelvin(double temperature) throws Exception {
        assertThat(converter.convert(kelvin(temperature)).toKelvin(), is(equalTo(kelvin(temperature))));
    }


    @Test @Parameters({
            "-273.15, 0",
    })
    public void should_convert_celsius_to_kelvin(double temperature, double expected) throws Exception {
        assertThat(converter.convert(celsius(temperature)).toKelvin(), is(equalTo(kelvin(expected))));
    }

    @Test @Parameters({
            "-32, -25.6",
    })
    public void should_convert_celsius_to_fahrenheit(double temperature, double expected) throws Exception {
        assertThat(converter.convert(celsius(temperature)).toFahrenheit(), is(equalTo(fahrenheit(expected))));
    }


    @Test @Parameters({
            "32, 273.15",
    })
    public void should_convert_fahrenheit_to_kelvin(double temperature, double expected) throws Exception {
        assertThat(converter.convert(fahrenheit(temperature)).toKelvin(), is(equalTo(kelvin(expected))));
    }

    @Test @Parameters({
            "32, 0",
    })
    public void should_convert_fahrenheit_to_celsius(double temperature, double expected) throws Exception {
        assertThat(converter.convert(fahrenheit(temperature)).toCelsius(), is(equalTo(celsius(expected))));
    }


    @Test @Parameters({
            "0, -459.67",
    })
    public void should_convert_kelvin_to_fahrenheit(double temperature, double expected) throws Exception {
        assertThat(converter.convert(kelvin(temperature)).toFahrenheit(), is(equalTo(fahrenheit(expected))));
    }

    @Test @Parameters({
            "0, -273.15",
    })
    public void should_convert_kelvin_to_celsius(double temperature, double expected) throws Exception {
        assertThat(converter.convert(kelvin(temperature)).toCelsius(), is(equalTo(celsius(expected))));
    }
}
