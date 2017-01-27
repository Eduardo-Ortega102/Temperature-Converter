package model.conversion;

import model.Degree;
import model.ScaleException;

@FunctionalInterface
public interface Operation {
    Degree execute() throws ScaleException;
}
