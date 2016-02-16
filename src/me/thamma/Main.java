package me.thamma;

import me.thamma.compiler.lexer.Lexer;
import me.thamma.compiler.lexer.Token;
import me.thamma.cube.Cube;

import static me.thamma.cube.Cube.*;

import me.thamma.cube.Turn;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Cube c = new Cube();

        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
//        in = "y2 (L' B L') (F2 L B' L') (F2 L2)";
        in = "(r2M3)2 R2 [R,U]";
        //in = "[R, U' L' U]";//Niklas
        in = "[R'2: D2, [R:U]]";//A perm
        //in = "(( R U)2 D)";
        //in = "D' F2 U L2 R B' R U' B2 L2 B' U L' B F2 R F2 L B' U' F2 D' U L B L2 F D2 R U'";
        System.out.println("Compiling algorithm: " + in);

        Algorithm alg = new Algorithm(in);
        System.out.println(alg);

        c.turn(alg);
        System.out.println(alg.order());
//        String[] edgestrings = {"UL", "UF", "UR", "UB", "LB", "LF", "RF", "RB", "DL", "DF", "DR", "DB"};
//        int[] edges = {UL, UF, UR, UB, LB, LF, RF, RB, DL, DF, DR, DB};
//        for (int i = 0; i < edges.length; i++) {
//            System.out.println(edgestrings[i] + ": " + c.getPiece(edges[i])[3]);
//        }
//        for (int i = 0; i < edges.length; i++)
//            if (c.getPiece(edges[i])[3] == 1)
//                System.out.print(edgestrings[i] + " ");
    }

}
