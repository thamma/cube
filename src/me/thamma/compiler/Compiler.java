package me.thamma.compiler;

import me.thamma.Algorithm;

public class Compiler {

    /* exp ::= exp [exp] | com | alg | ( exp ) [num]
     * com ::= [exp , exp]
     * alg ::= turn [alg]
     * turn::= ...
     * num ::= ...
     * */
    public static Algorithm compile(String input) {
        return new Algorithm();
    }

    public boolean lexer(String s) {

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {

            }
        }
        return false;
    }
}