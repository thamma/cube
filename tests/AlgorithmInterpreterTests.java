import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Turn;
import me.thamma.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AlgorithmInterpreterTests {



    @Test
    public void finiteOrder() {
        for (Algorithm alg : AlgorithmCollection.rawAlgorithms) {
            alg.getOrder();
        }
        assertTrue(true);
    }

    @Test
    public void correctSyntax() {
        for (int i = 0; i < AlgorithmCollection.rawAlgorithms.length; i++)
            assertTrue(AlgorithmCollection.rawAlgorithms[i].equals(new Algorithm(AlgorithmCollection.parsedAlgorithms[i])));
    }

    @Test
    public void correctSemantics() {
        for (int i = 0; i < AlgorithmCollection.rawAlgorithms.length; i++)
            assertTrue(new Cube(AlgorithmCollection.rawAlgorithms[i]).equals(new Cube(AlgorithmCollection.parsedAlgorithms[i])));
    }

    @Test
    public void selfInverseInverse() {
        for (Algorithm alg : AlgorithmCollection.rawAlgorithms) {
            Cube cube = new Cube();
            cube.turn(alg);
            cube.turn(alg.inverse());
            assertTrue(cube.isSolved());
        }
    }
}
