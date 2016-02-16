package me.thamma.compiler.parser.expressions;

import me.thamma.compiler.parser.Expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Dominic on 2/15/2016.
 */
public class SeriesExpression extends Expression implements Iterable<Expression>{

    List<Expression> expressions;

    public SeriesExpression() {
        this.expressions = new CopyOnWriteArrayList<Expression>();
    }

    public SeriesExpression(List<Expression> expressions) {
        this();
        this.expressions = expressions;
    }

    public void addAll(Collection<Expression> expressionCollection) {
        this.expressions.addAll(expressionCollection);
    }

    public void add(Expression exp) {
        this.expressions.add(exp);
    }

    @Override
    public String toString() {
        String inner = "";
        for (int i = 0; i < expressions.size(); i++) {
            inner += expressions.get(i).toString() + (i < expressions.size() - 1 ? "," : "");
        }
        return "AST_EXPRESSION(" + inner + ")";
    }

    @Override
    public Iterator<Expression> iterator() {
        return this.expressions.iterator();
    }
}
