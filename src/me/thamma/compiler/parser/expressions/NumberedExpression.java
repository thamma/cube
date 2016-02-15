package me.thamma.compiler.parser.expressions;

import me.thamma.compiler.parser.Expression;

/**
 * Created by Dominic on 2/15/2016.
 */
public class NumberedExpression extends Expression {

    private Expression exp;
    private int number;

    public NumberedExpression(Expression exp, int i) {
        this.exp = exp;
        this.number = i;

    }

    @Override
    public String toString() {
        return "NUMBERED_EXPRESSION(" + this.exp + ", " + number + ")";
    }

}
