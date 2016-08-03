package me.thamma.cube.model;

import me.thamma.utils.StringUtils;

public enum Sticker {

    // The following permutation of this enum is crucial for the implementation to work
    // ULB, UB, UBR, UL, U, UR, UFL, UF, URF, BL, L, FL, F, FR, R, BR, B, DLF, DF, DFR, DL, D,, DBL, DB, DRB;

    ULB(Piece.ULB, 0), UB(Piece.UB, 0), UBR(Piece.UBR, 0), UL(Piece.UL, 0), U(Piece.U, 0),
    UR(Piece.UR, 0), UFL(Piece.UFL, 0), UF(Piece.UF, 0), URF(Piece.URF, 0),

    FLU(Piece.UFL, 1), FU(Piece.UF, 1), FUR(Piece.URF, 2), FL(Piece.FL, 0), F(Piece.F, 0),
    FR(Piece.FR, 0), FDL(Piece.DLF, 2), FD(Piece.DF, 1), FRD(Piece.DFR, 1),

    RFU(Piece.URF, 1), RU(Piece.UR, 1), RUB(Piece.UBR, 2), RF(Piece.FR, 1), R(Piece.R, 0),
    RB(Piece.BR, 1), RDF(Piece.DFR, 2), RD(Piece.DR, 1), RBD(Piece.DRB, 1),

    DLF(Piece.DLF, 0), DF(Piece.DF, 0), DFR(Piece.DFR, 0), DL(Piece.DL, 0), D(Piece.D, 0),
    DR(Piece.DR, 0), DBL(Piece.DBL, 0), DB(Piece.DB, 0), DRB(Piece.DRB, 0),

    BRU(Piece.UBR, 1), BU(Piece.UB, 1), BUL(Piece.ULB, 2), BL(Piece.BL, 0), B(Piece.B, 0),
    BR(Piece.BR, 0), BDR(Piece.DRB, 2), BD(Piece.DB, 1), BLD(Piece.DBL, 1),

    LBU(Piece.ULB, 1), LU(Piece.UL, 1), LUF(Piece.UFL, 2), LB(Piece.BL, 1), L(Piece.L, 0),
    LF(Piece.FL, 1), LDB(Piece.DBL, 2), LD(Piece.DL, 1), LFD(Piece.DLF, 1);


    private int rotation;
    private Piece piece;

    Sticker(Piece piece, int rotation) {
        this.piece = piece;
        this.rotation = rotation;
    }

    public int getOrder() {
        return this.name().length();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return this.name();
    }


    public Sticker rotate() {
        String s = this.toString();
        return Sticker.valueOf(StringUtils.wrap(s));
    }

}