package me.thamma.cube.interpreter;

import me.thamma.cube.Piece;
import me.thamma.cube.Sticker;

public class FuckOffPlsWork {
    public static Piece[] corners = {Piece.ULB, Piece.UBR, Piece.UFL, Piece.URF, Piece.DLF, Piece.DFR,  Piece.DBL, Piece.DRB};
    public static Piece[] edges = {Piece.UB, Piece.UL, Piece.UR, Piece.UF, Piece.BL, Piece.FL, Piece.FR, Piece.BR, Piece.DF, Piece.DL, Piece.DR, Piece.DB};


    public static final Sticker[] upFace = {Sticker.ULB, Sticker.UB, Sticker.UBR, Sticker.UL, Sticker.U, Sticker.UR, Sticker.UFL, Sticker.UF, Sticker.URF};
    public static final Sticker[] frontFace = {Sticker.FLU, Sticker.FU, Sticker.FUR, Sticker.FL, Sticker.F, Sticker.FR, Sticker.FDL, Sticker.FD, Sticker.FRD};
    public static final Sticker[] rightFace = {Sticker.RFU, Sticker.RU, Sticker.RUB, Sticker.RF, Sticker.R, Sticker.RB, Sticker.RDF, Sticker.RD, Sticker.RBD};
    public static final Sticker[] downFace = {Sticker.DLF, Sticker.DF, Sticker.DFR, Sticker.DL, Sticker.D, Sticker.DR, Sticker.DBL, Sticker.DB, Sticker.DRB};
    public static final Sticker[] backFace = {Sticker.BRU, Sticker.BU, Sticker.BUL, Sticker.BR, Sticker.B, Sticker.BL, Sticker.BDR, Sticker.BD, Sticker.BLD};
    public static final Sticker[] leftFace = {Sticker.LBU, Sticker.LU, Sticker.LUF, Sticker.LB, Sticker.L, Sticker.LF, Sticker.LDB, Sticker.LD, Sticker.LFD};
    public static final Sticker[][] faceStickers = {upFace, frontFace, rightFace, downFace, backFace, leftFace};

    public static final Sticker[] faceletDefinition = (Sticker[]) mergeArrays(upFace, rightFace, frontFace,
            downFace, leftFace, backFace);


    private static Sticker[] mergeArrays(Sticker[]... objects) {
        int size = 0;
        for (Sticker[] inner : objects)
            for (Object e : inner)
                size++;
        Sticker[] out = new Sticker[size];
        int i = 0;
        for (Sticker[] inner: objects)
            for (Sticker e: inner)
                out[i++] = e;
        return out;
    }
}
