import model.Converter;
import model.Degree;
import model.ScaleException;
import model.converters.KelvinConverter;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.equalTo;
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
                .isThrownBy(() -> converter.convert(Degree.celsius(0)))
                .withMessageContaining("Expected: KELVIN")
                .withMessageContaining("But was: CELSIUS");
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> converter.convert(Degree.fahrenheit(0)))
                .withMessageContaining("Expected: KELVIN")
                .withMessageContaining("But was: FAHRENHEIT");
    }

    @Test
    public void should_convert_kelvin_to_kelvin() throws Exception {
        double temperature = 789;
        Degree degree = converter.convert(Degree.kelvin(temperature)).toKelvin();
        assertThat(degree, is(equalTo(Degree.kelvin(789))));
    }

    @Test
    public void should_convert_kelvin_to_fahrenheit() throws Exception {
        double temperature = 0;
        Degree degree = converter.convert(Degree.kelvin(temperature)).toFahrenheit();
        assertThat(degree, is(equalTo(Degree.fahrenheit(-459.67))));
    }

    @Test
    public void should_convert_kelvin_to_celsius() throws Exception {
        double temperature = 0;
        Degree degree = converter.convert(Degree.kelvin(temperature)).toCelsius();
        assertThat(degree, is(equalTo(Degree.celsius(-273.15))));
    }


}
