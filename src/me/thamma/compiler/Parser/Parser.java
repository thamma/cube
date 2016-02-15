package me.thamma.compiler.parser;

import me.thamma.compiler.lexer.Token;
import me.thamma.compiler.lexer.tokens.*;
import me.thamma.compiler.parser.expressions.*;
import me.thamma.cube.Turn;

import java.util.List;

public class Parser {

    public static ASTExpression parse(List<Token> tokenList) {
        ASTExpression out = new ASTExpression();
        while (!tokenList.isEmpty())
            out.add(parseExp(tokenList));
        return out;
    }

    private static Expression parseExp(List<Token> tokenList) {
        Token head = tokenList.remove(0);
        if (head instanceof TokenLPAR) {
            Expression exp = parseExp(tokenList);
            assert tokenList.remove(0) instanceof TokenRPAR;
            if (tokenList.get(0) instanceof TokenNumber) {
                int number = ((TokenNumber) tokenList.remove(0)).getNumber();
                return new NumberedExpression(exp, number);
            } else {
                return exp;
            }
        } else if (head instanceof TokenTurn) {
            TurnExpression exp;
            Turn turn = ((TokenTurn) head).getTurn();
            if (!tokenList.isEmpty() && tokenList.get(0) instanceof TokenNumber) {
                int number = ((TokenNumber) tokenList.remove(0)).getNumber();
                exp = new TurnExpression(turn, number);
            } else {
                exp = new TurnExpression(turn);
            }

            if (!tokenList.isEmpty() && tokenList.get(0) instanceof TokenTurn) {
                exp = new TurnExpression((TurnExpression) parseExp(tokenList), exp);
            }
            return exp;
        } else if (head instanceof TokenLBRAC) {
            Expression exp1 = parseExp(tokenList);
            head = tokenList.remove(0);
            if (head instanceof TokenComma) {
                //only commutator
                Expression exp2 = parseExp(tokenList);
                assert tokenList.remove(0) instanceof TokenRBRAC;
                return new CommutatorExpression(null, exp1, exp2);
            } else if (head instanceof TokenColon) {
                Expression exp2 = parseExp(tokenList);
                head = tokenList.remove(0);
                if (head instanceof TokenLBRAC) {
                    //only conjugate
                    return new CommutatorExpression(exp1, exp2, null);
                } else if (head instanceof TokenComma) {
                    //mixed commutator
                    Expression exp3 = parseExp(tokenList);
                    assert tokenList.remove(0) instanceof TokenRBRAC;
                    return new CommutatorExpression(exp1, exp2, exp3);
                }
            }
        }
        return null;
    }


}


