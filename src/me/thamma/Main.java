package me.thamma;

import me.thamma.compiler.lexer.Lexer;
import me.thamma.compiler.lexer.Token;
import me.thamma.cube.Cube;
import me.thamma.cube.Turn;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Algorithm a = new Algorithm(Turn.RIGHT, Turn.RIGHT_PRIME);
        System.out.println(a.order());
        Cube c = new Cube();
        c.turn(a);

        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
//        in = "y2 (L' B L') (F2 L B' L') (F2 L2)";
        in = "(r2M3)2 R2 [R,U]";
        //in = "U F";
        System.out.println("Compiling algorithm: " + in);

        Algorithm alg = new Algorithm(in);

    }

}
