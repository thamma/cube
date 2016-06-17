package me.thamma;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Cycles;
import me.thamma.utils.CubeUtils;

public class Main {

    public static void main(String[] args) {
        // String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
        // in = "[R, U' L' U]";//Niklas
        // in = "[R'2: D2, [R:U]]";//A perm
        // System.out.println(new Cube().normalize());
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

        Algorithm alg = new Algorithm("[R]");
        System.out.println(alg + " foils to " + alg.simplify() + "  ord(" + alg + ") = " + alg.getOrder());
    }
}
