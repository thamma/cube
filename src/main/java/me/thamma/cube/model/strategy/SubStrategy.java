package me.thamma.cube.model.strategy;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Turn;
import me.thamma.cube.model.regex.CubeRegex;
import me.thamma.utils.CubeUtils;

import static me.thamma.cube.model.Turn.*;

public abstract class SubStrategy {

    public static Turn[] PRIMITIVE_TURNS = new Turn[]{UP, UP_PRIME, FRONT, FRONT_PRIME, RIGHT, RIGHT_PRIME, DOWN, DOWN_PRIME, BACK, BACK_PRIME, LEFT, LEFT_PRIME};
    public static Turn[] ALL_TURNS = new Turn[]{UP, FRONT, RIGHT, DOWN, BACK, LEFT, MIDDLE, EQUATORIAL, STANDING};

    Algorithm getSolution(Cube cube) {
        CubeRegex regex;
        try {
            regex = getRegex();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        for (Algorithm algorithm : solutionSet())
            if (regex.matches(cube.clone().turn(algorithm)))
                return algorithm;
        return CubeUtils.bfsSolution(cube, regex, getTurns());
    }

    Algorithm[] solutionSet() {
        return new Algorithm[]{};
    }

    abstract Turn[] getTurns();

    abstract CubeRegex getRegex() throws Exception;

}
