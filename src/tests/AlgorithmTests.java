package tests;

import me.thamma.cube.Algorithm;
import me.thamma.cube.Cube;
import me.thamma.cube.Turn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AlgorithmTests {


    private Cube cube;
    private Algorithm[] algSet = new Algorithm[]{
            new Algorithm(Turn.DOWN),
            new Algorithm(Turn.DOWN, Turn.RIGHT),
            new Algorithm(Turn.DOWN, Turn.X, Turn.RIGHT),
            new Algorithm(Turn.DOWN, Turn.X, Turn.EQUATORIAL),
            new Algorithm(Turn.UP, Turn.EQUATORIAL, Turn.FRONT, Turn.UP)
    };;

    @Before
    public void setupSolvedCube() {
        cube = new Cube();
    }

    @Test
    public void finiteOrder() {
        for (Algorithm alg : algSet) {
            alg.order();
        }
    }

    //   U F R D B L  null
    //   1 2 3 4 5 6   0
    @Test
    public void selfInverseInverse() {
        for (Algorithm alg : algSet) {
            cube.solve();
            cube.turn(alg);
            cube.turn(alg.inverse());
            assertTrue(cube.isSolved());
        }
    }
}
