import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import model.Degree;
import model.Scale;
import model.ScaleException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static model.Degree.*;
import static model.Scale.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


@RunWith(JUnitParamsRunner.class)
public class Degree_ {

    @Test
    public void should_not_create_celsius_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -560d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> celsius(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: CELSIUS")
                .withMessageContaining("Absolute zero: -273.15")
                .withMessageContaining("Given temperature: " + temperature);
    }

    @Test
    public void should_not_create_kelvin_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -1d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> kelvin(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: KELVIN")
                .withMessageContaining("Absolute zero: 0.0")
                .withMessageContaining("Given temperature: " + temperature);
    }

    @Test
    public void should_not_create_fahrenheit_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -1000d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> fahrenheit(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: FAHRENHEIT")
                .withMessageContaining("Absolute zero: -459.67")
                .withMessageContaining("Given temperature: " + temperature);
    }


    @Test
    @Parameters({"17, KELVIN", "0, KELVIN",
            "30.5, CELSIUS", "-235.63, CELSIUS",
            "35, FAHRENHEIT", "-32, FAHRENHEIT",
    })
    public void should_create_degrees_in_properly_range(double temperature, Scale scale) throws Exception {
        Degree degree = (scale == KELVIN) ? kelvin(temperature) :
                        (scale == CELSIUS) ? celsius(temperature) :
                        (scale == FAHRENHEIT) ? fahrenheit(temperature) : null;
        assertThat(degree.scale(), is(scale));
        assertThat(degree.temperature(), is(temperature));
    }


    @Test
    public void should_check_equality_properly() throws Exception {
        assertThat(celsius(32), is(equalTo(celsius(32))));
        assertThat(kelvin(3), is(equalTo(kelvin(3))));
        assertThat(fahrenheit(2.60), is(equalTo(fahrenheit(2.60))));
        assertThat(celsius(32.50), is(not(equalTo(celsius(2.53)))));
        assertThat(kelvin(300), is(not(equalTo(kelvin(3)))));
        assertThat(fahrenheit(200), is(not(equalTo(fahrenheit(0)))));
        assertThat(celsius(32.00), is(not(equalTo(kelvin(32.00)))));
        assertThat(celsius(32.32), is(not(equalTo(fahrenheit(32.32)))));
        assertThat(fahrenheit(100), is(not(equalTo(kelvin(320)))));
    }

}
