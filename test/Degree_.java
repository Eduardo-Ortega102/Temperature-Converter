import model.Degree;
import model.Scale;
import model.ScaleException;
import org.junit.Test;

import java.util.Random;

import static model.Scale.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Degree_ {

    @Test
    public void should_not_create_celsius_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -560d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> Degree.ofScale(CELSIUS).andTemperature(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: CELSIUS")
                .withMessageContaining("Absolute zero: -273.15")
                .withMessageContaining("Given temperature: " + temperature);
    }

    @Test
    public void should_not_create_kelvin_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -1d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> Degree.ofScale(KELVIN).andTemperature(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: KELVIN")
                .withMessageContaining("Absolute zero: 0.0")
                .withMessageContaining("Given temperature: " + temperature);
    }

    @Test
    public void should_not_create_fahrenheit_degree_if_temperature_is_lower_than_absolute_zero() throws Exception {
        double temperature = -1000d;
        assertThatExceptionOfType(ScaleException.class)
                .isThrownBy(() -> Degree.ofScale(FAHRENHEIT).andTemperature(temperature))
                .withMessageContaining("The given temperature is lower than the scale's absolute zero")
                .withMessageContaining("Scale: FAHRENHEIT")
                .withMessageContaining("Absolute zero: -459.67")
                .withMessageContaining("Given temperature: " + temperature);
    }


    @Test
    public void acceptation() throws Exception {
        for (int i = 0; i < 100; i++)
            for (Scale scale : Scale.values()) {
                double temperature = scale.absoluteZero() + getRandomDouble();
                Degree degree = Degree.ofScale(scale).andTemperature(temperature);
                assertThat(degree.scale(), is(scale));
                assertThat(degree.temperature(), is(temperature));
            }
    }

    private double getRandomDouble() {
        return new Random().nextDouble() * Double.MAX_VALUE;
    }

}
