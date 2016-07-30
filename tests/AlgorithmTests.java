import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Turn;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AlgorithmTests {

    @Test
    public void testPurgeSliceTurns() {
        for (String s : bigSet) {
            Algorithm alg = new Algorithm(s);
            assertTrue(alg.clone().purgeSliceTurns().isCongruent(alg) != null);
        }
    }

    @Test
    public void testPurgeRotations() {
        for (String s : bigSet) {
            Algorithm alg = new Algorithm(s);
            System.out.println( );
            System.out.println(alg);
            System.out.println(alg.clone().purgeSliceTurns().purgeRotations());
            assertTrue(alg.clone().purgeSliceTurns().purgeRotations().isCongruent(alg) != null);
        }
    }

    @Test
    public void testCorrectSyntax() {
        for (int i = 0; i < rawAlgorithms.length; i++)
            assertTrue(rawAlgorithms[i].equals(new Algorithm(parsedAlgorithms[i])));
    }

    @Test
    public void correctSemantics() {
        for (int i = 0; i < rawAlgorithms.length; i++)
            assertTrue(new Cube(rawAlgorithms[i]).equals(Cube.fromScramble(parsedAlgorithms[i])));
    }

    @Test
    public void selfInverseInverse() {
        for (Algorithm alg : rawAlgorithms) {
            System.out.println("testing: " + alg);
            Cube cube = new Cube();
            cube.turn(alg).turn(alg.inverse());
            assertTrue(cube.isSolved());
        }
    }


    public static Algorithm[] rawAlgorithms = new Algorithm[]{
            new Algorithm(Turn.DOWN),
            new Algorithm(Turn.DOWN, Turn.RIGHT),
            new Algorithm(Turn.DOWN, Turn.X, Turn.RIGHT),
            new Algorithm(Turn.DOWN, Turn.X, Turn.EQUATORIAL),
            new Algorithm(Turn.UP, Turn.EQUATORIAL, Turn.FRONT, Turn.UP),
            new Algorithm(Turn.RIGHT_WIDE, Turn.MIDDLE, Turn.UP_WIDE_PRIME, Turn.STANDING),
            new Algorithm(Turn.UP, Turn.RIGHT, Turn.UP_PRIME, Turn.RIGHT_PRIME),
            new Algorithm(Turn.UP, Turn.RIGHT, Turn.UP_PRIME)
    };

    public static String[] parsedAlgorithms = new String[]{
            "D",
            "D R",
            "D x R",
            "D x E",
            "U E F U",
            "r M u' S",
            "[U, R]",
            "[U: R]"
    };


    public static String[] bigSet = {
            " [[RBU: RU' RU RU RU' R'U' R2], x2 y'] [B2: R U2 R' U' R U' R' L' U2 L U L' U L]",
            "M2 U M U2 M' U M2",
            "R U R' U' R' F R2 U' R' U' R U R' F'",
            "y' L' R' U2 L R (y) L U' R U2 L' U R' U2",
            "x' R U' R' D R U R' Uw2 R' U R D R' U' R",
            "l' U R' D2 R U' R' D2 R2",
            "l' R' D2 R U R' D2 R U' R x'",
            "y x' R U' R' D R U R' D' R U R' D R U' R' D' x",
            "y R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R",
            "R2 u R' U R' U' R u' R2 y' R' U R",
            "R' U' R y R2 u R' U R U' R u' R2",
            "R2 u' R U' R U R' u R2 y R U' R'",
            "R U R' y' R2 u' R U' R' U R' u R2",
            "M2 U M2 U2 M2 U M2",
            "R' U L' U2 R U' R' U2 R L",
            "R U R' F' R U R' U' R' F R2 U' R' U'",
            "R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R'",
            "R' U L' U2 R U' L R' U L' U2 R U' L",
            "L U2 L' U2 L F' L' U' L U L F L2",
            "R' U2 R U2 R' F R U R' U' R' F' R2",
            "R U R' U' R' F R2 U' R' U' R U R' F'",
            "R2 U' R' U' R U R U R U' R",
            "R' U R' U' R' U' R' U R U R2",
            "R' U R' d' R' F' R2 U' R' U R' F R F",
            "F R U' R' U' R U R' F' R U R' U' R' F R F'",
            "M2 U M2 U M' U2 M2 U2 M'",
            "y M2' U' M2' U' M' U2' M2' U2' M'",
            "M' U' M2' U' M2' U' M' U2' M2'",
            "R' U' R2 U R U R' U' R U R U' R U' R'"};


}
