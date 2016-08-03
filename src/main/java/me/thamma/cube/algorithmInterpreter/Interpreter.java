package me.thamma.cube.algorithmInterpreter;

import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.lexer.Lexer;
import me.thamma.cube.algorithmInterpreter.lexer.Token;
import me.thamma.cube.algorithmInterpreter.parser.Parser;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.SeriesExpression;
import me.thamma.cube.model.Turn;

import java.util.List;

public class Interpreter {

    public static List<Turn> interprete(String input) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {
        List<Token> tokenList = Lexer.lex(input);
        SeriesExpression ast = Parser.parse(tokenList);
        List<Turn> algorithm = Evaluator.eval(ast);
        return algorithm;
    }

}