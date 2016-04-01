package me.thamma.cube.compiler.parser;

/**
 * Created by Dominic on 2/15/2016.
 */
public abstract class Expression {

    public abstract String customString();

    @Override
    public String toString() {
        return this.customString();
    }

}
