package me.thamma.cube.interpreter.parser.expressions;

import me.thamma.cube.interpreter.parser.Expression;

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

    public CommutatorType getType() {
        if (exp1 == null) return CommutatorType.COMMUTATOR;
        if (exp3 == null) return CommutatorType.CONJUGATE;
        return CommutatorType.MIXED;
    }

    public Expression getFirst() {
        return this.exp1;
    }
    public Expression getSecond() {
        return this.exp2;
    }
    public Expression getThird() {
        return this.exp3;
    }

    @Override
    public String customString() {
        return "COMMUTATOR_EXPRESSION("+ this.getType().name() + " " + this.exp1 + ":" + this.exp2 + "," + this.exp3 + ")";
    }
}
