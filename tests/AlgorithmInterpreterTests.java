import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AlgorithmInterpreterTests {


    @Test
    public void finiteOrder() {
        for (Algorithm alg : TestAlgorithmCollection.rawAlgorithms) {
            alg.getOrder();
        }
        assertTrue(true);
    }

    @Test
    public void correctSyntax() {
        for (int i = 0; i < TestAlgorithmCollection.rawAlgorithms.length; i++)
            assertTrue(TestAlgorithmCollection.rawAlgorithms[i].equals(new Algorithm(TestAlgorithmCollection.parsedAlgorithms[i])));
    }

    @Test
    public void correctSemantics() {
        for (int i = 0; i < TestAlgorithmCollection.rawAlgorithms.length; i++)
            assertTrue(new Cube(TestAlgorithmCollection.rawAlgorithms[i]).equals(Cube.fromScramble(TestAlgorithmCollection.parsedAlgorithms[i])));
    }

    @Test
    public void selfInverseInverse() {
        for (Algorithm alg : TestAlgorithmCollection.rawAlgorithms) {
            System.out.println("testing: " + alg);
            Cube cube = new Cube();
            cube.turn(alg).turn(alg.inverse());
            assertTrue(cube.isSolved());
        }
    }
}
