package me.thamma.cube.algorithmInterpreter.parser;

import me.thamma.cube.algorithmInterpreter.lexer.Token;
import me.thamma.cube.algorithmInterpreter.lexer.tokens.*;
import me.thamma.cube.algorithmInterpreter.parser.expressions.CommutatorExpression;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.ParenthesesExpression;
import me.thamma.cube.algorithmInterpreter.parser.expressions.SeriesExpression;
import me.thamma.cube.algorithmInterpreter.parser.expressions.TurnExpression;
import me.thamma.cube.model.Turn;

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
                if (tokenList.size() >= 2 && tokenList.get(0) instanceof TokenTurn && tokenList.get(1) instanceof TokenRBRAC || tokenList.size() >= 3 && tokenList.get(1) instanceof TokenNumber && tokenList.get(2) instanceof TokenRBRAC) {
                    TokenTurn tokenTurn = (TokenTurn) tokenList.remove(0);
                    int amount = 1;
                    if (tokenList.get(0) instanceof TokenNumber)
                        amount = ((TokenNumber)tokenList.remove(0)).getNumber();
                    tokenList.remove(0);
                    Turn rotation = null;
                    switch (tokenTurn.getTurn()) {
                        case UP: {
                            rotation = Turn.Y;
                            break;
                        }
                        case FRONT: {
                            rotation = Turn.Z;
                            break;
                        }
                        case RIGHT: {
                            rotation = Turn.X;
                            break;
                        }
                        case DOWN: {
                            rotation = Turn.Y_PRIME;
                            break;
                        }
                        case BACK: {
                            rotation = Turn.Z_PRIME;
                            break;
                        }
                        case LEFT: {
                            rotation = Turn.X_PRIME;
                            break;
                        }

                        case UP_PRIME: {
                            rotation = Turn.Y_PRIME;
                            break;
                        }
                        case FRONT_PRIME: {
                            rotation = Turn.Z_PRIME;
                            break;
                        }
                        case RIGHT_PRIME: {
                            rotation = Turn.X_PRIME;
                            break;
                        }
                        case DOWN_PRIME: {
                            rotation = Turn.Y;
                            break;
                        }
                        case BACK_PRIME: {
                            rotation = Turn.Z;
                            break;
                        }
                        case LEFT_PRIME: {
                            rotation = Turn.X;
                            break;
                        }
                        default:
                            throw new UnexpectedTokenException(tokenTurn);
                    }
                    out.add(new TurnExpression(rotation, amount));
                } else {
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
                }
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
        if (tokenList.size() == 0 )
            throw new UnexpectedEndOfLineException();
        Token head = tokenList.get(0);
        boolean commutator;
        if (head instanceof TokenColon) {
            tokenList.remove(0);
            commutator = false;
        } else if (head instanceof TokenComma) {
            tokenList.remove(0);
            commutator = true;
        } else if (head instanceof TokenRBRAC) {
            return exp1;
        } else
            throw new UnexpectedTokenException(head);
        Expression exp2 = parseCommutatorExpression(tokenList);
        if (commutator)
            return new CommutatorExpression(null, exp1, exp2);
        return new CommutatorExpression(exp1, exp2, null);
    }

}


