package me.thamma.cube.algorithmInterpreter.lexer.tokens;

import me.thamma.cube.algorithmInterpreter.lexer.Token;

/**
 * Created by Dominic on 1/24/2016.
 */
public class TokenNumber extends Token {

    private int number;

    public TokenNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }


    @Override
    public String toString() {
        return "TOKEN_" + this.number;
    }

}
