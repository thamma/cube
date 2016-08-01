import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Metrics;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MetricTests {

    Algorithm[] algorithms = new Algorithm[]{
            Algorithm.fromScramble("[R,U]"),
            Algorithm.fromScramble("R2 U"),
            Algorithm.fromScramble("M' U2"),
            Algorithm.fromScramble("x2 F R2 u"),
            Algorithm.fromScramble("[M,E] x R"),
            Algorithm.fromScramble("R L U2 F U' D F2 R2 B2 L U2 F' B' U R2 D F2 U R2 U "),
            Algorithm.fromScramble("((M'U)4xy')3"),
    };
    int[][] lengths = new int[][]{
            {4, 4, 4},
            {3, 2, 2},
            {4, 3, 2},
            {4, 3, 3},
            {9, 9, 5},
            {28, 20, 20},
            {36, 36, 24}
    };

    @Test
    public void testQTM() {
        for (int i = 0; i < algorithms.length; i++) {
            assertTrue(algorithms[i].length(Metrics.QTM) == lengths[i][0]);
        }
    }

    @Test
    public void testHTM() {
        for (int i = 0; i < algorithms.length; i++) {
            assertTrue(algorithms[i].length(Metrics.HTM) == lengths[i][1]);
        }
    }

    @Test
    public void testSTM() {
        for (int i = 0; i < algorithms.length; i++) {
            assertTrue(algorithms[i].length(Metrics.STM) == lengths[i][2]);
        }
    }
}
