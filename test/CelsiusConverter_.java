import model.converters.CelsiusConverter;
import model.Converter;
import model.Degree;
import model.ScaleException;
import static model.Scale.*;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CelsiusConverter_ {

    private Converter converter;

    @Before
    public void setUp() throws Exception {
        converter = new CelsiusConverter();
    }

    @Test
    public void should_not_convert_temperature_if_scale_is_not_celsius() throws Exception {
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.ofScale(KELVIN).andTemperature(0)))
                .withMessageContaining("Expected: CELSIUS")
                .withMessageContaining("But was: KELVIN");
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.ofScale(FAHRENHEIT).andTemperature(0)))
                .withMessageContaining("Expected: CELSIUS")
                .withMessageContaining("But was: FAHRENHEIT");
    }

    @Test
    public void should_convert_celsius_to_kelvin() throws Exception {
        double temperature = getRandomTemperatureOfScaleCelsius();
        Degree degree = converter.convert(getDegree(temperature)).toKelvin();
        assertThat(degree.scale(), is(KELVIN));
        assertThat(degree.temperature(), is(temperature + 273.15));
    }

    @Test
    public void should_convert_celsius_to_fahrenheit() throws Exception {
        double temperature = getRandomTemperatureOfScaleCelsius();
        Degree degree = converter.convert(getDegree(temperature)).toFahrenheit();
        assertThat(degree.scale(), is(FAHRENHEIT));
        assertThat(degree.temperature(), is(temperature + 32));
    }

    @Test
    public void should_convert_celsius_to_celsius() throws Exception {
        double temperature = getRandomTemperatureOfScaleCelsius();
        Degree degree = converter.convert(getDegree(temperature)).toCelsius();
        assertThat(degree.scale(), is(CELSIUS));
        assertThat(degree.temperature(), is(temperature));
    }

    private Degree getDegree(double temperature) throws ScaleException {
        return Degree.ofScale(CELSIUS).andTemperature(temperature);
    }

    private double getRandomTemperatureOfScaleCelsius() {
        return CELSIUS.absoluteZero() + new Random().nextDouble() * Double.MAX_VALUE;
    }

}
