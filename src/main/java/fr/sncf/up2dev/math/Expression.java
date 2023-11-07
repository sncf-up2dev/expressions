package fr.sncf.up2dev.math;

import java.util.function.BinaryOperator;

public interface Expression {

    Value calculate();

    static Expression parse(String input) {
        String str = input.trim();
        if (str.isEmpty()) {
            throw new MalformedExpression("Invalid empty expression");
        }
        char firstChar = str.charAt(0);
        if (firstChar == '(') {
            int position = 1;
            int openParentheses = 1;
            while (position < str.length() && openParentheses > 0) {
                char c = str.charAt(position++);
                openParentheses += c == '(' ? 1 : c == ')' ? -1 : 0;
            }
            if (openParentheses == 0) {
                String subExpression = str.substring(1, position - 1);
                Expression left = parse(subExpression);
                return parse(str, position, left);
            } else {
                throw new MalformedExpression("Invalid expression <%s>".formatted(str));
            }
        } else {
            int position = 0;
            char c = str.charAt(position);
            while (position < str.length() && (c >= '0' && c <= '9' || c == '.' || c == '-')) {
                c = str.charAt(position);
                position++;
            }
            if (position == 0) {
                throw new MalformedExpression("Invalid expression <%s>".formatted(str));
            } else {
                Expression left = parseConstant(str.substring(0, position));
                return parse(str, position, left);
            }
        }

    }

    static Expression parse(String input, int position, Expression left) {
        while (position != input.length() && Character.isWhitespace(input.charAt(position))) {
            position++;
        }
        if (position == input.length()) {
            return left;
        }
        char operator = input.charAt(position);
        String rightString = input.substring(position + 1);
        if (operator == '^') {
            if (!rightString.trim().equals("-1")) {
                throw new MalformedExpression("Invalid exponent <%s>".formatted(rightString));
            }
            return new Reciprocal(left);
        }
        Expression right = parse(rightString);
        BinaryOperator<Expression> function = switch (operator) {
            case '+' -> Addition::new;
            case '-' -> Subtraction::new;
            case '*' -> Multiplication::new;
            case '/' -> Division::new;
            default -> throw new MalformedExpression("Invalid operator <%s> in <%s>".formatted(operator, input));
        };
        return function.apply(left, right);
    }

    static private Expression parseConstant(String input) {
        String number = input.trim();
        try {
            Value value = input.contains(".")
                    ? new RealValue(Double.parseDouble(number))
                    : new IntegerValue(Integer.parseInt(number));
            return new ConstantExpression(value);
        } catch (NumberFormatException e) {
            throw new MalformedExpression("Invalid constant <%s>".formatted(input));
        }
    }
}
