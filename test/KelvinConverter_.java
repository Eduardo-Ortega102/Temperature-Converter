import model.Converter;
import model.Degree;
import model.converters.KelvinConverter;
import model.ScaleException;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static model.Scale.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KelvinConverter_ {

    private Converter converter;

    @Before
    public void setUp() throws Exception {
        converter = new KelvinConverter();
    }

    @Test
    public void should_not_convert_temperature_if_scale_is_not_kelvin() throws Exception {
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.ofScale(CELSIUS).andTemperature(0)))
                .withMessageContaining("Expected: KELVIN")
                .withMessageContaining("But was: CELSIUS");
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.ofScale(FAHRENHEIT).andTemperature(0)))
                .withMessageContaining("Expected: KELVIN")
                .withMessageContaining("But was: FAHRENHEIT");
    }

    @Test
    public void should_convert_kelvin_to_kelvin() throws Exception {
        double temperature = getRandomTemperatureOfScaleKelvin();
        Degree degree = converter.convert(getDegree(temperature)).toKelvin();
        assertThat(degree.scale(), is(KELVIN));
        assertThat(degree.temperature(), is(temperature));
    }

    @Test
    public void should_convert_kelvin_to_fahrenheit() throws Exception {
        double temperature = getRandomTemperatureOfScaleKelvin();
        Degree degree = converter.convert(getDegree(temperature)).toFahrenheit();
        assertThat(degree.scale(), is(FAHRENHEIT));
        assertThat(degree.temperature(), is(temperature * (9 / 5) - 459.67));
    }

    @Test
    public void should_convert_kelvin_to_celsius() throws Exception {
        double temperature = getRandomTemperatureOfScaleKelvin();
        Degree degree = converter.convert(getDegree(temperature)).toCelsius();
        assertThat(degree.scale(), is(CELSIUS));
        assertThat(degree.temperature(), is(temperature - 273.15));
    }

    private Degree getDegree(double temperature) throws ScaleException {
        return Degree.ofScale(KELVIN).andTemperature(temperature);
    }

    private double getRandomTemperatureOfScaleKelvin() {
        return KELVIN.absoluteZero() + new Random().nextDouble() * Double.MAX_VALUE;
    }





}
