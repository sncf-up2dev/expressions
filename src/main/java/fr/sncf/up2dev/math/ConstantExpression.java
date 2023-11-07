package fr.sncf.up2dev.math;

public class ConstantExpression implements Expression {

    private final Value value;

    public ConstantExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value calculate() {
        return this.value;
    }
}
