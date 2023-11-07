package fr.sncf.up2dev.math;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionTest {

    @Test
    public void testIntegerConstant() {
        testExpression("1", 1);
    }

    @Test
    public void testRealConstant() {
        testExpression("1.0", 1D);
    }

    @Test
    public void testAdd() {
        testExpression("1 + 2", 3);
    }

    @Test
    public void testSubtract() {
        testExpression("1 - 2", -1);
    }

    @Test
    public void testMultiply() {
        testExpression("3 * 2", 6);
    }

    @Test
    public void testIntegerDivision() {
        testExpression("5 / 2", 2);
    }

    @Test
    public void testRealDivision() {
        testExpression("5.0 / 2.0", 2.5);
    }

    @Test
    public void testOpposite() {
        testExpression("-1 * 3", -3);
    }

    @Test
    public void testAddZero() {
        testExpression("1 + 0", 1);
    }

    @Test
    public void testTimesZero() {
        testExpression("1 * 0", 0);
    }

    @Test
    public void testTimesOne() {
        testExpression("1 * 3", 3);
    }

    @Test
    public void testParentheses() {
        testExpression("(1 + 2) * 3", 9);
    }

    private void testExpression(String expression, int expected) {
        Expression exp = Expression.parse(expression);
        Assertions.assertEquals(exp.calculate().get().intValue(), expected);
    }

    private void testExpression(String expression, double expected) {
        Expression exp = Expression.parse(expression);
        Assertions.assertEquals(exp.calculate().get().doubleValue(), expected);
    }
}