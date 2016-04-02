package me.thamma.cube.compiler;

import me.thamma.cube.Algorithm;
import me.thamma.cube.compiler.parser.Expression;
    import me.thamma.cube.compiler.parser.expressions.CommutatorExpression;
import me.thamma.cube.compiler.parser.expressions.ParenthesesExpression;
import me.thamma.cube.compiler.parser.expressions.SeriesExpression;
import me.thamma.cube.compiler.parser.expressions.TurnExpression;

/**
 * Created by Dominic on 1/24/2016.
 */
public class Evaluator {

    public static Algorithm eval(SeriesExpression ast) {
        Algorithm out = new Algorithm();
        for (Expression exp : ast) {
            out.addAll(evalExpression(exp));
        }
        return out;
    }

    public static Algorithm evalExpression(Expression exp) {
        if (exp instanceof ParenthesesExpression) {
            return evalParenthesesExpression((ParenthesesExpression) exp);
        } else if (exp instanceof TurnExpression) {
            return evalTurnExpression((TurnExpression) exp);
        } else if (exp instanceof CommutatorExpression) {
            return evalCommutatorExpression((CommutatorExpression) exp);
        } else if (exp instanceof SeriesExpression) {
            return evalSeriesExpression((SeriesExpression) exp);
        }
        return new Algorithm();
    }

    private static Algorithm evalSeriesExpression(SeriesExpression seriesExpression) {
        Algorithm out = new Algorithm();
        for (Expression expression: seriesExpression) {
            out.addAll(evalExpression(expression));
        }
        return out;
    }

    private static Algorithm evalParenthesesExpression(ParenthesesExpression parenthesesExpression) {
        Algorithm out;
        out = evalExpression(parenthesesExpression.getInner());
        out = out.power(parenthesesExpression.getRepeats());
        if (parenthesesExpression.isInverse())
            out = out.inverse();
        return out;
    }

    private static Algorithm evalCommutatorExpression(CommutatorExpression commutatorExpression) {
        Algorithm out = new Algorithm();
        Algorithm a1, a2, a3;
        switch (commutatorExpression.getType()) {
            case COMMUTATOR:
                a2 = evalExpression(commutatorExpression.getSecond());
                a3 = evalExpression(commutatorExpression.getThird());
                out.addAll(a2);
                out.addAll(a3);
                out.addAll(a2.inverse());
                out.addAll(a3.inverse());
                break;
            case CONJUGATE:
                a1 = evalExpression(commutatorExpression.getFirst());
                a2 = evalExpression(commutatorExpression.getSecond());
                out.addAll(a1);
                out.addAll(a2);
                out.addAll(a1.inverse());
                break;
            case MIXED:
                a1 = evalExpression(commutatorExpression.getFirst());
                a2 = evalExpression(commutatorExpression.getSecond());
                a3 = evalExpression(commutatorExpression.getThird());
                out.addAll(a1);
                out.addAll(a2);
                out.addAll(a3);
                out.addAll(a2.inverse());
                out.addAll(a3.inverse());
                out.addAll(a1.inverse());
                break;
        }
        return out;
    }

    private static Algorithm evalTurnExpression(TurnExpression turnExpression) {
        Algorithm out = new Algorithm();
        Algorithm temp;

        temp = new Algorithm(turnExpression.getTurn());
        temp = temp.power(turnExpression.getAmount());
        out.addAll(temp);

        while (turnExpression.getChild() != null) {
            turnExpression = turnExpression.getChild();
            temp = new Algorithm(turnExpression.getTurn());
            temp = temp.power(turnExpression.getAmount());
            out.addAll(temp);
        }
        return out;
    }

}
