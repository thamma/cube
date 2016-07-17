package me.thamma.cube.model;

import me.thamma.utils.MathUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Cycles extends ArrayList<Cycle> {

    private Set<Piece> pieces;

    public Cycles(Cube cube) {
        pieces = new HashSet<>();
        for (Piece piece : CubeConstants.cornersNEdges) {
            Cycle cycle = new Cycle(cube, piece);
            if (pieces.contains(cycle.get(0).getPiece()))
                continue;
            pieces.addAll(cycle.stream().map(Sticker::getPiece).collect(Collectors.toList()));
            if (cycle.size() != 1)
                this.add(cycle);
        }
    }

    public Cycles(Algorithm algorithm) {
        this(new Cube(algorithm));
    }

    public int getOrder() {
        int order = 1;
        for (Cycle cycle : this)
            order = MathUtils.lcm(order, cycle.getOrder());
        return order;
    }

}
