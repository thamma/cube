package me.thamma.cube;

import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;

import java.util.Arrays;

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
     * implemented, hard recognition.
     * edge rotation is ZZ edge orientation
     */

    public static String[] sideStrings = {null, "U", "F", "R", "D", "B", "L"};

    public static final int ULB = 0;
    public static final int UB = 1;
    public static final int UBR = 2;
    public static final int UL = 3;
    public static final int U = 4;
    public static final int UR = 5;
    public static final int UFL = 6;
    public static final int UF = 7;
    public static final int URF = 8;

    public static final int BL = 9;
    public static final int L = 10;
    public static final int FL = 11;

    public static final int F = 12;

    public static final int FR = 13;
    public static final int R = 14;
    public static final int BR = 15;

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

    public static final int[] DEFAULT_CUBE = {5610, 510, 3510, 610, 10, 310, 6210, 210, 2310, 650, 60, 620, 20, 320, 30, 350, 50, 2640, 240, 3240, 640, 40, 340, 6540, 540, 5340};

    public int[] pieces;

    public Cube() {
        this(DEFAULT_CUBE.clone());
    }

    public Cube(int[] pieces) {
        this.pieces = pieces;
    }

    public void solve() {
        this.pieces = DEFAULT_CUBE.clone();
    }

    public boolean isSolved() {
        return this.equals(new Cube());
    }

    public void print() {
        System.out.println(Arrays.toString(this.pieces));
    }

    public void turn(String s) throws IllegalCharacterException, UnexpectedTokenException, UnexpectedEndOfLineException {
        this.turn(new Algorithm(s));
    }

    public void turn(Turn t) {
        if (t.hasChildren()) {
            for (Turn child : t.getChildren())
                turn(child);
        } else {
            this.cyclePieces(t.getOffset(), t.getTarget(), t.getRotation());
        }
    }

    public void turn(Algorithm a) {
        a.forEach(this::turn);
    }

    public int getColor(Sticker s) {
        int[] piece = this.getPiece(s.getPiece());
        return piece[2 - (s.getOffset() + piece[3]) % order(piece)];
//        if (order(piece) != 2)
//            return piece[2 - (s.getOffset() + piece[3]) % order(piece)];
//        if (s.name().contains("U")||s.name().contains("D")) {
//            return piece[2 - (s.getOffset() + piece[3]) % order(piece)];
//        } else {
//            return 0;
//        }
    }

    public Sticker getCurrentStickerAt(Sticker local) {
//        System.out.println(Arrays.toString(this.getPiece(local.getPiece())));
        String stickerString = "";
//        System.out.println("argument:" + local);
//        System.out.println(local);
//        System.out.println(getColor(local));
//        System.out.println(local.rotate());
//        System.out.println(getColor(local.rotate()));
        stickerString += sideStrings[getColor(local)];
        if (local.toString().length() > 1)
            stickerString += sideStrings[getColor(local.rotate())];
        if (local.toString().length() > 2)
            stickerString += sideStrings[getColor(local.rotate().rotate())];
//        System.out.println("stickerstring: "+ stickerString);
        return Sticker.valueOf(stickerString);
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

    public static int rotate(int movedPiece, int rotation) {
        int[] arr = pieceToArray(movedPiece);
        int order = order(arr);
        arr[3] += rotation + order;
        arr[3] %= order;
        return (((arr[0] * 10) + arr[1]) * 10 + arr[2]) * 10 + arr[3];
    }

    public int[] getPiece(int piece) {
        return pieceToArray(this.pieces[piece]);
    }

    private static int[] pieceToArray(int piece) {
        assert piece != 0;
        assert ("" + piece).length() < 5;
        String s = new String(new char[4 - ("" + piece).length()]).replace("\0", "0") + piece;
        int[] arr = s.chars().map(a -> a - '0').toArray();
        assert arr.length == 4;
        return arr;
    }

    private static int order(int[] pieceArray) {
        assert pieceArray.length == 4;
        return (pieceArray[0] == 0 ? 0 : 1) + (pieceArray[2] == 0 ? 0 : 1) + (pieceArray[1] == 0 ? 0 : 1);
    }

    public Cube clone() {
        int[] a = new int[26];
        System.arraycopy(this.pieces, 0, a, 0, pieces.length);
        return new Cube(a);

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cube))
            return false;
        Cube ref = (Cube) o;
        for (int i = 0; i < pieces.length; i++)
            if (ref.pieces[i] != this.pieces[i])
                return false;
        return true;
    }
}
