package fr.sncf.up2dev.math;

public abstract class UnaryExpression implements Expression {

    protected final Expression operand;

    protected UnaryExpression(Expression operand) {
        this.operand = operand;
    }
}
