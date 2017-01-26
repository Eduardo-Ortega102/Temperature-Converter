import model.Converter;
import model.Degree;
import model.ScaleException;
import model.converters.CelsiusConverter;
import org.junit.Before;
import org.junit.Test;

import static model.Degree.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.equalTo;
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
                .isThrownBy(() -> converter.convert(kelvin(0)))
                .withMessageContaining("Expected: CELSIUS")
                .withMessageContaining("But was: KELVIN");
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(fahrenheit(0)))
                .withMessageContaining("Expected: CELSIUS")
                .withMessageContaining("But was: FAHRENHEIT");
    }

    @Test
    public void should_convert_celsius_to_kelvin() throws Exception {
        double temperature = -273.15;
        Degree degree = converter.convert(celsius(temperature)).toKelvin();
        assertThat(degree, is(equalTo(kelvin(0))));
    }

    @Test
    public void should_convert_celsius_to_fahrenheit() throws Exception {
        double temperature = -32;
        Degree degree = converter.convert(celsius(temperature)).toFahrenheit();
        assertThat(degree, is(equalTo(fahrenheit(-25.6))));
    }

    @Test
    public void should_convert_celsius_to_celsius() throws Exception {
        double temperature = 56;
        Degree degree = converter.convert(celsius(temperature)).toCelsius();
        assertThat(degree, is(equalTo(celsius(56))));
    }

}
