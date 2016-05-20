package me.thamma;

import me.thamma.cube.Cube;
import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException, AWTException {
//        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
//        in = "[R, U' L' U]";//Niklas
//        in = "[R'2: D2, [R:U]]";//A perm
        System.out.println("called");
        Cube c = new Cube("R'xz");
        Cube d = new Cube("Lw'");
        System.out.println(c.equals(d));
    }

}
