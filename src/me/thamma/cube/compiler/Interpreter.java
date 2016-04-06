package me.thamma.cube.compiler;

import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.lexer.Lexer;
import me.thamma.cube.compiler.lexer.Token;
import me.thamma.cube.compiler.parser.Parser;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.compiler.parser.expressions.SeriesExpression;
import me.thamma.cube.Turn;

import java.util.List;

public class Interpreter {

    public static List<Turn> interprete(String input) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {
        List<Token> tokenList = Lexer.lex(input);
        SeriesExpression ast = Parser.parse(tokenList);
        List<Turn> algorithm = Evaluator.eval(ast);
        return algorithm;
    }

}