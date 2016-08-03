package me.thamma.cube.model;

public enum Piece {

    ULB, UB, UBR, UL, U, UR, UFL, UF, URF, BL, L, FL, F, FR, R, BR, B, DLF, DF, DFR, DL, D,DR, DBL, DB, DRB;

    private Sticker[] stickers;

    public Sticker[] getStickers() {
        if (this.stickers!=null)
            return this.stickers;
        this.stickers = new Sticker[this.name().length()];
        this.stickers[0] = Sticker.valueOf(this.name());
        for (int i = 1; i < this.stickers.length; i++)
            this.stickers[i] = this.stickers[i-1].rotate();
        return this.stickers;
    }

    public Sticker getCanonicalSticker() {
        return this.getStickers()[0];
    }


}
