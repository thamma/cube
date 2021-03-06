package me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions;

import me.thamma.cube.algorithmInterpreter.lexer.Token;

/**
 * Created by Dominic on 2/16/2016.
 */
public class UnexpectedTokenException extends Exception {

    private Token headToken;

    public UnexpectedTokenException() {

    }

    public UnexpectedTokenException(Token head) {
        super("Unexpected Token found: " + head.toString());
        this.headToken = head;
    }

    public UnexpectedTokenException(String message) {
        super(message);
    }
}
