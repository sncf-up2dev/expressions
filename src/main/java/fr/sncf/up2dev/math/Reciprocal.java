package fr.sncf.up2dev.math;

public class Reciprocal extends UnaryExpression {

    protected Reciprocal(Expression operand) {
        super(operand);
    }

    @Override
    public Value calculate() {
        double original = operand.calculate().get().doubleValue();
        return new RealValue(1D / original);
    }
}
