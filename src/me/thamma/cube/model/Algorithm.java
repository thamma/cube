package me.thamma.cube.model;

import me.thamma.cube.algorithmInterpreter.Interpreter;
import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.utils.CubeUtils;
import me.thamma.utils.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithm extends ArrayList<Turn> {

    public Algorithm() {
        super();
    }

    public Algorithm(Turn... turns) {
        this();
        this.addAll(Arrays.asList(turns));
    }

    private String rawInput;

    public Algorithm(String input) {
        this();
        try {
            this.addAll(Interpreter.interprete(input));
        } catch (UnexpectedTokenException |
                IllegalCharacterException |
                UnexpectedEndOfLineException e) {
            System.out.println(input);
            e.printStackTrace();
        }
        this.rawInput = input;
    }

    public Algorithm(Cube cube) {
        this();
        this.addAll(CubeUtils.anySolve(cube));
    }

    public Algorithm inverse() {
        Algorithm clone = new Algorithm();
        for (Turn t : this)
            clone.add(0, t.inverse());
        return clone;
    }

    public Algorithm power(int i) {
        if (i < 1) return this.clone();
        Algorithm clone = new Algorithm();
        for (int j = 0; j < i; j++)
            clone.addAll(this);
        return clone;
    }

    public Algorithm simplify() {
        Algorithm clone = this.clone();
        int i;
        do {
            i = clone.size();
            clone.simplifySingle();
        } while (i != clone.size());
        clone.recreateRawString();
        return clone;
    }

    // TODO: rewrite or reinvent
    @Deprecated
    private void recreateRawString() {
        Turn last = (this.size() > 0 ? this.get(0) : null);
        String out = "";
        Turn t;
        int count = 1;
        for (int i = 1; i < this.size() + 1; i++) {
            if (i != this.size()) {
                t = this.get(i);
            } else
                t = null;
            if (t != null && (last == t || last == t.inverse())) {
                count += (last.equals(t) ? 1 : -1);
            } else {
                count %= 4;
                if (count == 0) {
                    count = 1;
                } else if (count > 0) {
                    if (count == 3) {
                        out += last.inverse().toString() + " ";
                    } else
                        out += last.toString() + (count > 1 ? count : "") + " ";
                    count = 1;
                } else {
                    if (count == -3) {
                        out += last.toString() + " ";
                    } else
                        out += last.inverse().toString() + (count < -1 ? count : "") + " ";
                    count = 1;
                }
                last = t;
            }
        }
        this.rawInput = out;
    }

    public Algorithm clone() {
        return this.stream().collect(Collectors.toCollection(Algorithm::new));
    }

    private void simplifySingle() {
        int lower = Integer.MAX_VALUE;
        int upper = 0;
        int range = 0;
        Cube c = new Cube();
        List<Cube> copies = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            Turn t = this.get(i);
            copies.add(c.clone());
            c.turn(t);
            if (copies.contains(c)) {
                int index = copies.lastIndexOf(c);
                if (i - index > range) {
                    range = i - index;
                    lower = index;
                    upper = i;
                }
            }
        }
        if (range > 0)
            this.removeRange(lower, upper + 1);
    }

    public boolean isCommutator() {
        if (this.getCycles().size() != 1)
            return false;
        Cycle cycle = this.getCycles().get(0);
        return cycle.getOrder() == 3;
    }


    public Cycles getCycles() {
        return new Cycles(new Cube(this));
    }

    @Override
    public String toString() {
        if (this.size() == 0)
            return "1";
        if (this.rawInput != null)
            return this.rawInput;
        return Arrays.toString(Arrays.stream(this.toArray()).map(a -> a.toString()).toArray()).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
    }

    public int getOrder() {
        int order = 1;
        for (Cycle cycle: this.getCycles())
            order = MathUtils.lcm(order, cycle.getOrder());
        return order;
    }

//    public boolean isCongruent(Algorithm otherSolve) {
//        Cube thisCube = new Cube(this);
//        Cube otherCube;
//        for (Algorithm rotation : UtilSets.cubeOrientations) {
//            otherCube = new Cube(rotation);
//            otherCube.turn(otherSolve);
//            if (otherCube.equals(thisCube)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Algorithm getCongruency(Algorithm otherSolve) {
//        Cube thisCube = new Cube(this);
//        Cube otherCube;
//        for (Algorithm rotation : UtilSets.cubeOrientations) {
//            otherCube = new Cube(rotation);
//            otherCube.turn(otherSolve);
//            if (otherCube.equals(thisCube)) {
//                return rotation;
//            }
//        }
//        return null;
//    }
}