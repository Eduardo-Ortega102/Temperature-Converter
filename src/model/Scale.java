package model;

public enum Scale {
    CELSIUS {
        @Override
        public double absoluteZero() {
            return -273.15;
        }
    },
    KELVIN {
        @Override
        public double absoluteZero() {
            return 0;
        }
    },
    FAHRENHEIT {
        @Override
        public double absoluteZero() {
            return -459.67;
        }
    };

    public abstract double absoluteZero();

}
