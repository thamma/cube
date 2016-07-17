package me.thamma;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Turn;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
        // in = "[R, U' L' U]";//Niklas
        // in = "[R'2: D2, [R:U]]";//A perm
        // System.out.println(new Cube().normalizeRotation());
        // Cube c = new Cube(" [[RBU: RU' RU RU RU' R'U' R2], x2 y']");
        // Cube c = new Cube(" [[RBU: RU' RU RU RU' R'U' R2], x2 y'] [B2: R U2 R' U' R U' R' L' U2 L U L' U L]");
        //System.out.println(new Algorithm("ru").getOrder());
//
//        Algorithm a = new Algorithm(" [[RBU: RU' RU RU RU' R'U' R2], x2 y'] [B2: R U2 R' U' R U' R' L' U2 L U L' U L]");
//        Algorithm b = new Algorithm(new Cube(a));
//        System.out.println(b);
//        System.out.println(a);
//
//        System.out.println(a.isCongruent(b));
        // System.out.println(CubeUtils.perfectSolve(new Cube(new Algorithm("[E,M] x2 [BU': M' ,[L,U]] z' [B'U2: M' ,[L,U]]"))));
        // System.out.println(Search.solution("DLBLUBBFLBDRDRFDRBLUUUFRFLRUBFRDUFRULFDULLUBRDDFDBBRFL", 20, 500, false));
        // c.turn("((M'U)4 xy' )3");
        // Algorithm mirrorCube = new Algorithm(Search.solution("FBLBURBRLBDFFRLRDFLUUUFDFBDRUBRDLBLRDLDRLFUBDUDLFBFUUR", 20, 500, false));
        // System.out.println(mirrorCube.inverse());
        // c.turn("U L' L' B' B' L' L' D U' U' L R' U' U' F' F' D' R' R' B' D F L' R' B' B' F' U' U L' L' B' B' L' L' D U' U' L R' U' U' F' F' D' R' R' B' D F L' R' B' B' F' U'");
        // System.out.println(solve(c));
        // System.out.println(CubeUtils.solve(cube));

        //Algorithm alg = new Algorithm("[x2 y', F': RU2R', D'] [E', M] [L2 U': RU2R'U'RU'R' L'U2LUL'UL] ");
        //Algorithm alg = new Algorithm("[x2 y', F': RU2R', D']");
        //System.out.printf("order: %d,   cycle: %s", alg.getOrder(), alg.getCycles());
        // System.out.println(CubeUtils.perfectSolve(new Cube(alg)));
        // System.out.println(new Algorithm("(U R' F R)").getOrder());
        // System.out.println(alg + " foils to " + alg.cancelOut() + "  ord(" + alg + ") = " + alg.getOrder());
        // Cube cube = Cube.fromScramble("L' B F D U B' F' L2 R2 D B' R' B D2 U' B2 F2 D' U2 R B D' U' B F' R' U2 L2 B F");
        // System.out.println(new Cycles(cube));

         Algorithm[] cubeOrientations = {
                new Algorithm("     "),
                new Algorithm("   y "),
                new Algorithm("   y2"),
                new Algorithm("   y'"),
                new Algorithm(" x   "),
                new Algorithm(" x y "),
                new Algorithm(" x y2"),
                new Algorithm(" x y'"),
                new Algorithm("x2   "),
                new Algorithm("x2 y "),
                new Algorithm("x2 y2"),
                new Algorithm("x2 y'"),
                new Algorithm("x'   "),
                new Algorithm("x' y "),
                new Algorithm("x' y2"),
                new Algorithm("x' y'"),
                new Algorithm(" z   "),
                new Algorithm(" z y "),
                new Algorithm(" z y2"),
                new Algorithm(" z y'"),
                new Algorithm("z'   "),
                new Algorithm("z' y "),
                new Algorithm("z' y2"),
                new Algorithm("z' y'")
        };
        for (int i = 0; i < cubeOrientations.length; i++) {
            System.out.println(cubeOrientations[i].clone());
        }

    }
}
