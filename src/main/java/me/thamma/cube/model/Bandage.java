package me.thamma.cube.model;

public class Bandage {

    public Sticker s1, s2;

    public Bandage(Sticker s1, Sticker s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "Bandage(" + s1 + ", " + s2 + ")";
    }
}
