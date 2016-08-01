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
    };
    int[][] lengths = new int[][]{
            {4, 4, 4},
            {3, 2, 2},
            {4, 3, 2},
            {4, 3, 3},
            {9, 9, 5}
    };

    @Test
    public void correctQTM() {
        for (int i = 0; i < algorithms.length; i++) {
            assertTrue(algorithms[i].length(Metrics.QTM) == lengths[i][0]);
        }
    }

    @Test
    public void correctHTM() {
        for (int i = 0; i < algorithms.length; i++) {
            assertTrue(algorithms[i].length(Metrics.HTM) == lengths[i][1]);
        }
    }

    @Test
    public void correctSTM() {
        for (int i = 0; i < algorithms.length; i++) {
            assertTrue(algorithms[i].length(Metrics.STM) == lengths[i][2]);
        }
    }
}
