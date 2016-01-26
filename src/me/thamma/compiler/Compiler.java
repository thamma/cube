package me.thamma.compiler;

import me.thamma.Algorithm;
import me.thamma.Evaluator;
import me.thamma.compiler.lexer.Lexer;
import me.thamma.compiler.lexer.Token;
import me.thamma.compiler.lexer.tokens.TokenTurn;
import me.thamma.cube.Turn;

import java.util.List;

public class Compiler {

    /* exp ::= exp [exp] | com | alg | ( exp ) [num]
     * com ::= [exp , exp]
     * alg ::= turn [alg]
     * turn::= ...
     * num ::= ...
     * */
    public static List<Turn> compile(String input) {
        List<Token> tokenList = Lexer.lex(input);
        tokenList.stream().forEach(t -> {
            System.out.print(t+ " ");
        });
        System.out.println();
        List<TokenTurn> turnTokenList = Parser.parse(tokenList);
        turnTokenList.stream().forEach(t -> {
            System.out.print(t + "  ");
        });
        System.out.println();
        List<Turn> turnList = Evaluator.eval(turnTokenList);
        turnList.stream().forEach(t -> {
            System.out.print(t+ " ");
        });
        System.out.println();
        return turnList;
    }

}