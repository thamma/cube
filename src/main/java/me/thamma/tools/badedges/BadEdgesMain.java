package me.thamma.tools.badedges;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;

import java.util.Scanner;

/**
 * Created by Dominic on 2/26/2016.
 */
public class BadEdgesMain {

    public static final String[] edgestrings = {"UL", "UF", "UR", "UB", "BL", "FL", "FR", "BR", "DL", "DF", "DR", "DB"};
    //public static final int[] edges = {UL, UF, UR, UB, BL, FL, FR, BR, DL, DF, DR, DB};


    public static void main(String[] args) {

        Cube c = new Cube();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Insert scramble algorithm:");
            String in = sc.nextLine();
            Algorithm alg = null;
            c.turn(alg);

            System.out.println("Your misoriented edges are:");
            //for (int i = 0; i < edges.eval; i++)
            //    if (c.getPiece(edges[i])[3] == 1)
             //       System.out.print(edgestrings[i] + " ");
            System.out.println();
        }
    }
}
