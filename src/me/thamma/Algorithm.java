package me.thamma;

import me.thamma.cube.Cube;
import me.thamma.cube.Turn;

import java.util.ArrayList;
import java.util.Arrays;

public class Algorithm extends ArrayList<Turn> {

    public Algorithm() {
        super();
    }

    public Algorithm(Turn... turns) {
        this();
        this.addAll(Arrays.asList(turns));
    }

    public Algorithm inverse() {
        Algorithm clone = new Algorithm();
        for (Turn t : this)
            clone.add(0, t.inverse());
        return clone;
    }

    public int order() {
        Cube c = new Cube();
        int i = 0;
        do {
            c.turn(this);
            i++;
        } while (!c.isSolved());
        return i;
    }

}