package me.thamma.cube.model;

import java.util.ArrayList;

public class Cycle extends ArrayList<Sticker> {

    private int parity;

    public Cycle(Cube cube, Piece piece) {
        super();
        this.parity = 0;
        addCycle(cube, piece.getCanonicSticker());
    }

    private void addCycle(Cube cube, Sticker start) {
        Sticker sticker = start;
        do {
            this.add(sticker);
            sticker = cube.getCurrentStickerAt(sticker);
        } while (!containsPiece(sticker.getPiece()));
        while (start != sticker) {
            start = start.rotate();
            this.parity++;
        }
    }

    public boolean containsPiece(Piece piece) {
        for (Sticker sticker: this)
            if (sticker.getPiece() == piece)
                return true;
        return false;
    }

    public int getOrder() {
        return this.size() * (this.getParity()+1);
    }

    public int getParity() {
        return this.parity;
    }
}
