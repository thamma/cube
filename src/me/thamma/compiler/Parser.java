package me.thamma.compiler;

import me.thamma.compiler.lexer.Token;
import me.thamma.compiler.lexer.tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Parser {

    public static List<TokenTurn> parse(List<Token> tokenList) {
        List<TokenTurn> out = new CopyOnWriteArrayList<TokenTurn>();
        out.addAll(parseExp(tokenList));
        return out;
    }

    public static List<TokenTurn> parseExp(List<Token> tokenList) {
        CopyOnWriteArrayList<TokenTurn> out = new CopyOnWriteArrayList<TokenTurn>();
        if (tokenList.size() <= 0) return out;
        Token head = tokenList.remove(0);
        if (head instanceof TokenLPAR) {
            int i;
            searchRPAR:
            for (i = 0; i < tokenList.size(); i++) {
                if (tokenList.get(i) instanceof TokenRPAR)
                    break searchRPAR;
            }
            CopyOnWriteArrayList<Token> inParentheses = new CopyOnWriteArrayList<Token>(tokenList.subList(0, i));
            tokenList = tokenList.subList(i + 1, tokenList.size());
            //test for quantifiers
            if (!tokenList.isEmpty() && tokenList.get(0) instanceof TokenNumber) {
                TokenNumber numberToken = (TokenNumber) tokenList.get(0);
                CopyOnWriteArrayList<TokenTurn> inPars = new CopyOnWriteArrayList<TokenTurn>(parseExp(inParentheses));
                for (int j = 0; j < numberToken.getNumber(); j++) {
                    //parse recursively
                    out.addAll(inPars);
                }
                tokenList.remove(0);
            } else {

            }

        } else if (head instanceof TokenRPAR) {
            //Code should never be reached
            return out;
        } else if (head instanceof TokenTurn) {
            if (tokenList.size() > 0 && tokenList.get(0) instanceof TokenNumber) {
                TokenNumber numberToken = (TokenNumber) tokenList.remove(0);
                for (int i = 0; i < numberToken.getNumber(); i++)
                    out.add((TokenTurn) head);
            }
            out.add((TokenTurn) head);
            out.addAll(parseExp(tokenList));
        }
        return out;
    }
}