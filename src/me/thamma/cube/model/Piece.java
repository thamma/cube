package me.thamma.cube.model;

public enum Piece {

    ULB, UB, UBR, UL, U, UR, UFL, UF, URF, BL, L, FL, F, FR, R, BR, B, DLF, DF, DFR, DL, D,DR, DBL, DB, DRB;

    private Sticker[] stickers;

    public Sticker[] getStickers() {
        if (this.stickers!=null)
            return this.stickers;
        this.stickers = new Sticker[this.name().length()];
        Sticker sticker = Sticker.valueOf(this.name());
        for (int i = 0; i < this.stickers.length; i++) {
            stickers[i] = sticker;
            sticker = sticker.rotate();
        }
        return this.stickers;
    }

    public Sticker getCanonicSticker() {
        return this.getStickers()[0];
    }


}
