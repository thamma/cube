package me.thamma;

import me.thamma.cube.Algorithm;
import me.thamma.cube.Cube;
import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;

public class Main {

    public static void main(String[] args) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {
//        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
//        in = "[R, U' L' U]";//Niklas
//        in = "[R'2: D2, [R:U]]";//A perm

        Cube c = new Cube();
        Algorithm alg = new Algorithm("[R, U' L' U]");
        System.out.println(alg.getCycle());

    }

}
