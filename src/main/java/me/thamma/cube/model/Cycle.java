package me.thamma.cube.model;

import java.util.ArrayList;

public class Cycle extends ArrayList<Sticker> {

    private boolean parity;
    private int type;

    public Cycle(Cube cube, Piece piece) {
        super();
        type = piece.name().length();
        this.parity = false;
        addCycle(cube, piece.getCanonicalSticker());
    }

    private void addCycle(Cube cube, Sticker start) {
        Sticker sticker = start;
        do {
            this.add(sticker);
            sticker = cube.getCurrentStickerAt(sticker);
        } while (!containsPiece(sticker.getPiece()));
        while (start != sticker) {
            start = start.rotate();
            this.parity = true;
        }
        if (this.parity) {
            this.remove(0);
            this.add(0, start);
        }
    }

    private boolean containsPiece(Piece piece) {
        for (Sticker sticker : this)
            if (sticker.getPiece() == piece)
                return true;
        return false;
    }

    public int getOrder() {
        if (this.parity)
            return (this.size()) * this.type;
        return super.size();
    }
}
