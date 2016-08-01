import me.thamma.cube.model.Cube;
import me.thamma.cube.model.regex.CubeRegex;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CubeRegexTests {

    @Test
    public void trivialRegex() {
        assertTrue(CubeRegex.compile("(.)54").matches(new Cube()));
        assertTrue(CubeRegex.compile("......................................................").matches(new Cube()));
        assertTrue(CubeRegex.compile("").matches(new Cube()));
        assertTrue(CubeRegex.compile("(.)54").matches(Cube.fromScramble("[R,UFD]")));
        assertTrue(CubeRegex.compile("......................................................").matches(Cube.fromScramble("[R,UFD]")));
        assertTrue(CubeRegex.compile("").matches(Cube.fromScramble("[R,UFD]")));
    }

    @Test
    public void oneSide() {
        Cube oneSideSolvedCube = Cube.fromScramble("r F r' d L D2 L'");
        assertTrue(CubeRegex.compile("(U)9(.)45").matches(oneSideSolvedCube));
        assertTrue(CubeRegex.compile("(U)9").matches(oneSideSolvedCube));
        assertTrue(CubeRegex.compile("UUUUUUUUU.............................................").matches(oneSideSolvedCube));
        assertTrue(CubeRegex.compile("UUUUUUUUU").matches(oneSideSolvedCube));
    }

    @Test
    public void exactMatch() {
        Cube scrambledCube = Cube.fromScramble("M'");
        // exact matches
        assertTrue(CubeRegex.compile("(URF:U, ULB:U, UB:F, FU:D)").matches(scrambledCube));
        // exact match ignores wildcard anyway
        assertTrue(CubeRegex.compile("(URF:U, ULB:U, UB:F, FU:D)......................................................").matches(scrambledCube));
        // match pattern
        assertTrue(CubeRegex.compile("UF......U.D...........................................").matches(scrambledCube));
        // exact match should override pattern:
        assertTrue(CubeRegex.compile("(URF:U, ULB:U, UB:F, FU:D)LL......L.L...........................................").matches(scrambledCube));
    }


}
