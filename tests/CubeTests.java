import me.thamma.cube.Cube;
import me.thamma.cube.Sticker;
import me.thamma.cube.Turn;
import org.junit.Before;
import org.junit.Test;

import static me.thamma.cube.Piece.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class CubeTests {

    private Cube cube;

    @Before
    public void setupSolvedCube() {
        cube = new Cube();
    }

    @Test
    public void testStickerIdentityCube() {
        for (Sticker sticker : Sticker.values()) {
            System.out.println(sticker);
            assertTrue(sticker.toString(), cube.getCurrentStickerAt(sticker).equals(sticker));
        }
    }

    @Test
    public void testUpMove() {
        cube.turn(Turn.UP);
        assertTrue("ULB", Arrays.equals(cube.getPiece(ULB), (new int[]{6, 2, 1, 0})));
        assertTrue("UB", Arrays.equals(cube.getPiece(UB), (new int[]{0, 6, 1, 0})));
        assertTrue("UBR", Arrays.equals(cube.getPiece(UBR), (new int[]{5, 6, 1, 0})));
        assertTrue("UL", Arrays.equals(cube.getPiece(UL), (new int[]{0, 2, 1, 0})));
        assertTrue("U", Arrays.equals(cube.getPiece(U), (new int[]{0, 0, 1, 0})));
        assertTrue("UR", Arrays.equals(cube.getPiece(UR), (new int[]{0, 5, 1, 0})));
        assertTrue("UFL", Arrays.equals(cube.getPiece(UFL), (new int[]{2, 3, 1, 0})));
        assertTrue("UF", Arrays.equals(cube.getPiece(UF), (new int[]{0, 3, 1, 0})));
        assertTrue("URF", Arrays.equals(cube.getPiece(URF), (new int[]{3, 5, 1, 0})));
    }

    @Test
    public void testRightFront() {
        cube.turn(Turn.FRONT);
        assertTrue("UFL", Arrays.equals(cube.getPiece(UFL), (new int[]{2, 6, 4, 1})));
        assertTrue("UF", Arrays.equals(cube.getPiece(UF), (new int[]{0, 6, 2, 1})));
        assertTrue("URF", Arrays.equals(cube.getPiece(URF), (new int[]{6, 2, 1, 2})));
        assertTrue("LF", Arrays.equals(cube.getPiece(FL), (new int[]{0, 2, 4, 1})));
        assertTrue("F", Arrays.equals(cube.getPiece(F), (new int[]{0, 0, 2, 0})));
        assertTrue("RF", Arrays.equals(cube.getPiece(FR), (new int[]{0, 2, 1, 1})));
        assertTrue("DLF", Arrays.equals(cube.getPiece(DLF), (new int[]{3, 2, 4, 2})));
        assertTrue("DF", Arrays.equals(cube.getPiece(DF), (new int[]{0, 3, 2, 1})));
        assertTrue("DFR", Arrays.equals(cube.getPiece(DFR), (new int[]{2, 3, 1, 1})));
    }

    @Test
    public void testRightMove() {
        cube.turn(Turn.RIGHT);
        assertTrue("URF", Arrays.equals(cube.getPiece(URF), (new int[]{3, 2, 4, 1})));
        assertTrue("UR", Arrays.equals(cube.getPiece(UR), (new int[]{0, 3, 2, 0})));
        assertTrue("UBR", Arrays.equals(cube.getPiece(UBR), (new int[]{2, 3, 1, 2})));
        assertTrue("RF", Arrays.equals(cube.getPiece(FR), (new int[]{0, 3, 4, 0})));
        assertTrue("R", Arrays.equals(cube.getPiece(R), (new int[]{0, 0, 3, 0})));
        assertTrue("RB", Arrays.equals(cube.getPiece(BR), (new int[]{0, 3, 1, 0})));
        assertTrue("DFR", Arrays.equals(cube.getPiece(DFR), (new int[]{5, 3, 4, 2})));
        assertTrue("DR", Arrays.equals(cube.getPiece(DR), (new int[]{0, 3, 5, 0})));
        assertTrue("DRB", Arrays.equals(cube.getPiece(DRB), (new int[]{3, 5, 1, 1})));
    }

    @Test
    public void testRightDown() {
        cube.turn(Turn.DOWN);
        assertTrue("DLF", Arrays.equals(cube.getPiece(DLF), (new int[]{6, 5, 4, 0})));
        assertTrue("DF", Arrays.equals(cube.getPiece(DF), (new int[]{0, 6, 4, 0})));
        assertTrue("DFR", Arrays.equals(cube.getPiece(DFR), (new int[]{2, 6, 4, 0})));
        assertTrue("DL", Arrays.equals(cube.getPiece(DL), (new int[]{0, 5, 4, 0})));
        assertTrue("D", Arrays.equals(cube.getPiece(D), (new int[]{0, 0, 4, 0})));
        assertTrue("DR", Arrays.equals(cube.getPiece(DR), (new int[]{0, 2, 4, 0})));
        assertTrue("DBL", Arrays.equals(cube.getPiece(DBL), (new int[]{5, 3, 4, 0})));
        assertTrue("DB", Arrays.equals(cube.getPiece(DB), (new int[]{0, 3, 4, 0})));
        assertTrue("DRB", Arrays.equals(cube.getPiece(DRB), (new int[]{3, 2, 4, 0})));
    }

    @Test
    public void testRightBack() {
        cube.turn(Turn.BACK);
        assertTrue("UBR", Arrays.equals(cube.getPiece(UBR), (new int[]{5, 3, 4, 1})));
        assertTrue("UB", Arrays.equals(cube.getPiece(UB), (new int[]{0, 3, 5, 1})));
        assertTrue("ULB", Arrays.equals(cube.getPiece(ULB), (new int[]{3, 5, 1, 2})));
        assertTrue("RB", Arrays.equals(cube.getPiece(BR), (new int[]{0, 5, 4, 1})));
        assertTrue("B", Arrays.equals(cube.getPiece(B), (new int[]{0, 0, 5, 0})));
        assertTrue("LB", Arrays.equals(cube.getPiece(BL), (new int[]{0, 5, 1, 1})));
        assertTrue("DRB", Arrays.equals(cube.getPiece(DRB), (new int[]{6, 5, 4, 2})));
        assertTrue("DB", Arrays.equals(cube.getPiece(DB), (new int[]{0, 6, 5, 1})));
        assertTrue("DBL", Arrays.equals(cube.getPiece(DBL), (new int[]{5, 6, 1, 1})));
    }

    @Test
    public void testRightLeft() {
        cube.turn(Turn.LEFT);
        assertTrue("ULB", Arrays.equals(cube.getPiece(ULB), (new int[]{6, 5, 4, 1})));
        assertTrue("UL", Arrays.equals(cube.getPiece(UL), (new int[]{0, 6, 5, 0})));
        assertTrue("UFL", Arrays.equals(cube.getPiece(UFL), (new int[]{5, 6, 1, 2})));
        assertTrue("LB", Arrays.equals(cube.getPiece(BL), (new int[]{0, 6, 4, 0})));
        assertTrue("L", Arrays.equals(cube.getPiece(L), (new int[]{0, 0, 6, 0})));
        assertTrue("LF", Arrays.equals(cube.getPiece(FL), (new int[]{0, 6, 1, 0})));
        assertTrue("DBL", Arrays.equals(cube.getPiece(DBL), (new int[]{2, 6, 4, 2})));
        assertTrue("DL", Arrays.equals(cube.getPiece(DL), (new int[]{0, 6, 2, 0})));
        assertTrue("DLF", Arrays.equals(cube.getPiece(DLF), (new int[]{6, 2, 1, 1})));
    }
}
