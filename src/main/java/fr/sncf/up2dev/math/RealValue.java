package fr.sncf.up2dev.math;

public class RealValue implements Value {

    private final double value;

    public RealValue(double value) {
        this.value = value;
    }

    @Override
    public Double get() {
        return this.value;
    }
}
