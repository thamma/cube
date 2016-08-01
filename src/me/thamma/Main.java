package me.thamma;

import me.thamma.cube.model.Cube;
import me.thamma.utils.CubeUtils;

public class Main {

    public static void main(String[] args)  {
        System.out.println(CubeUtils.perfectSolve(Cube.fromScramble("((M'U)4xy')3")));
    }
}
