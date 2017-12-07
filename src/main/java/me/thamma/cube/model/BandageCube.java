package me.thamma.cube.model;

import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Sticker;
import me.thamma.cube.model.Turn;
import me.thamma.tools.Bandage;

import java.util.List;

public class BandageCube extends Cube{

    private List<Bandage> bandages;

    public BandageCube(List<Bandage> bandages) {
        this.bandages = bandages;
    }

    private boolean matchesBandages() {
        for (Bandage bandage: this.bandages) {
            Sticker[] adjacentStickers = this.getReverseStickerLookup().get(bandage.s1).getAdjacentsStickers();
            boolean contains = false;
            for (Sticker sticker: adjacentStickers)
                if (sticker == bandage.s2)
                    contains = true;
            if (!contains)
                return false;

        }
        return true;
    }

    @Override
    public boolean canTurn(Turn t) {
        BandageCube next = (BandageCube) this.clone();
        return next.matchesBandages();
    }
}
