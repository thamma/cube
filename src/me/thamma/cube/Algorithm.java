package me.thamma.cube;

import me.thamma.cube.compiler.Compiler;
import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;

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

    private String rawInput;
    public Algorithm(String input) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {
        super(Compiler.compile(input));
        this.rawInput = input;
    }

    public Algorithm inverse() {
        Algorithm clone = new Algorithm();
        for (Turn t : this)
            clone.add(0, t.inverse());
        return clone;
    }

    public void power(int i) {
        if (i < 1) return;
        Algorithm clone = new Algorithm();
        for (int j = 0; j < i; j++)
            clone.addAll(this);
        this.clear();
        this.addAll(clone);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(this.toArray()).map( a -> ((Turn) a).toString()).toArray()).replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","");
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

    public static boolean isAlgorithm(String input) {
        try {
            new Algorithm(input);
        } catch (IllegalCharacterException | UnexpectedTokenException | UnexpectedEndOfLineException e) {
            return false;
        }
        return true;
    }
}