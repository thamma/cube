import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Cycle;
import me.thamma.cube.model.Cycles;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CycleTests {

    @Test
    public void cycleParityTest() {
        for (String algorithm : AlgorithmTests.bigSet) {
            Cube cube = Cube.fromScramble(algorithm);
            cube.normalizeRotation();
            Cycles cycles = new Cycles(cube);
            System.out.println(algorithm);
            System.out.println(cycles);
            int count = 0;
            for (Cycle cycle : cycles)
                if (cycle.size() % 2 == 0)
                    count++;
            assertTrue(count % 2 == 0);
        }
    }

    @Test
    public void parityTest() {
        for (String algorithm : AlgorithmTests.bigSet) {
            assertFalse(Cube.fromScramble(algorithm).hasParity());
        }
    }
}
