import model.Converter;
import model.converters.FahrenheitConverter;
import model.Degree;
import model.ScaleException;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static model.Scale.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FahrenheitConverter_ {

    private Converter converter;

    @Before
    public void setUp() throws Exception {
        converter = new FahrenheitConverter();
    }

    @Test
    public void should_not_convert_temperature_if_scale_is_not_celsius() throws Exception {
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.ofScale(KELVIN).andTemperature(0)))
                .withMessageContaining("Expected: FAHRENHEIT")
                .withMessageContaining("But was: KELVIN");
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.ofScale(CELSIUS).andTemperature(0)))
                .withMessageContaining("Expected: FAHRENHEIT")
                .withMessageContaining("But was: CELSIUS");
    }

    @Test
    public void should_convert_fahrenheit_to_kelvin() throws Exception {
        double temperature = getRandomTemperatureOfScaleFahrenheit();
        Degree degree = converter.convert(getDegree(temperature)).toKelvin();
        assertThat(degree.scale(), is(KELVIN));
        assertThat(degree.temperature(), is((temperature + 459.67) * (5 / 9)));
    }

    @Test
    public void should_convert_fahrenheit_to_fahrenheit() throws Exception {
        double temperature = getRandomTemperatureOfScaleFahrenheit();
        Degree degree = converter.convert(getDegree(temperature)).toFahrenheit();
        assertThat(degree.scale(), is(FAHRENHEIT));
        assertThat(degree.temperature(), is(temperature));
    }

    @Test
    public void should_convert_fahrenheit_to_celsius() throws Exception {
        double temperature = getRandomTemperatureOfScaleFahrenheit();
        Degree degree = converter.convert(getDegree(temperature)).toCelsius();
        assertThat(degree.scale(), is(CELSIUS));
        assertThat(degree.temperature(), is(temperature - 32));
    }

    private Degree getDegree(double temperature) throws ScaleException {
        return Degree.ofScale(FAHRENHEIT).andTemperature(temperature);
    }

    private double getRandomTemperatureOfScaleFahrenheit() {
        return FAHRENHEIT.absoluteZero() + new Random().nextDouble() * Double.MAX_VALUE;
    }


}
