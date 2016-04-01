package me.thamma.cube.compiler.parser.expressions.Exceptions;

import me.thamma.cube.compiler.lexer.Token;

/**
 * Created by Dominic on 4/1/2016.
 */
public class UnexpectedEndOfLineException extends Exception {

    private Token headToken;

    public UnexpectedEndOfLineException() {

    }

    public UnexpectedEndOfLineException(String message) {
        super(message);
    }
}
