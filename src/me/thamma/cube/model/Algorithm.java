package me.thamma.cube.model;

import me.thamma.cube.algorithmInterpreter.Interpreter;
import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.utils.CubeUtils;

import java.util.*;
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

    /**
     * Algorithm constructor parsing the given scramble throwing the exceptions thrown by the interpreter
     *
     * @param scramble
     * @throws UnexpectedEndOfLineException Thrown if the very last token was not valid
     * @throws UnexpectedTokenException     Thrown if the given was not parsable
     * @throws IllegalCharacterException    Thrown if an illegal character was provided to the lexer
     */
    public Algorithm(String scramble) throws UnexpectedEndOfLineException, UnexpectedTokenException, IllegalCharacterException {
        this();
        this.addAll(Interpreter.interprete(scramble));
        if (this.size() > 0)
            this.rawInput = scramble;
    }

    public static Algorithm fromScramble(String scramble) {
        try {
            return new Algorithm(scramble);
        } catch (UnexpectedEndOfLineException | UnexpectedTokenException | IllegalCharacterException e) {
            e.printStackTrace();
        }
        return new Algorithm();
    }

    public static Algorithm fromCube(Cube cube) {
        Algorithm out = new Algorithm();
        out.addAll(CubeUtils.anySolve(cube));
        return out;
    }

    //
    //  public methods
    //

    /**
     * Whether two algorithms are congruent. That is they are equal apart from initial cube rotations
     *
     * @param algorithm The algorithm to compare to
     * @return null iff the algorithms are not congruent. Else the algorithm A, such that (this = A ∘ algorithm)
     */
    public Algorithm isCongruent(Algorithm algorithm) {
        Cube local = new Cube(this);
        for (Algorithm setup : CubeConstants.Algorithms.cubeOrientations)
            if (local.equals(new Cube().turn(setup).turn(algorithm)))
                return setup;
        return null;
    }

    public Algorithm translate(Turn translation) {
        Algorithm temp = new Algorithm();
        if (!translation.isCubeRotation())
            return null;
        for (int i = 0; i < super.size(); i++)
            temp.add(super.get(i).translateTurn(translation));
        this.rawInput = null;
        this.clear();
        this.addAll(temp);
        return this;
    }


    public Algorithm mirror(Turn translation) {
        Algorithm temp = new Algorithm();
        for (int i = 0; i < super.size(); i++)
            temp.add(super.get(i).mirrorTurn(translation));
        this.rawInput = null;
        this.clear();
        this.addAll(temp);
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
            if (!translation.isCubeRotation()) continue;
            for (int j = i + 1; j < super.size(); j++)
                super.set(j, super.get(j).translateTurn(translation));
        }
        for (int i = 0; i < super.size(); i++)
            if (super.get(i).isCubeRotation())
                super.remove(i--);
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

    public Algorithm purge() {
        return this.purgeWideTurns().purgeSliceTurns().purgeRotations();
    }

    public Algorithm purgeWideTurns() {
        Algorithm newAlg = new Algorithm();
        for (int i = 0; i < super.size(); i++) {
            if (super.get(i).isWideTurn()) {
                newAlg.addAll(Arrays.asList(super.get(i).getChildren()));
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
        Algorithm temp = new Algorithm();
        for (int i = this.size() - 1; i >= 0; i--) {
            temp.add(this.get(i).inverse());
        }
        this.clear();
        this.addAll(temp);
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
        Algorithm temp = new Algorithm();
        for (int j = 0; j < Math.abs(exponent); j++)
            temp.addAll(this);
        if (exponent < 0)
            temp.inverse();
        this.rawInput = null;
        this.clear();
        this.addAll(temp);
        return this;
    }

    /**
     * Simplifies the Algorithm such that no cube state is ever visited twice while applying the Algorithm.
     *
     * @return A reference to the current Algorithm Object
     */
    public Algorithm cancelOut() {
        int i;
        do { // tries to simplifyLoops as long as the algorithm's size decrements
            i = super.size();
            this.simplifyLoop();
        } while (i != super.size());
        this.rawInput = null;
        return this;
    }

    /**
     * Sorts an algorithm such that subsequent turns operating on the same axis (which commute) become grouped.
     * For example: "R U D' U F R L x L R'" becomes "R D' U U F R R L L x"
     *
     * @return the properly sorted Algorithm
     */
    public Algorithm groupTurns() {
        Algorithm out = new Algorithm();
        Axis curr = null;
        List<Turn> temp = new ArrayList<>();
        for (int i = 0; i < super.size(); i++) {
            Turn turn = super.get(i);
            if (curr != null && turn.getAxis() != curr) {
                temp.sort((t1, t2) -> Integer.compare(t1.ordinal(), t2.ordinal()));
                out.addAll(temp);
                temp.clear();
            }
            temp.add(turn);
            curr = turn.getAxis();
        }
        out.addAll(temp);
        return out;
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
    }

    public String rawString() {
        return super.toString();
    }

    private void simplifyLoop() {
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
