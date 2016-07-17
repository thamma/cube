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
    public int length(Metric metric) {
        return metric.length(this);
    }

    /**
     * Determines the length of the Algorithm according to the HTM metric.
     *
     * @return the algorithms length according to the HTM metric
     */
    public int length() {
        return this.length(Metric.HTM);
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
        return String.join("", Arrays.stream(this.toArray()).map(Object::toString).collect(Collectors.toList()));
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