package me.thamma.cube;

import me.thamma.cube.model.BandageCube;
import me.thamma.cube.model.Sticker;
import me.thamma.cube.model.Turn;
import me.thamma.tools.Bandage;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) {
        List<Bandage> bandage = new ArrayList<>();
        bandage.add(new Bandage(Sticker.UR, Sticker.U));
        BandageCube cube = new BandageCube(bandage);
        System.out.println(cube.canTurn(Turn.RIGHT));
    }

}