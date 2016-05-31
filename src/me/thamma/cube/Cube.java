package me.thamma.cube;

import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;

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

    public String getFaceletDefinition() {
        String out = "";
        out += (this.getCurrentStickerAt(Sticker.ULB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.UB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.UBR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.UL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.U)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.UR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.UFL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.UF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.URF)+"").substring(0,1);

        out += (this.getCurrentStickerAt(Sticker.RFU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RUB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.R)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RDF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RD)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.RBD)+"").substring(0,1);

        out += (this.getCurrentStickerAt(Sticker.FLU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FUR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.F)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FDL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FD)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.FRD)+"").substring(0,1);

        out += (this.getCurrentStickerAt(Sticker.DLF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DFR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.D)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DBL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.DRB)+"").substring(0,1);

        out += (this.getCurrentStickerAt(Sticker.LBU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LUF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.L)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LF)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LDB)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LD)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.LFD)+"").substring(0,1);
        // U1, U2, U3, U4, U5, U6, U7, U8, U9, R1, R2, R3, R4, R5, R6,
        //
        out += (this.getCurrentStickerAt(Sticker.BRU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BU)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BUL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.B)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BL)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BDR)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BD)+"").substring(0,1);
        out += (this.getCurrentStickerAt(Sticker.BLD)+"").substring(0,1);
        // F1, F2, F3, F4, F5, F6, F7, F8, F9, D1, D2, D3, D4, D5, D6, D7, D8, D9,
        // L1, L2, L3, L4, L5, L6, L7, L8, L9, B1, B2, B3, B4, B5, B6, B7, B8, B9
        return out;
    }

    public Cube(String scramble) {
        try {
            this.turn(scramble);
        } catch (IllegalCharacterException e) {
            e.printStackTrace();
        } catch (UnexpectedTokenException e) {
            e.printStackTrace();
        } catch (UnexpectedEndOfLineException e) {
            e.printStackTrace();
        }
    }

    public void solve() {
        this.pieces = DEFAULT_CUBE.clone();
    }

    public boolean isSolved() {
        return this.equals(new Cube());
    }

    public void turn(String s) throws
            IllegalCharacterException, UnexpectedTokenException, UnexpectedEndOfLineException {
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
        return piece[2 - (s.getOffset() + piece[3]) % order(piece)];
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

    private static int rotate(int movedPiece, int rotation) {
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

    public Cube normalize() {
        Cube c = this.clone();
        int i = 0;
        while (c.getCurrentStickerAt(Sticker.U) != Sticker.U) {
            c.turn(i == 0 ? Turn.X : Turn.Z);
            i ^= 1;
        }
        while (c.getCurrentStickerAt(Sticker.F) != Sticker.F) {
            c.turn(Turn.Y);
        }
        if (c != null) {

        }
        return c;
    }

    private final Sticker[][] faceStickers = {{Sticker.ULB, Sticker.UB, Sticker.UBR, Sticker.UL, Sticker.UR, Sticker.UFL, Sticker.UF, Sticker.URF},
            {Sticker.FLU, Sticker.FU, Sticker.FUR, Sticker.FL, Sticker.FR, Sticker.FDL, Sticker.FD, Sticker.FRD},
            {Sticker.RFU, Sticker.RU, Sticker.RUB, Sticker.RF, Sticker.RB, Sticker.RDF, Sticker.RD, Sticker.RBD},
            {Sticker.DLF, Sticker.DF, Sticker.DFR, Sticker.DL, Sticker.DR, Sticker.DBL, Sticker.DB, Sticker.DRB},
            {Sticker.BRU, Sticker.BU, Sticker.BUL, Sticker.BR, Sticker.BL, Sticker.BDR, Sticker.BD, Sticker.BLD},
            {Sticker.LBU, Sticker.LU, Sticker.LUF, Sticker.LB, Sticker.LF, Sticker.LDB, Sticker.LD, Sticker.LFD}
    };

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cube))
            return false;
        Cube local = this.normalize();
        Cube ref = ((Cube) o).normalize();
        for (int i = 0; i < faceStickers.length; i++)
            for (int j = 0; j < faceStickers[i].length; j++)
                if (local.getCurrentStickerAt(faceStickers[i][j]) != ref.getCurrentStickerAt(faceStickers[i][j]))
                    return false;
        return true;
    }
}
