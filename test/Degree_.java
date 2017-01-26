import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import model.Degree;
import model.Scale;
import model.ScaleException;
import org.junit.Test;
import org.junit.runner.RunWith;

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
                .isThrownBy(() -> Degree.celsius(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: CELSIUS")
                .withMessageContaining("Absolute zero: -273.15")
                .withMessageContaining("Given temperature: " + temperature);
    }

    @Test
    public void should_not_create_kelvin_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -1d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> Degree.kelvin(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: KELVIN")
                .withMessageContaining("Absolute zero: 0.0")
                .withMessageContaining("Given temperature: " + temperature);
    }

    @Test
    public void should_not_create_fahrenheit_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -1000d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> Degree.fahrenheit(temperature))
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
        Degree degree = null;
        if (scale == KELVIN) degree = Degree.kelvin(temperature);
        else if (scale == CELSIUS) degree = Degree.celsius(temperature);
        else if (scale == FAHRENHEIT) degree = Degree.fahrenheit(temperature);
        assertThat(degree.scale(), is(scale));
        assertThat(degree.temperature(), is(temperature));
    }


    @Test
    public void should_check_equality_properly() throws Exception {
        assertThat(Degree.celsius(32)     , is(equalTo(Degree.celsius(32))));
        assertThat(Degree.kelvin(3)       , is(equalTo(Degree.kelvin(3))));
        assertThat(Degree.fahrenheit(2.60), is(equalTo(Degree.fahrenheit(2.60))));
        assertThat(Degree.celsius(32.50)  , is(not(equalTo(Degree.celsius(2.53)))));
        assertThat(Degree.kelvin(300)     , is(not(equalTo(Degree.kelvin(3)))));
        assertThat(Degree.fahrenheit(200) , is(not(equalTo(Degree.fahrenheit(0)))));
        assertThat(Degree.celsius(32.00)  , is(not(equalTo(Degree.kelvin(32.00)))));
        assertThat(Degree.celsius(32.32)  , is(not(equalTo(Degree.fahrenheit(32.32)))));
        assertThat(Degree.fahrenheit(100) , is(not(equalTo(Degree.kelvin(320)))));
    }

}
