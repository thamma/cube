import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Cycle;
import me.thamma.cube.model.Cycles;
import me.thamma.cube.model.Turn;
import org.junit.Test;

import java.util.Random;

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

    @Test
    public void randomCubeWalker() {
        int count = 1 << 12;
        Random r = new Random();
        Cube cube = new Cube();
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            cube.turn(Turn.values()[r.nextInt(Turn.values().length)]);
            assertTrue(!cube.hasParity());
        }
        //System.out.printf("took %d ms (%.3f ms per turn; %d tps)", System.currentTimeMillis() - start, (double) (System.currentTimeMillis() - start) / count, count * 1000 / (System.currentTimeMillis() - start));
    }
}
