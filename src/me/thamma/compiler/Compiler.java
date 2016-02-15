package me.thamma.compiler;

import me.thamma.compiler.parser.Parser;
import me.thamma.compiler.lexer.Lexer;
import me.thamma.compiler.lexer.Token;
import me.thamma.compiler.lexer.tokens.TokenTurn;
import me.thamma.compiler.parser.expressions.ASTExpression;
import me.thamma.cube.Turn;

import java.util.ArrayList;
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
        tokenList.stream().forEach(t -> System.out.print(t + " "));
        System.out.println();
        ASTExpression ast = Parser.parse(tokenList);
        System.out.println(ast);
        return new ArrayList<Turn>();
    }

}