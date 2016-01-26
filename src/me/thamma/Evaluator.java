package me.thamma;

import me.thamma.compiler.lexer.tokens.TokenTurn;
import me.thamma.cube.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dominic on 1/24/2016.
 */
public class Evaluator {

    public static List<Turn> eval (List<TokenTurn> tokenList) {
        List<Turn> out = tokenList.stream().map(TokenTurn::getTurn).collect(Collectors.toList());
        return out;
    }

}
