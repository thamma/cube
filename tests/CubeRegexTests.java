import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class CubeRegexTests {

    @Test
    public void trivialRegex() {
        assertTrue(new Cube().matches("(.)54"));
        assertTrue((new Cube().matches("......................................................")));
        assertTrue(new Cube().matches(""));
        assertTrue(Cube.fromScramble("[R,UFD]").matches("(.)54"));
        assertTrue(Cube.fromScramble("[R,UFD]").matches("......................................................"));
        assertTrue(Cube.fromScramble("[R,UFD]").matches(""));
    }

    @Test
    public void oneSide() {
        Cube oneSideSolvedCube = Cube.fromScramble("r F r' d L D2 L'");
        assertTrue(oneSideSolvedCube.matches("(U)9(.)45"));
        assertTrue(oneSideSolvedCube.matches("(U)9"));
        assertTrue(oneSideSolvedCube.matches("UUUUUUUUU............................................."));
        assertTrue(oneSideSolvedCube.matches("UUUUUUUUU"));
    }

    @Test
    public void exactMatch() {
        Cube scrambledCube = Cube.fromScramble("M'");
        assertTrue(scrambledCube.matches("(URF:U, ULB:U, UB:F, FU:D)"));
        assertTrue(scrambledCube.matches("(URF:U, ULB:U, UB:F, FU:D)......................................................"));
        assertTrue(scrambledCube.matches("UF......U.D..........................................."));
        assertTrue(scrambledCube.matches("(URF:U, ULB:U, UB:F, FU:D)LL......L.L..........................................."));
        assertFalse(scrambledCube.matches("(URF:R, ULB:U, UB:F, FU:D)"));
        assertFalse(scrambledCube.matches("(URF:R)(ULB:U)(UB:F)(FU:D).........."));
        assertFalse(Cube.fromScramble("F").matches("(.)45...LLLLLL"));
        assertTrue(Cube.fromScramble("U").matches("(.)45...LLLLLL"));
    }

    @Test
    public void exactMatchWithOr() {
        Cube scrambledCube = Cube.fromScramble("M'");
        assertTrue(scrambledCube.matches("(URF:U|R, ULB:U|F, UB:F|L, FU:D|R)"));
        assertTrue(scrambledCube.matches("(URF:U|., ULB:U|., UB:F|., FU:D|.)......................................................"));
        assertTrue(scrambledCube.matches("U(F|B)......(U|R).(D|F)..........................................."));
        assertTrue(scrambledCube.matches("(URF:U|L)(ULB:U, UB:F, FU:D)LL......L.L..........................................."));
        assertFalse(Cube.fromScramble("F").matches("(F|.)45...LLLLLL"));
    }

    @Test
    public void edgeOrientation() {
        Algorithm[] invariants = new Algorithm[]{Algorithm.fromScramble("F2"), Algorithm.fromScramble("B2"), Algorithm.fromScramble("R"), Algorithm.fromScramble("L"), Algorithm.fromScramble("U"), Algorithm.fromScramble("D")};
        Random random = new Random();
        Cube cube = new Cube();
        for (int i = 0; i < 100; i++) {
            cube.turn(invariants[random.nextInt(6)]);
            assertTrue(cube.matches(".o.o.o.o.(.)18.o.o.o.o.(.)18"));
        }
    }

    @Test
    public void cornerOrientation() {
        Algorithm[] invariants = new Algorithm[]{Algorithm.fromScramble("F2"), Algorithm.fromScramble("B2"), Algorithm.fromScramble("R2"), Algorithm.fromScramble("L2"), Algorithm.fromScramble("U"), Algorithm.fromScramble("D")};
        Random random = new Random();
        Cube cube = new Cube();
        for (int i = 0; i < 100; i++) {
            cube.turn(invariants[random.nextInt(6)]);
            assertTrue(cube.matches("o.o...o.o(.)18o.o...o.o(.)18"));
        }
    }

    @Test
    public void matchScramble() {
        for (String s : AlgorithmTests.bigSet) {
            Cube scrambledCube = Cube.fromScramble(s);
            assertTrue(scrambledCube.clone().normalizeRotation().matches(scrambledCube.clone().normalizeRotation().getFaceletDefinition()));
        }
    }

}
