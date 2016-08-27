import me.thamma.cube.model.Turn;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TurnTests {

    @Test
    public void turnSelfInverse() {
        for (Turn t : Turn.values())
            assertTrue(t.inverse().inverse() == t);
    }

    @Test
    public void turnWides() {
        for (Turn t : Turn.values())
            assertTrue(t.name().toLowerCase().contains("wide") == t.isWideTurn());
    }

    @Test
    public void turnSlice() {
        for (Turn t : Turn.values())
            assertTrue((t.name().startsWith("M") || t.name().startsWith("E") || t.name().startsWith("S")) == t.isSliceTurn());
    }

    @Test
    public void turnRotations() {
        for (Turn t : Turn.values())
            assertTrue((t.name().contains("X") || t.name().contains("Y") || t.name().contains("Z")) == t.isCubeRotation());
    }

    @Test
    public void turnTranslationsXYInvariant() {
        Turn t1 = Turn.X, t2 = Turn.Y;
        for (Turn t : Turn.values())
            assertTrue(String.format("Invariant broke for Turn %s\n", t), t.translateTurn(t1).translateTurn(t2).translateTurn(t1).translateTurn(t2).translateTurn(t1).translateTurn(t2) == t);
    }

    @Test
    public void turnTranslationsXZInvariant() {
        Turn t1 = Turn.X, t2 = Turn.Z;
        for (Turn t : Turn.values())
            assertTrue(String.format("Invariant broke for Turn %s\n", t),t.translateTurn(t1).translateTurn(t2).translateTurn(t1).translateTurn(t2).translateTurn(t1).translateTurn(t2) == t);
    }

    @Test
    public void turnTranslationsYZInvariant() {
        Turn t1 = Turn.Y, t2 = Turn.Z;
        for (Turn t : Turn.values())
            assertTrue(String.format("Invariant broke for Turn %s\n", t),t.translateTurn(t1).translateTurn(t2).translateTurn(t1).translateTurn(t2).translateTurn(t1).translateTurn(t2) == t);
    }

}
