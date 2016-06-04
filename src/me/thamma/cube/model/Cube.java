package me.thamma.cube.model;

import me.thamma.utils.CubeUtils;

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

    public static final int[] DEFAULT_CUBE = {5610, 510, 3510, 610, 10, 310, 6210, 210, 2310, 650, 60, 620, 20, 320, 30, 350, 50, 2640, 240, 3240, 640, 40, 340, 6540, 540, 5340};

    public int[] pieces;

    public Cube() {
        this(DEFAULT_CUBE.clone());
    }

    public Cube(int[] pieces) {
        this.pieces = pieces;
    }

    public String getFaceletDefinition() {
        String out = "";
        for (Sticker sticker: UtilSets.faceletDefinition)
            out += this.getCurrentStickerAt(sticker).toString().substring(0, 1);
        return out;
    }

    public Cube (Algorithm algorithm){
        this();
        this.turn(algorithm);
    }

    public Cube(String scramble) {
        this();
        this.turn(scramble);
    }


    public boolean isSolved() {
        return this.equals(new Cube());
    }

    public void turn(String s) {
        this.turn(new Algorithm(s));
    }

    public void turn(Turn t) {
        int a = 5;
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
        return piece[2 - (s.getRotation() + piece[3]) % order(piece)];
    }

    public Sticker getCurrentStickerAt(Sticker local) {
        String stickerString = "";
        stickerString += sideStrings[getColor(local)];
        if (local.toString().length() > 1)
            stickerString += sideStrings[getColor(local.rotate())];
        if (local.toString().length() > 2)
            stickerString += sideStrings[getColor(local.rotate().rotate())];
        return Sticker.valueOf(stickerString);
    }

    private void cyclePieces(int offset, Sticker[] target, int[] rotation) {
            offset = (offset + target.length) % target.length;
        int[] piecesClone = this.pieces.clone();
        for (int i = 0; i < target.length; i++) {
            Sticker a = target[(i + offset) % target.length];
            piecesClone[a.getPiece().ordinal()] = rotate(pieces[target[i].getPiece().ordinal()], rotation[i]);
        }
        this.pieces = piecesClone;
    }

    private static int rotate(int movedPiece, int rotation) {
        int[] arr = pieceToArray(movedPiece);
        int order = order(arr);
        arr[3] += rotation + order;
        arr[3] %= order;
        return (((arr[0] * 10) + arr[1]) * 10 + arr[2]) * 10 + arr[3];
    }

    public int[] getPiece(Piece piece) {
        return pieceToArray(this.pieces[piece.ordinal()]);
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

    public Cube normalize() {
        Cube c;
        for (Algorithm alg: UtilSets.cubeOrientations) {
            c = this.clone();
            c.turn(alg);
            if (c.getCurrentStickerAt(Sticker.U)==Sticker.U && c.getCurrentStickerAt(Sticker.F) == Sticker.F)
                return c;
        }
        return null;
    }

//    public boolean isCongruent(Cube cube) {
//        Algorithm thisSolve = CubeUtils.anySolve(this);
//        Algorithm otherSolve = CubeUtils.anySolve(cube);
//        return thisSolve.isCongruent(otherSolve);
//    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cube))
            return false;
        Cube local = this.normalize();
        Cube ref = ((Cube) o).normalize();
        for (int i = 0; i < UtilSets.faceStickers.length; i++)
            for (int j = 0; j < UtilSets.faceStickers[i].length; j++){
                if (local.getCurrentStickerAt(UtilSets.faceStickers[i][j]) != ref.getCurrentStickerAt(UtilSets.faceStickers[i][j]))
                    return false;}
        return true;
    }
}
