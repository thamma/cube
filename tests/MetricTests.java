import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Metrics;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MetricTests {

    Algorithm[] algorithms = new Algorithm[]{
            new Algorithm("[R,U]"),
            new Algorithm("R2 U"),
            new Algorithm("M' U2"),
            new Algorithm("x2 F R2 u")
    };
    int[][] lengths = new int[][]{
            {4, 4, 4},
            {3, 2, 2},
            {4, 3, 2},
            {4, 3, 2}
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
            assertTrue(algorithms[i].length(Metrics.QTM) == lengths[i][2]);
        }
    }
}
