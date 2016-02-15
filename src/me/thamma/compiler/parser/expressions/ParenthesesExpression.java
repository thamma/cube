package me.thamma.compiler.parser.expressions;

import me.thamma.compiler.parser.Expression;

/**
 * Created by Dominic on 2/15/2016.
 */
public class ParenthesesExpression extends Expression {

    private Expression exp;

    public ParenthesesExpression(Expression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "PARENTHESES_EXPRESSION(" + this.exp + ")";
    }

}
