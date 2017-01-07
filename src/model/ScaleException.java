package model;

public class ScaleException extends Exception {

    private ScaleException(String message) {
        super(message);
    }

    public static ScaleMessageBuilder expected(Scale scale1) {
        return scale2 -> new ScaleException("Scale: \n Expected: " + scale1 + " \n But was: " + scale2);
    }

    public static TemperatureMessageBuilder temperature(double temperature) {
        return scale -> {
            String message = "The given temperature is lower than the scale's absolute zero. \n";
            message += "Scale: " + scale + "\nAbsolute zero: " + scale.absoluteZero() + "\n";
            message += "Given temperature: " + temperature;
            return new ScaleException(message);
        };
    }


    @FunctionalInterface
    public interface ScaleMessageBuilder {
        ScaleException butWas(Scale scale);
    }

    @FunctionalInterface
    public interface TemperatureMessageBuilder {
        ScaleException isLowerThanAbsoluteZeroInScale(Scale scale);
    }

}
