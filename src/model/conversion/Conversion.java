package model.conversion;

import model.Degree;
import model.Scale;
import model.ScaleException;

public class Conversion {
    private Scale from;
    private Scale to;
    private Operation operation;

    public Conversion from(Scale scale) {
        this.from = scale;
        return this;
    }

    public Conversion to(Scale scale) {
        this.to = scale;
        return this;
    }

    public Conversion as(Operation operation) {
        this.operation = operation;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        Conversion conversion = (Conversion) obj;
        return from == conversion.from && to == conversion.to;
    }

    public Degree execute() {
        try {
            return operation.execute();
        } catch (ScaleException e) { throw new RuntimeException("This never happen"); }
    }
}
