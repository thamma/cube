package me.thamma.compiler.parser.expressions;

import me.thamma.compiler.parser.Expression;
import me.thamma.cube.Turn;

/**
 * Created by Dominic on 2/15/2016.
 */
public class TurnExpression extends Expression {

    private Turn turn;
    private int amount;
    private TurnExpression turnExpression;
    private TurnExpression inner;

    public TurnExpression(TurnExpression turnExpression, TurnExpression inner) {
        this(inner.turn, inner.amount);
        this.turnExpression = turnExpression;
        this.inner = inner; //the just added turnexpression
        assert inner.inner == null;
    }

    public TurnExpression(Turn turn, int i) {
        this.turn = turn;
        this.amount = i;
    }

    public TurnExpression(Turn turn) {
        this(turn, 1);
    }

    @Override
    public String toString() {
        if (this.inner == null) {
            return "TURN_EXPRESSION(" + this.turn.name() + "," + amount + ")";
        } else {
            return "TURN_EXPRESSION(" + this.turnExpression.toString() + "," + this.inner.turn + "," + this.inner.amount + ")";
        }
        //return "TURN_EXPRESSION(" + turns + ")";
    }

}
