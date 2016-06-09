package me.thamma.cube.model;

import java.util.*;

public class Cycle {

    List<Sticker> stickerList;
    List<Cycle> children;

    public Cycle(Cube cube, Sticker sticker) {
        this.stickerList = new ArrayList<Sticker>();
        addCycle(cube, sticker);
    }

    public Cycle(Cube cube) {
        this.children = new ArrayList<Cycle>();
        for (Sticker s : UtilSets.faceletDefinition) {
            if (!this.containsPiece(s.getPiece())){

            }
        }
    }

    private void addCycle(Cube cube, Sticker sticker) {
        if (this.hasChildren()) {
            Cycle inner = new Cycle(cube, sticker);
            this.children.add(inner);
            return;
        } else {
            if (this.stickerList.size() > 0) {
                this.children = new ArrayList<Cycle>();
                this.children.add(this);
            }  else {
                do {
                    this.stickerList.add(sticker);
                    sticker = cube.getCurrentStickerAt(sticker);
                } while (!containsPiece(sticker.getPiece()));
            }
        }
    }

    public boolean containsPiece(Piece piece) {
        //if (this.hasChildren()) {
            for (Cycle inner : this.children) {
                if (inner.containsPiece(piece))
                    return true;
            }
            return false;

    }

    public boolean hasChildren() {
        return this.children == null;
    }


    @Override
    public String toString() {
        return this.stickerList.toString();
    }
}
