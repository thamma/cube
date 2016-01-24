package me.thamma;

import me.thamma.cube.Cube;
import me.thamma.cube.Turn;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Algorithm a = new Algorithm(Turn.RIGHT);
        //System.out.println(a.order());
        Cube c = new Cube();
        c.turn(a);
        Cube solved = new Cube();
        for (int i = 0; i < c.pieces.length; i++)
            if (solved.pieces[i]!=c.pieces[i])
                System.out.println(i);
            System.out.println(Arrays.toString(c.getPiece(Cube.RB)));
    }

}
