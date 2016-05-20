package me.thamma.cube.interpreter.parser.expressions.Exceptions;

import me.thamma.cube.interpreter.lexer.Token;

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
