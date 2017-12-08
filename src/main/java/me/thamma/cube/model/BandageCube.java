package me.thamma.cube.model;


import java.util.List;

public class BandageCube extends Cube {

    private List<Bandage> bandages;

    public BandageCube(List<Bandage> bandages, int[] pieces) {
        super(pieces);
        this.bandages = bandages;
    }

    public BandageCube(List<Bandage> bandages) {
        super();
        this.bandages = bandages;
    }

    public BandageCube(List<Bandage> bandages, Algorithm algorithm) {
        this(bandages);
        this.turn(algorithm);
    }

    public boolean matchesBandage(Bandage bandage) {
        Sticker[] adjacentStickers = this.getReverseStickerLookup().
                get(bandage.s1).getAdjacentsStickers();
        boolean contains = false;
        Sticker target = this.getReverseStickerLookup().get(bandage.s2);
        for (Sticker sticker : adjacentStickers) {
            if (sticker == target)
                contains = true;
        }
        return contains;
    }

    public boolean matchesBandages() {
        for (Bandage bandage : this.bandages) {
            if (!matchesBandage(bandage)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canTurn(Turn t) {
        BandageCube next = (BandageCube) this.clone().turn(t);
        return next.matchesBandages();
    }

    @Override
    public BandageCube clone() {
        BandageCube out = new BandageCube(this.bandages, this.getPieces());
        return out;
    }
}
