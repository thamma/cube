package me.thamma.cube.interpreter.parser;

import me.thamma.cube.interpreter.lexer.Token;
import me.thamma.cube.interpreter.lexer.tokens.*;
import me.thamma.cube.interpreter.parser.expressions.*;
import me.thamma.cube.Turn;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;

import java.util.List;

public class Parser {

    public static SeriesExpression parse(List<Token> tokenList) throws UnexpectedTokenException, UnexpectedEndOfLineException {
        SeriesExpression out = parseExpression(tokenList);
//        while (!tokenList.isEmpty())
//          out.add(parseExp(tokenList));
        return out;
    }

    private static SeriesExpression parseExpression(List<Token> tokenList) throws UnexpectedTokenException, UnexpectedEndOfLineException {
        SeriesExpression out = new SeriesExpression();
        boolean EOL = false;
        while (tokenList.size() > 0 && !EOL) {
            Token head = tokenList.get(0);
            if (head instanceof TokenLPAR) {
                tokenList.remove(0);//LPAR
                Expression expression = parseExpression(tokenList);
                if (tokenList.size() == 0)
                    throw new UnexpectedEndOfLineException();
                tokenList.remove(0);//RPAR
                boolean inverse = false;
                if (tokenList.size() > 0 && tokenList.get(0) instanceof TokenInverse) {
                    inverse = true;
                    tokenList.remove(0);
                }
                if (tokenList.size() > 0 && tokenList.get(0) instanceof TokenNumber)
                    expression = new ParenthesesExpression(expression, ((TokenNumber) tokenList.remove(0)).getNumber());

                out.add(inverse ? new ParenthesesExpression(expression, true) : expression);
            } else if (head instanceof TokenLBRAC) {
                tokenList.remove(0);
                Expression commutatorExpression = parseCommutatorExpression(tokenList);
                tokenList.remove(0);//pop RBRAC
                boolean inverse = false;
                if (tokenList.size() > 0 && tokenList.get(0) instanceof TokenInverse) {
                    inverse = true;
                    tokenList.remove(0);
                }
                if (tokenList.size() > 0 && tokenList.get(0) instanceof TokenNumber)
                    commutatorExpression = new ParenthesesExpression(commutatorExpression, ((TokenNumber) tokenList.remove(0)).getNumber());

                out.add(inverse ? new ParenthesesExpression(commutatorExpression, true) : commutatorExpression);
            } else if (head instanceof TokenTurn) {
                Expression turnExpression = parseTurnExpression(tokenList);
                out.add(turnExpression);
            } else if (head instanceof TokenRPAR || head instanceof TokenRBRAC) {
                EOL = true;
            } else if (head instanceof TokenNumber) {
                throw new UnexpectedTokenException(head);
            } else if (head instanceof TokenColon || head instanceof TokenComma) {
                return out;
            }
        }
        return out;
    }

    private static Expression parseTurnExpression(List<Token> tokenList) {
        TurnExpression exp;
        Token head = tokenList.remove(0);
        Turn turn = ((TokenTurn) head).getTurn();
        if (!tokenList.isEmpty() && tokenList.get(0) instanceof TokenNumber) {
            int number = ((TokenNumber) tokenList.remove(0)).getNumber();
            exp = new TurnExpression(turn, number);
        } else {
            exp = new TurnExpression(turn);
        }
        if (!tokenList.isEmpty() && tokenList.get(0) instanceof TokenTurn) {
            exp = new TurnExpression((TurnExpression) parseTurnExpression(tokenList), exp);
        }
        return exp;
    }

    private static Expression parseCommutatorExpression(List<Token> tokenList) throws UnexpectedTokenException, UnexpectedEndOfLineException {
        Expression exp1 = parseExpression(tokenList);
        if (tokenList.size() == 0)
            throw new UnexpectedEndOfLineException();
        Token head = tokenList.remove(0);
        if (head instanceof TokenComma) {
            //only commutator
            Expression exp2 = parseExpression(tokenList);
            if (tokenList.size() == 0)
                throw new UnexpectedEndOfLineException();
            if (!(tokenList.get(0) instanceof TokenRBRAC))
                throw new UnexpectedTokenException("Commutator, found " + tokenList.get(0));
            return new CommutatorExpression(null, exp1, exp2);
        } else if (head instanceof TokenColon) {
            Expression exp2 = parseExpression(tokenList);
            if (tokenList.size() == 0)
                throw new UnexpectedEndOfLineException();
            head = tokenList.get(0);
            if (head instanceof TokenRBRAC) {
                //only conjugate
                return new CommutatorExpression(exp1, exp2, null);
            } else if (head instanceof TokenComma) {
                tokenList.remove(0); //pop head
                //mixed commutator
                Expression exp3 = parseExpression(tokenList);
                if (tokenList.size() == 0)
                    throw new UnexpectedEndOfLineException();
                if (!(tokenList.get(0) instanceof TokenRBRAC))
                    throw new UnexpectedTokenException("Commutator, found" + tokenList.get(0));
                return new CommutatorExpression(exp1, exp2, exp3);
            } else {
                throw new UnexpectedTokenException(head);
            }
        } else {
            throw new UnexpectedTokenException(head);
        }
    }

}


