package me.thamma.cube;

import me.thamma.cube.interpreter.Interpreter;
import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.solver.Search;
import me.thamma.tools.commutator.Cycle;

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
            e.printStackTrace();
        }
        this.rawInput = input;
    }

    public Algorithm(Cube cube) throws UnexpectedEndOfLineException, UnexpectedTokenException, IllegalCharacterException {
        this(Search.solution(cube.getFaceletDefinition(), 20, 500, false));
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
        if (this.order() != 3)
            return false;
        return this.affectedStickers().size() == 9 || this.affectedStickers().size() == 6;
    }

    public List<Sticker> affectedStickers() {
        Cube c = new Cube();
        c.turn(this);
        List<Sticker> out = new ArrayList<Sticker>();
        for (Sticker sticker : Sticker.values()) {
            if (c.getCurrentStickerAt(sticker) != sticker) {
                out.add(sticker);
            }
        }
        return out;
    }

    public Cycle getCycle() {
        if (!isCommutator())
            return null;
        Cube c = new Cube();
        c.turn(this);
        Cube defaultCube = new Cube(Cube.DEFAULT_CUBE);
        Sticker diff = null;
        for (Sticker st : Sticker.values()) {
            if (!c.getCurrentStickerAt(st).equals(defaultCube.getCurrentStickerAt(st))) {
                diff = st;
                break;
            }
        }
        Sticker sticker2 = c.getCurrentStickerAt(diff);
        Sticker sticker3 = c.getCurrentStickerAt(sticker2);
        return new Cycle(diff, sticker3, sticker2);
    }

    @Override
    public String toString() {
        if (this.size() == 0)
            return "1";
        if (this.rawInput != null)
            return this.rawInput;
        return Arrays.toString(Arrays.stream(this.toArray()).map(a -> a.toString()).toArray()).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
    }

    public int order() {
        Cube c = new Cube();
        c.turn(this);
        if (c.isSolved()) return 1;
        int i = 1;
        while (!c.isSolved()) {
            c.turn(this);
            i++;
        }
        return i;
    }

    public static boolean isAlgorithm(String input) {
        try {
            Interpreter.interprete(input);
        } catch (IllegalCharacterException | UnexpectedTokenException | UnexpectedEndOfLineException e) {
            return false;
        }
        return true;
    }
}