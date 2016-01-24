package me.thamma.compiler.lexer.tokens;

import me.thamma.compiler.lexer.Token;
import me.thamma.cube.Turn;

/**
 * Created by Dominic on 1/24/2016.
 */
public class TokenTurn extends Token {

    private Turn turn;

    public TokenTurn(Turn turn) {
        this.turn = turn;
    }

    public Turn getTurn() {
        return this.turn;
    }

    @Override
    public String toString() {
        return "TOKEN_" + this.turn.name();
    }

}
