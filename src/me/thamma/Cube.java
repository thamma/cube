package me.thamma;

import java.util.Arrays;

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
     * leading zeros are omitted to prevent confusion with octal numbers
     * Rotation:
     *   TODO
     */

    public static final int ULB = 0;
    public static final int UB = 1;
    public static final int UBR = 2;
    public static final int UL = 3;
    public static final int U = 4;
    public static final int UR = 5;
    public static final int UFL = 6;
    public static final int UF = 7;
    public static final int URF = 8;
    public static final int LB = 9;
    public static final int L = 10;
    public static final int LF = 11;
    public static final int F = 12;
    public static final int RF = 13;
    public static final int R = 14;
    public static final int RB = 15;
    public static final int B = 16;
    public static final int DLF = 17;
    public static final int DF = 18;
    public static final int DFR = 19;
    public static final int DL = 20;
    public static final int D = 21;
    public static final int DR = 22;
    public static final int DBL = 23;
    public static final int DB = 24;
    public static final int DRB = 25;

    public static final int[] DEFAULT_CUBE = {5610, 510, 3510, 610, 10, 310, 6210, 210, 2310, 560, 60, 260, 20, 230, 30, 530, 50, 2640, 240, 3240, 640, 40, 340, 6540, 540, 3540};

    private int[] pieces;

    public Cube() {
        this.pieces = DEFAULT_CUBE.clone();
    }

    public void test() {
        cyclePieces(2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0,0, 0, 0, 0});
        System.out.println("  " + Arrays.toString(DEFAULT_CUBE));
        System.out.println("->" + Arrays.toString(this.pieces));
    }

    public void turn(Turn t) {
        this.cyclePieces(t.getOffset(),t.getTarget(),t.getRotation());
    }

    private void cyclePieces(int offset, int[] target, int[] rotation) {
        offset = (offset + target.length) % target.length;
        assert target.length == rotation.length;
        int[] piecesClone = this.pieces.clone();
        for (int i = 0; i < target.length; i++) {
            int a = target[(i + offset) % target.length];
            piecesClone[a] = rotate(pieces[target[i]], rotation[i]);
        }
        this.pieces = piecesClone;
    }

    private int rotate(int movedPiece, int rotation) {
        assert movedPiece != 0;
        assert ("" + movedPiece).length() < 5;
        String s = new String(new char[4 - ("" + movedPiece).length()]).replace("\0", "0") + movedPiece;
        int[] arr = s.chars().map(a -> a - '0').toArray();
        assert arr.length == 4;
        int order = (arr[0] == 0 ? 0 : 1) + (arr[2] == 0 ? 0 : 1) + (arr[1] == 0 ? 0 : 1);
        arr[3] += rotation;
        arr[3] %= order;
        return (((arr[0] * 10) + arr[1]) * 10 + arr[2]) * 10 + arr[3];
    }

}
