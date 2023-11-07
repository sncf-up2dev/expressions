package fr.sncf.up2dev.math;

public class IntegerValue implements Value {

    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public Integer get() {
        return this.value;
    }
}
