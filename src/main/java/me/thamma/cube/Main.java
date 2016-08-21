package me.thamma.cube;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Metrics;
import me.thamma.utils.CubeUtils;

public class Main {

    public static void main(String... args) throws Exception {
        Algorithm raw = Algorithm.fromScramble("[[F':D',RU2R'] : x2y']");
        System.out.println(raw.rawString());
        String spacer = new String(new char[raw.toString().length() + 11]).replace("\0", "-");
        Algorithm algorithm = raw.clone().cancelOut();
        new Cube(algorithm).printGrid("test");
        System.out.println(String.format("Raw input: %s" +
                        "\n%s" +
                        "\nCancelled out version: %s" +
                        "\nRotation purged version: %s" +
                        "\nCycles: %s" +
                        "\nOrder: %d" +
                        "\nLength (Q/H/S): %d/%d/%d" +
                        "\n2-Phase _suboptimal_ version: %s",
                raw,
                spacer,
                algorithm,
                algorithm.clone().purgeRotations(),
                algorithm.getCycles(),
                algorithm.getOrder(),
                algorithm.length(Metrics.QTM), algorithm.length(Metrics.HTM), algorithm.length(Metrics.STM),
                CubeUtils.anySolve(new Cube().turn(algorithm)).cancelOut().inverse()
                )
                );

    }

}
