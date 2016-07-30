package me.thamma.cube.model;

import me.thamma.cube.algorithmInterpreter.Interpreter;
import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.utils.CubeUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithm extends ArrayList<Turn> {

    private String rawInput = null;

    //
    //  constructors and factory methods
    //

    public Algorithm() {
        super();
    }

    public Algorithm(Turn... turns) {
        this();
        this.addAll(Arrays.asList(turns));
    }

    public Algorithm(String input) {
        this();
        try {
            this.addAll(Interpreter.interprete(input));
        } catch (UnexpectedTokenException |
                IllegalCharacterException |
                UnexpectedEndOfLineException e) {
            System.out.printf("Could not construct Algorithm from %s.\n", input);
            e.printStackTrace();
        }
        if (this.size() > 0)
            this.rawInput = input;
    }

    public Algorithm(Cube cube) {
        this();
        this.addAll(CubeUtils.anySolve(cube));
    }

    //
    //  public methods
    //

    /**
     * Whether two algorithms are congruent. That is they are equal apart from initial cube rotations.
     *
     * @param algorithm The algorithm to compare to
     * @return null iff the algorithms are not congruent. Else the algorithm A, such that (this = A * algorithm)
     */
    public Algorithm isCongruent(Algorithm algorithm) {
        Cube local = new Cube(this);
        for (Algorithm setup : CubeConstants.cubeOrientations)
            if (local.equals(new Cube().turn(setup).turn(algorithm)))
                return setup;
        return null;
    }

    private Algorithm translate(Turn translation, int startindex) {
        if (!translation.isCubeRotation())
            return null;
        for (int i = 0; i < super.size(); i++) {
            if (this.get(i) == translation) {
                //this.remove(i++);
            } else
                super.set(i, this.get(i).translateTurn(translation));
        }
        this.rawInput = null;
        return this;
    }

    /**
     * Removes all cube rotations from the current Algorithm and translates the algorithm along these rotations, accordingly
     *
     * @return A reference to the current Algorithm Object
     */
    public Algorithm purgeRotations() {
        for (int i = 0; i < super.size(); i++) {
            Turn translation = super.get(i);
            if (!translation.isCubeRotation())
                continue;
            super.remove(i);
            for (int j = i; j < super.size(); j++){
//                System.out.printf("%s -> %s  (%s)\n", super.get(i), super.get(i).translateTurn(translation), translation    );
                super.set(j, super.get(j).translateTurn(translation));}

        }
        this.rawInput = null;
        return this;
    }

    /**
     * Removes all slice turns from the current Algorithm and replaces them with basic turns and cube rotations, accordingly
     *
     * @return A reference to the current Algorithm Object
     */
    public Algorithm purgeSliceTurns() {
        Algorithm newAlg = new Algorithm();
        for (int i = 0; i < super.size(); i++) {
            if (super.get(i).isSliceTurn()) {
                switch (super.get(i)) {
                    case MIDDLE: {
                        newAlg.addAll(Arrays.asList(Turn.LEFT_PRIME, Turn.X_PRIME, Turn.RIGHT));
                        break;
                    }
                    case MIDDLE_PRIME: {
                        newAlg.addAll(Arrays.asList(Turn.LEFT, Turn.X, Turn.RIGHT_PRIME));
                        break;
                    }
                    case EQUATORIAL: {
                        newAlg.addAll(Arrays.asList(Turn.UP, Turn.Y_PRIME, Turn.DOWN_PRIME));
                        break;
                    }
                    case EQUATORIAL_PRIME: {
                        newAlg.addAll(Arrays.asList(Turn.UP_PRIME, Turn.Y, Turn.DOWN));
                        break;
                    }
                    case STANDING: {
                        newAlg.addAll(Arrays.asList(Turn.FRONT_PRIME, Turn.Z_PRIME, Turn.BACK));
                        break;
                    }
                    case STANDING_PRIME: {
                        newAlg.addAll(Arrays.asList(Turn.FRONT, Turn.Z, Turn.BACK_PRIME));
                        break;
                    }
                }
            } else
                newAlg.add(super.get(i));
        }
        super.clear();
        super.addAll(newAlg);
        this.rawInput = null;
        return this;
    }

    /**
     * Inverses the algorithm. That is the algorithm will now cancel out the original one.
     *
     * @return A reference to the current Algorithm Object
     */
    public Algorithm inverse() {
        if (super.size() > 0)
            for (int i = 0; i < super.size(); i++)
                super.add(i, super.remove(super.size() - 1).inverse());
        this.rawInput = null;
        return this;
    }

    /**
     * Raises the Algorithm to the exponent'th power. If the integer specified is negative, the algorithm will be inverted.
     *
     * @param exponent
     * @return A reference to the current Algorithm Object
     */
    public Algorithm power(int exponent) {
        if (exponent == 0)
            this.clear();

        for (int j = 0; j < Math.abs(exponent) - 1; j++)
            super.addAll(this);
        if (exponent < 0)
            this.inverse();
        this.rawInput = null;
        return this;
    }

    /**
     * Simplifies the Algorithm such that no cube state is ever visited twice while applying the Algorithm.
     *
     * @return A reference to the current Algorithm Object
     */
    public Algorithm cancelOut() {
        int i;
        do {
            i = super.size();
            this.simplifyLoops();
        } while (i != super.size());
        this.rawInput = null;
        return this;
    }

    /**
     * Determines the length of the Algorithm according to a specified metric.
     *
     * @param metric The metric according to which the algorithm should be measured
     * @return the algorithms length according to the metric
     */
    public int length(Metrics metric) {
        return metric.length(this);
    }

    /**
     * Determines the length of the Algorithm according to the HTM metric.
     *
     * @return the algorithms length according to the HTM metric
     */
    public int length() {
        return this.length(Metrics.HTM);
    }

    /**
     * Creates a new Algorithm Object representing the same Algorithm
     *
     * @return a proper copy of the current Algorithm
     */
    @Override
    public Algorithm clone() {
        return this.stream().collect(Collectors.toCollection(Algorithm::new));
    }

    @Override
    public String toString() {
        if (this.size() == 0)
            return "1";
        if (this.rawInput != null)
            return this.rawInput;
        return String.join(" ", Arrays.stream(this.toArray()).map(Object::toString).collect(Collectors.toList()));
        // return Arrays.toString(Arrays.stream(this.toArray()).map(a -> a.toString()).toArray()).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
    }

    private void simplifyLoops() {
        int lower = Integer.MAX_VALUE;
        int upper = 0;
        int range = 0;
        Cube c = new Cube();
        List<Cube> copies = new ArrayList<>(super.size());
        for (int i = 0; i < this.size(); i++) {
            Turn t = this.get(i);
            copies.add(c.clone());
            c.turn(t); //ok
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

    public boolean isPureCommutator() {
        if (this.getCycles().size() != 1)
            return false;
        return this.getCycles().get(0).getOrder() == 3;
    }


    public Cycles getCycles() {
        return new Cycles(this);
    }

    public int getOrder() {
        return new Cycles(this).getOrder();
    }
}