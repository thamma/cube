package me.thamma.cube;

import me.thamma.cube.model.*;
import me.thamma.utils.CubeUtils;

import java.util.ArrayList;
import java.util.List;

import static me.thamma.cube.model.Turn.*;

public class Main {

    public static void main(String... args) {
        List<Bandage> bandage = new ArrayList<>();
        bandage.add(new Bandage(Sticker.UL, Sticker.ULB));
        bandage.add(new Bandage(Sticker.U, Sticker.UB));
        bandage.add(new Bandage(Sticker.UR, Sticker.UBR));
        bandage.add(new Bandage(Sticker.UF, Sticker.URF));
        bandage.add(new Bandage(Sticker.F, Sticker.FR));
        bandage.add(new Bandage(Sticker.R, Sticker.RB));
        bandage.add(new Bandage(Sticker.LDB, Sticker.LB));
        bandage.add(new Bandage(Sticker.L, Sticker.LD));
        bandage.add(new Bandage(Sticker.FL, Sticker.FDL));
        bandage.add(new Bandage(Sticker.FD, Sticker.FRD));
        bandage.add(new Bandage(Sticker.RD, Sticker.RBD));
        bandage.add(new Bandage(Sticker.B, Sticker.BD));
        bandage.add(new Bandage(Sticker.D, Sticker.DB));
        // Algorithm that transforms a solved cube into the state of the bandage cube
        Algorithm algo = Algorithm.fromScramble(
                "[Db'D',F'] F [U'FUF', b] F'");
        BandageCube cube = new BandageCube(bandage, algo.inverse());

        Algorithm solution = CubeUtils.bfsBandageSolution(cube, new Turn[]{UP, FRONT, RIGHT, LEFT});
        System.out.println(solution);
    }

}