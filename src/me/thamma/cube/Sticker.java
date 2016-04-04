package me.thamma.cube;

/**
 * Created by Dominic on 3/30/2016.
 */
public enum  Sticker {

    ULB(0, 0), UB(1, 0), UBR(2, 0), UL(3, 0), U(4, 0), UR(5, 0), UFL(6, 0), UF(7, 0), URF(8, 0),
    FLU(6, 1), FU(7, 1), FUR(8, 2), FL(11, 0), F(12, 0), FR(13, 0), FDL(17, 2), FD(18, 1), FRD(19, 1),
    RFU(8, 1), RU(5, 1), RUB(2, 2), RF(13, 1), R(14, 0), RB(15, 1), RDF(19, 2), RD(22, 1), RBD(25, 1),
    DLF(17, 0), DF(18, 1), DFR(19, 0), DL(20, 0), D(21, 0), DR(22, 0), DBL(23, 0), DB(24, 1), DRB(25, 0),
    BRU(2, 1), BU(1, 0), BUL(0, 2), BL(15, 0), B(16, 0), BR(9, 0), BDR(25, 2), BD(24, 0), BLD(23, 1),
    LBU(0, 1), LU(3, 1), LUF(6, 2), LB(9, 1), L(10, 0), LF(11, 1), LDB(23, 2), LD(20, 1), LFD(17, 1);

    public static String[] stickerStrings = {"ULB", "UB", "UBR", "UL", "U", "UR", "UFL", "UF", "URF",
            "FLU", "FU", "FUR", "FL", "F", "FR", "FDL", "FD", "FRD",
            "RFU", "RU", "RUB", "RF", "R", "RB", "RDF", "RD", "RBD",
            "DLF", "DF", "DFR", "DL", "D", "DR", "DBL", "DB", "DRB",
            "BRU", "BU", "BUL", "BL", "B", "BR", "BDR", "BD", "BRD",
            "LBU", "LU", "LUF", "LB", "L", "LF", "LDB", "LD", "LFD"};

    private int piece, offset;

    Sticker(int piece, int offset) {
        this.piece = piece;
        this.offset = offset;
    }

    public int getPiece() {
        return this.piece;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static boolean isValidSticker(String s) {
        try {
            Sticker.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public Sticker rotate() {
        String s = this.toString();
        return Sticker.valueOf(wrap(s));
    }

    private String wrap(String s) {
        return s.substring(1, s.length()) + s.charAt(0);
    }
}
