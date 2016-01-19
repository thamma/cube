/**
 * Created by Dominic on 19.01.2016.
 */
public class Cube {

    /*
     * Encoding:
     *   [c3, c2, c1, rot] where c3,c2,c1 are the according colors, rot the rotation
     * Colors:
     *   U F R D B L  null
     *   1 2 3 4 5 6   0
     * (null color is provided if the piece consists of less than three facelets)
     * Rotation:
     *   TODO
     */
    static final int[] DEFAULT_CUBE = {5610, 0510, 3510, 0610, 0010, 0310, 6210, 0210, 2310};
    private int[] pieces;

    public Cube() {
        this.pieces = new int[26];
        this.pieces = DEFAULT_CUBE;
    }

}
