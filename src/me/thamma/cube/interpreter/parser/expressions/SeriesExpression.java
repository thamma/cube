package me.thamma.cube.interpreter.parser.expressions;

import me.thamma.cube.interpreter.parser.Expression;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Dominic on 2/15/2016.
 */
public class SeriesExpression extends Expression implements Iterable<Expression> {

    private List<Expression> expressions;

    public SeriesExpression() {
        this.expressions = new CopyOnWriteArrayList<Expression>();
    }

    public void add(Expression expression) {
        this.expressions.add(expression);
    }


    @Override
    public String customString() {
        String s = "SERIES_EXPRESSION(";
        for (int i = 0; i < this.expressions.size(); i++) {
            s += this.expressions.get(i) + (i != expressions.size() - 1 ? "@" : "");
        }
        return s;
    }

    @Override
    public Iterator<Expression> iterator() {
        return this.expressions.iterator();
    }
}
