package me.thamma.cube;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        //System.out.println(CubeUtils.perfectSolve(Cube.fromScramble("R U R' U' R' F R2 U' R' U' R U R' F' U2 R U R' U' R' F R2 U' R' U' R U R' F' U2")));
        //System.out.println(Algorithm.fromScramble("F U2 F2 D F2 U2 F R2 U F2 U F2 U2 R2 D' F2").translate(Turn.Y).mirror(Turn.Z));

        PrintWriter out = null;
        try {
            out = new PrintWriter("testfile.txt");
            out.print("test");
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
