import me.thamma.cube.model.Cube;
import me.thamma.cube.model.regex.CubeRegex;
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
        assertFalse(Cube.fromScramble("F").matches("(.)45...LLLLLL"));
    }

    @Test
    public void matchScramble() {
        Cube scrambledCube = Cube.fromScramble("L2 U R2 B' L U' B2 R2 F D' R' L' D L2 F2 U F2 D2 F2 R2 ");
        assertTrue(scrambledCube.matches("BBLLUFBDBLBRUFDDURDDFRRFDFBLFFUDRUBUULLLBLRRRUDDULBFRF"));
    }

}
