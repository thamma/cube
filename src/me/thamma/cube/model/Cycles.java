package me.thamma.cube.model;

import me.thamma.utils.CubeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Cycles extends ArrayList<Cycle> {

    private Set<Piece> pieces;

    public Cycles(Cube cube) {
        pieces = new HashSet<Piece>();
        for (Piece piece : CubeConstants.cornersNEdges) {
            Cycle cycle = new Cycle(cube, piece);
            if (pieces.contains(cycle.get(0).getPiece()))
                continue;
            for (Sticker sticker : cycle)
                pieces.add(sticker.getPiece());
            if (cycle.size() != 1)
                this.add(cycle);
        }
    }

    public Cycles (Algorithm algorithm) {
        this(new Cube(algorithm));
    }


}
