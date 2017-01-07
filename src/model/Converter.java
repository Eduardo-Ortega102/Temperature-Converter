package model;

public interface Converter {

    Converter convert(Degree degree) throws ScaleException;

    Degree toKelvin();

    Degree toFahrenheit();

    Degree toCelsius();

}
