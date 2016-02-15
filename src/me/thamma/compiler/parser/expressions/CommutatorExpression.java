package me.thamma.compiler.parser.expressions;

import me.thamma.compiler.parser.Expression;

/**
 * Created by Dominic on 2/15/2016.
 */
public class CommutatorExpression extends Expression {

    // possible encodings
    //  null exp1 exp2 -> Commutator
    //  exp1 exp2 null -> Conjugate
    //  exp1 exp2 exp3 -> Conjugate and Commutator

    private Expression exp1, exp2, exp3;

    public CommutatorExpression(Expression exp1, Expression exp2, Expression exp3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public String toString() {
        return "COMMUTATOR_EXPRESSION(" + this.exp1 + ":" + this.exp2 + "," + this.exp3 + ")";
    }
}
