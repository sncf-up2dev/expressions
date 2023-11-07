package fr.sncf.up2dev.math;

public class Addition extends BinaryExpression {

    protected Addition(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    protected int apply(int first, int second) {
        return first + second;
    }

    @Override
    protected double apply(double first, double second) {
        return first + second;
    }
}
