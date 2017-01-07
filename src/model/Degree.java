package model;

public class Degree {

    private final Scale scale;
    private final double temperature;

    private Degree(Scale scale, double temperature) {
        this.scale = scale;
        this.temperature = temperature;
    }

    public static TemperatureChecker ofScale(Scale scale) {
        return temperature -> {
            if (temperature < scale.absoluteZero())
                throw ScaleException.temperature(temperature).isLowerThanAbsoluteZeroInScale(scale);
            return new Degree(scale, temperature);
        };
    }

    public Scale scale() {
        return scale;
    }

    public double temperature() {
        return temperature;
    }


    @FunctionalInterface
    public interface TemperatureChecker {
        Degree andTemperature(double temperature) throws ScaleException;
    }
}
