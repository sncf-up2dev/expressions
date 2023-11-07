package fr.sncf.up2dev.math;

public abstract class BinaryExpression implements Expression {

    protected final Expression first;
    protected final Expression second;

    protected BinaryExpression(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Value calculate() {
        Value firstValue = this.first.calculate();
        Value secondValue = this.second.calculate();
        if (
                firstValue instanceof IntegerValue firstInteger &&
                secondValue instanceof IntegerValue secondInteger
        ) {
            int result = this.apply(firstInteger.get(), secondInteger.get());
            return new IntegerValue(result);
        } else {
            double result = this.apply(firstValue.get().doubleValue(), secondValue.get().doubleValue());
            return new RealValue(result);
        }
    }

    abstract protected int apply(int first, int second);
    abstract protected double apply(double first, double second);
}
