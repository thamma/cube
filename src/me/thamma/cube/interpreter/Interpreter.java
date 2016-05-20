package me.thamma.cube.interpreter;

import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.lexer.Lexer;
import me.thamma.cube.interpreter.lexer.Token;
import me.thamma.cube.interpreter.parser.Parser;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.interpreter.parser.expressions.SeriesExpression;
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