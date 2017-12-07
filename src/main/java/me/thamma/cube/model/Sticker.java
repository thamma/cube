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
    //private Sticker[] adjacents;

    Sticker(Piece piece, int rotation) {
        this.piece = piece;
        this.rotation = rotation;
       // this.adjacents = adjacentLookup();
    }

    public Sticker[] adjacentLookup(Sticker sticker) {
        switch (sticker) {
            case ULB:
                return new Sticker[]{Sticker.UB, Sticker.UL};
            case UB:
                return new Sticker[]{Sticker.ULB, Sticker.U, Sticker.UBR};
            case UBR:
                return new Sticker[]{Sticker.UB, Sticker.UR};
            case UL:
                return new Sticker[]{Sticker.ULB, Sticker.U, Sticker.UFL};
            case U:
                return new Sticker[]{Sticker.UB, Sticker.UL, Sticker.UR, Sticker.UF};
            case UR:
                return new Sticker[]{Sticker.ULB, Sticker.U, Sticker.URF};
            case UFL:
                return new Sticker[]{Sticker.UL, Sticker.UF};
            case UF:
                return new Sticker[]{Sticker.UFL, Sticker.U, Sticker.URF};
            case URF:
                return new Sticker[]{Sticker.UF, Sticker.UL};
            case FLU:
                return new Sticker[]{Sticker.FU, Sticker.FL};
            case FU:
                return new Sticker[]{Sticker.FLU, Sticker.U, Sticker.FUR};
            case FUR:
                return new Sticker[]{Sticker.FU, Sticker.FR};
            case FL:
                return new Sticker[]{Sticker.FLU, Sticker.F, Sticker.FDL};
            case F:
                return new Sticker[]{Sticker.FU, Sticker.FL, Sticker.FR, Sticker.FD};
            case FR:
                return new Sticker[]{Sticker.FLU, Sticker.F, Sticker.FUR};
            case FDL:
                return new Sticker[]{Sticker.FL, Sticker.FD};
            case FD:
                return new Sticker[]{Sticker.FDL, Sticker.F, Sticker.FRD};
            case FRD:
                return new Sticker[]{Sticker.FR, Sticker.FD};
            case RFU:
                return new Sticker[]{Sticker.RF, Sticker.RU};
            case RU:
                return new Sticker[]{Sticker.RFU, Sticker.R, Sticker.RUB};
            case RUB:
                return new Sticker[]{Sticker.RU, Sticker.RB};
            case RF:
                return new Sticker[]{Sticker.RFU, Sticker.R, Sticker.RDF};
            case R:
                return new Sticker[]{Sticker.RU, Sticker.RF, Sticker.RB, Sticker.RD};
            case RB:
                return new Sticker[]{Sticker.RUB, Sticker.R, Sticker.RBD};
            case RDF:
                return new Sticker[]{Sticker.RF, Sticker.RD};
            case RD:
                return new Sticker[]{Sticker.RDF, Sticker.R, Sticker.RBD};
            case RBD:
                return new Sticker[]{Sticker.RB, Sticker.RD};
            case DLF:
                return new Sticker[]{Sticker.DL, Sticker.DF};
            case DF:
                return new Sticker[]{Sticker.DLF, Sticker.D, Sticker.DFR};
            case DFR:
                return new Sticker[]{Sticker.DF, Sticker.DR};
            case DL:
                return new Sticker[]{Sticker.DLF, Sticker.D, Sticker.DFR};
            case D:
                return new Sticker[]{Sticker.DF, Sticker.DR, Sticker.DL, Sticker.DB};
            case DR:
                return new Sticker[]{Sticker.DRB, Sticker.D, Sticker.DBL};
            case DBL:
                return new Sticker[]{Sticker.DB, Sticker.DL};
            case DB:
                return new Sticker[]{Sticker.DBL, Sticker.D, Sticker.DRB};
            case DRB:
                return new Sticker[]{Sticker.DR, Sticker.DB};
            case BRU:
                return new Sticker[]{Sticker.UB, Sticker.UR};
            case BU:
                return new Sticker[]{Sticker.BRU, Sticker.B, Sticker.BUL};
            case BUL:
                return new Sticker[]{Sticker.BU, Sticker.BL};
            case BL:
                return new Sticker[]{Sticker.BUL, Sticker.B, Sticker.BRU};
            case B:
                return new Sticker[]{Sticker.BU, Sticker.BR, Sticker.BL, Sticker.BD};
            case BR:
                return new Sticker[]{Sticker.BDR, Sticker.B, Sticker.BLD};
            case BDR:
                return new Sticker[]{Sticker.BR, Sticker.BD};
            case BD:
                return new Sticker[]{Sticker.BLD, Sticker.B, Sticker.BDR};
            case BLD:
                return new Sticker[]{Sticker.BL, Sticker.BD};
            case LBU:
                return new Sticker[]{Sticker.LB, Sticker.LU};
            case LU:
                return new Sticker[]{Sticker.LBU, Sticker.L, Sticker.LUF};
            case LUF:
                return new Sticker[]{Sticker.LU, Sticker.LF};
            case LB:
                return new Sticker[]{Sticker.LBU, Sticker.L, Sticker.LDB};
            case L:
                return new Sticker[]{Sticker.LU, Sticker.LF, Sticker.LB, Sticker.LD};
            case LF:
                return new Sticker[]{Sticker.LUF, Sticker.L, Sticker.LFD};
            case LDB:
                return new Sticker[]{Sticker.LD, LB};
            case LD:
                return new Sticker[]{Sticker.LDB, Sticker.L, Sticker.LFD};
            case LFD:
                return new Sticker[]{Sticker.LF, Sticker.LD};
            default:
                throw new RuntimeException("unreachable code");
        }
    }

    public Sticker[] getAdjacentsStickers() {
        return this.adjacentLookup(this);
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