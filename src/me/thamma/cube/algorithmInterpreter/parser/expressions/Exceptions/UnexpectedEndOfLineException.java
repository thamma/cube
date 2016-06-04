package me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions;

import me.thamma.cube.algorithmInterpreter.lexer.Token;

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
