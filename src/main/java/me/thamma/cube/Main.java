package me.thamma.cube;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Turn;
import me.thamma.utils.CubeUtils;

import static me.thamma.cube.model.Turn.*;

public class Main {

    public static void main(String... args) {
        Cube.fromScramble("[M2U:M',U2]").printGrid("test");
        Algorithm alg = CubeUtils.bfsSolution(Cube.fromScramble("[M2U:M',U2]"), new Turn[]{UP, RIGHT,UP_PRIME, RIGHT_PRIME});
        System.out.println(alg);
    }

}