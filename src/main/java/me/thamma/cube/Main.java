package me.thamma.cube;

import me.thamma.cube.model.Algorithm;

public class Main {

    public static void main(String... args) throws Exception {
        System.out.printf("%d", Algorithm.fromScramble("R'y").getOrder());;
    }

}
