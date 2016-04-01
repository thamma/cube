package me.thamma.cube.compiler.parser.expressions;

import me.thamma.cube.compiler.parser.Expression;

/**
 * Created by Dominic on 2/15/2016.
 */
public class ParenthesesExpression extends Expression {

    private Expression exp;
    private int number;
    private boolean inverse;

    public ParenthesesExpression(Expression exp) {
        this(exp, 1, false);
    }

    public ParenthesesExpression(Expression exp, int i) {
        this(exp, i, false);
    }

    public ParenthesesExpression(Expression exp, int i, boolean inverse) {
        this.exp = exp;
        this.number = i;
        this.inverse = inverse;
    }

    public Expression getInner() {
        return this.exp;
    }

    public int getRepeats() {
        return this.number;
    }

    public boolean isInverse() {
        return this.inverse;
    }

    @Override
    public String customString() {
        return "PARENTHESES_EXPRESSION(" + this.exp + ")";
    }

}
