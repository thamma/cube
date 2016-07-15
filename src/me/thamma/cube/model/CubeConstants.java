package me.thamma.cube.model;

import me.thamma.utils.CubeUtils;

public class CubeConstants {

    public static Piece[] corners = {Piece.ULB, Piece.UBR, Piece.UFL, Piece.URF, Piece.DLF, Piece.DFR,  Piece.DBL, Piece.DRB};
    public static Piece[] edges = {Piece.UB, Piece.UL, Piece.UR, Piece.UF, Piece.BL, Piece.FL, Piece.FR, Piece.BR, Piece.DF, Piece.DL, Piece.DR, Piece.DB};
    public static Piece[] cornersNEdges = {Piece.ULB, Piece.UBR, Piece.UFL, Piece.URF, Piece.DLF, Piece.DFR,  Piece.DBL, Piece.DRB,
            Piece.UB, Piece.UL, Piece.UR, Piece.UF, Piece.BL, Piece.FL, Piece.FR, Piece.BR, Piece.DF, Piece.DL, Piece.DR, Piece.DB};

    public static final int[] DEFAULT_CUBE = {5610, 510, 3510, 610, 10, 310, 6210, 210, 2310, 650, 60, 620, 20, 320, 30, 350, 50, 2640, 240, 3240, 640, 40, 340, 6540, 540, 5340};

    public static final Sticker[] upFace = {Sticker.ULB, Sticker.UB, Sticker.UBR, Sticker.UL, Sticker.U, Sticker.UR, Sticker.UFL, Sticker.UF, Sticker.URF};
    public static final Sticker[] frontFace = {Sticker.FLU, Sticker.FU, Sticker.FUR, Sticker.FL, Sticker.F, Sticker.FR, Sticker.FDL, Sticker.FD, Sticker.FRD};
    public static final Sticker[] rightFace = {Sticker.RFU, Sticker.RU, Sticker.RUB, Sticker.RF, Sticker.R, Sticker.RB, Sticker.RDF, Sticker.RD, Sticker.RBD};
    public static final Sticker[] downFace = {Sticker.DLF, Sticker.DF, Sticker.DFR, Sticker.DL, Sticker.D, Sticker.DR, Sticker.DBL, Sticker.DB, Sticker.DRB};
    public static final Sticker[] backFace = {Sticker.BRU, Sticker.BU, Sticker.BUL, Sticker.BR, Sticker.B, Sticker.BL, Sticker.BDR, Sticker.BD, Sticker.BLD};
    public static final Sticker[] leftFace = {Sticker.LBU, Sticker.LU, Sticker.LUF, Sticker.LB, Sticker.L, Sticker.LF, Sticker.LDB, Sticker.LD, Sticker.LFD};
    public static final Sticker[][] faceStickers = {upFace, frontFace, rightFace, downFace, backFace, leftFace};

    // Used for the alien solverModel
    public static final Sticker[] faceletDefinition = (Sticker[]) CubeUtils.mergeArrays(upFace, rightFace, frontFace,
            downFace, leftFace, backFace);

    public static final Algorithm[] cubeOrientations = {
            new Algorithm("     "),
            new Algorithm("   y "),
            new Algorithm("   y2"),
            new Algorithm("   y'"),
            new Algorithm(" x   "),
            new Algorithm(" x y "),
            new Algorithm(" x y2"),
            new Algorithm(" x y'"),
            new Algorithm("x2   "),
            new Algorithm("x2 y "),
            new Algorithm("x2 y2"),
            new Algorithm("x2 y'"),
            new Algorithm("x'   "),
            new Algorithm("x' y "),
            new Algorithm("x' y2"),
            new Algorithm("x' y'"),
            new Algorithm(" z   "),
            new Algorithm(" z y "),
            new Algorithm(" z y2"),
            new Algorithm(" z y'"),
            new Algorithm("z'   "),
            new Algorithm("z' y "),
            new Algorithm("z' y2"),
            new Algorithm("z' y'"),


    };
}
