package me.thamma.tools.badedges;

import me.thamma.cube.Algorithm;
import me.thamma.cube.Cube;

import static me.thamma.cube.Cube.*;

import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;

import java.util.Scanner;

/**
 * Created by Dominic on 2/26/2016.
 */
public class BadEdgesMain {

    public static final String[] edgestrings = {"UL", "UF", "UR", "UB", "BL", "FL", "FR", "BR", "DL", "DF", "DR", "DB"};
    public static final int[] edges = {UL, UF, UR, UB, BL, FL, FR, BR, DL, DF, DR, DB};


    public static void main(String[] args) {

        Cube c = new Cube();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Insert scramble algorithm:");
            String in = sc.nextLine();
            Algorithm alg = null;
            try {
                alg = new Algorithm(in);
             } catch (UnexpectedTokenException e) {
                e.printStackTrace();
                System.out.println("Invalid algorithm syntax!");
                break;
            } catch (IllegalCharacterException e) {
                e.printStackTrace();
                System.out.println("Invalid algorithm syntax!");
                break;
            } catch (UnexpectedEndOfLineException e) {
                e.printStackTrace();
                System.out.println("Invalid algorithm syntax!");
                break;
            }
            c.turn(alg);

            System.out.println("Your misoriented edges are:");
            for (int i = 0; i < edges.length; i++)
                if (c.getPiece(edges[i])[3] == 1)
                    System.out.print(edgestrings[i] + " ");
            System.out.println();
        }
    }
}
