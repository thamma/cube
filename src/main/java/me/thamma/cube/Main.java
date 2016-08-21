package me.thamma.cube;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Axis;

public class Main {

    public static void main(String... args) {
        Algorithm alg = Algorithm.fromScramble("F R U' R' U' R U R' F' R U R' U' R' F R F'").mirror(Axis.X);
        System.out.println(alg);
    }

}