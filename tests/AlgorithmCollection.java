import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Turn;

public class AlgorithmCollection {

    public static Algorithm[] rawAlgorithms = new Algorithm[]{
            new Algorithm(Turn.DOWN),
            new Algorithm(Turn.DOWN, Turn.RIGHT),
            new Algorithm(Turn.DOWN, Turn.X, Turn.RIGHT),
            new Algorithm(Turn.DOWN, Turn.X, Turn.EQUATORIAL),
            new Algorithm(Turn.UP, Turn.EQUATORIAL, Turn.FRONT, Turn.UP),
            new Algorithm(Turn.RIGHT_WIDE, Turn.MIDDLE, Turn.UP_WIDE_PRIME, Turn.STANDING),
            new Algorithm(Turn.UP, Turn.RIGHT, Turn.UP_PRIME, Turn.RIGHT_PRIME),
            new Algorithm(Turn.UP, Turn.RIGHT, Turn.UP_PRIME)
    };

    public static String[] parsedAlgorithms =  new String[]{
            "D",
            "D R",
            "D x R",
            "D x E",
            "U E F U",
            "r M u' S",
            "[U, R]",
            "[U: R]"
    };

    public static String[] randomAlgorithms = {
            " [[RBU: RU' RU RU RU' R'U' R2], x2 y'] [B2: R U2 R' U' R U' R' L' U2 L U L' U L]",
            "M2 U M U2 M' U M2",
            "R U R' U' R' F R2 U' R' U' R U R' F'",
            "y' L' R' U2 L R (y) L U' R U2 L' U R' U2",
            "x' R U' R' D R U R' Uw2 R' U R D R' U' R"
    };

}
