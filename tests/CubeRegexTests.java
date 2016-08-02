import me.thamma.cube.model.Cube;
import org.junit.Test;

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
    public void matchOrientation() {
        assertTrue(Cube.fromScramble("F2 B2 L2 R2").matches("(o)54"));
    }

    @Test
    public void matchScramble() {
        for (String s: AlgorithmTests.bigSet) {
            Cube scrambledCube = Cube.fromScramble(s);
            assertTrue(scrambledCube.clone().normalizeRotation().matches(scrambledCube.clone().normalizeRotation().getFaceletDefinition()));
        }
    }

}
