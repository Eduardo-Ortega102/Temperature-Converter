package model;

public class ScaleException extends Exception {

    public ScaleException(Scale scale, double temperature) {
        super("The given temperature is lower than the scale's absolute zero. \n" +
              "Scale: " + scale + "\n" +
              "Absolute zero: " + scale.absoluteZero() + "\n" +
              "Given temperature: " + temperature);
    }

}
