package me.thamma;

import me.thamma.compiler.Compiler;
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

    public Algorithm(String input) {
        super(Compiler.compile(input));
    }

    public Algorithm inverse() {
        Algorithm clone = new Algorithm();
        for (Turn t : this)
            clone.add(0, t.inverse());
        return clone;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    public int order() {
        Cube c = new Cube();
        c.turn(this);
        if (c.isSolved()) return 0;
        int i = 1;
        while (!c.isSolved()) {
            c.turn(this);
            i++;
        }
        return i;
    }

}