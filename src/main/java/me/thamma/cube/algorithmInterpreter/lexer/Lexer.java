package me.thamma.cube.algorithmInterpreter.lexer;

import me.thamma.cube.algorithmInterpreter.lexer.tokens.*;
import me.thamma.cube.model.Turn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominic on 1/24/2016.
 */
public class Lexer {

    public static List<Token> lex(String input) throws IllegalCharacterException {
        List<Token> tokenList = new ArrayList<Token>();
        while (input.length() > 0) {
            char c = input.charAt(0);
            input = input.substring(1);
            switch (c) {
                case 'U':
                case 'F':
                case 'R':
                case 'D':
                case 'B':
                case 'L':
                case 'u':
                case 'f':
                case 'r':
                case 'd':
                case 'b':
                case 'l': {
                    String add = "";
                    if (input.length() > 0 && input.charAt(0) == 'w') {
                        add += "w";
                        input = input.substring(1);
                    }
                    boolean num = false;
                    if (input.length() > 0 &&'1' <= input.charAt(0) && input.charAt(0) <= '9') {
                        input = lexNumber(input.charAt(0), input.substring(1), tokenList);
                        num = true;
                    }
                    if (input.length() > 0 && input.charAt(0) == '\'') {
                        add += "'";
                        input = input.substring(1);
                    }
                    Turn t = Turn.byString(c + add);
                    if (num) {
                        tokenList.add(tokenList.size() - 1, new TokenTurn(t));
                    } else {
                        tokenList.add(new TokenTurn(t));
                    }
                    break;
                }
                case 'X':
                case 'Y':
                case 'Z':
                case 'x':
                case 'y':
                case 'z':
                case 'M':
                case 'E':
                case 'S': {
                    boolean num = false;
                    if (input.length() > 0 &&'1' <= input.charAt(0) && input.charAt(0) <= '9') {
                        input = lexNumber(input.charAt(0), input.substring(1), tokenList);
                        num = true;
                    }
                    String add = "";
                    if (input.length() > 0 && input.charAt(0) == '\'') {
                        add += "'";
                        input = input.substring(1);
                    }
                    Turn t = Turn.byString(c + add);
                    if (num) {
                        tokenList.add(tokenList.size() - 1, new TokenTurn(t));
                    } else {
                        tokenList.add(new TokenTurn(t));
                    }
                    break;
                }
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    input = lexNumber(c, input, tokenList);
                    break;
                }
                case '(': {
                    tokenList.add(new TokenLPAR());
                    break;
                }
                case ')': {
                    tokenList.add(new TokenRPAR());
                    break;
                }
                case '[': {
                    tokenList.add(new TokenLBRAC());
                    break;
                }
                case ']': {
                    tokenList.add(new TokenRBRAC());
                    break;
                }
                case ':': {
                    tokenList.add(new TokenColon());
                    break;
                }
                case ',': {
                    tokenList.add(new TokenComma());
                    break;
                }
                case '\'': {
                    tokenList.add(new TokenInverse());
                    break;
                }
                case ' ':
                    break;
                default: {
//                    System.out.println("Lexer ignored unknown input symbol: '" + c + "'");
                    throw new IllegalCharacterException(c);
                }
            }
        }
        return tokenList;
    }

    public static String lexNumber(char lead, String rest, List<Token> tokenList) {
        int out = (lead - '0');
        while (rest.length()> 0 && '0' <= rest.charAt(0) && rest.charAt(0) <= '9') {
            out = out * 10 + (rest.charAt(0) - '0');
            rest = rest.substring(1);
        }
        tokenList.add(new TokenNumber(out));
        return rest;
    }

}
