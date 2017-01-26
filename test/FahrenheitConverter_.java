import model.Converter;
import model.Degree;
import model.ScaleException;
import model.converters.FahrenheitConverter;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.equalTo;
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
                .isThrownBy(() -> converter.convert(Degree.kelvin(0)))
                .withMessageContaining("Expected: FAHRENHEIT")
                .withMessageContaining("But was: KELVIN");
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.celsius(0)))
                .withMessageContaining("Expected: FAHRENHEIT")
                .withMessageContaining("But was: CELSIUS");
    }

    @Test
    public void should_convert_fahrenheit_to_kelvin() throws Exception {
        double temperature = 32;
        Degree degree = converter.convert(getDegree(temperature)).toKelvin();
        assertThat(degree, is(equalTo(Degree.kelvin(273.15))));
    }

    @Test
    public void should_convert_fahrenheit_to_fahrenheit() throws Exception {
        double temperature = 136.9;
        Degree degree = converter.convert(getDegree(temperature)).toFahrenheit();
        assertThat(degree, is(equalTo(Degree.fahrenheit(temperature))));
    }

    @Test
    public void should_convert_fahrenheit_to_celsius() throws Exception {
        double temperature = 32;
        Degree degree = converter.convert(getDegree(temperature)).toCelsius();
        assertThat(degree, is(equalTo(Degree.celsius(0))));
    }

    private Degree getDegree(double temperature) throws ScaleException {
        return Degree.fahrenheit(temperature);
    }


}
