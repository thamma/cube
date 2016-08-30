package me.thamma.utils;

import me.thamma.cube.algorithmInterpreter.Interpreter;
import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.model.*;
import me.thamma.cube.model.regex.CubeRegex;
import me.thamma.utils.solverModel.Search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static me.thamma.cube.model.Turn.*;

public class CubeUtils {

    public static Sticker[] joinStickerArrays(Sticker[]... objects) {
        int size = 0;
        for (Sticker[] outer : objects)
            for (Object ignored : outer)
                size++;
        Sticker[] out = new Sticker[size];
        int i = 0;
        for (Sticker[] inner : objects)
            for (Sticker e : inner)
                out[i++] = e;
        return out;
    }

    public static boolean isValidAlgorithm(String input) {
        try {
            Interpreter.interprete(input);
        } catch (IllegalCharacterException | UnexpectedTokenException | UnexpectedEndOfLineException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidSticker(String s) {
        try {
            Sticker.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public static Algorithm perfectSolve(Cube cube) {
        String solution = "Error";
        for (int i = 0; i < 21 && solution.contains("Error"); i++) {
            System.out.println("Trying depth: " + i);
            solution = Search.solution(cube.normalizeRotation().getFaceletDefinition(CubeConstants.Stickers.alienFaceletDefinition), i, 12, false);
        }
        return Algorithm.fromScramble(solution);
    }

    public static Algorithm bfsSolution(Cube cube) {
        return bfsSolution(cube, new Turn[]{UP, FRONT, RIGHT, DOWN, BACK, LEFT});
    }

    public static Algorithm bfsSolution(Cube cube, Turn[] turns) {
        Queue<Algorithm> solutions = new LinkedList<>();
        solutions.add(new Algorithm());
        for (Turn t : turns)
            solutions.add(new Algorithm(t));

        int currSize = 1;
        long start = System.nanoTime();
        double lastdelta = 1;
        while (!solutions.isEmpty()) {

            Algorithm head = solutions.poll();
            if (head.size() > currSize) {
                currSize++;
                System.out.println(head);
                System.out.printf("%d\t%.2f\t%.1f\n", currSize, Double.valueOf(System.nanoTime() - start) / 1000000000, (System.nanoTime() - start) / lastdelta / 1000000000);
                lastdelta = Double.valueOf(System.nanoTime() - start) / 1000000000;
                start = System.nanoTime();
            }
            if (cube.clone().turn(head).isSolved())
                return head;
            for (Turn t : turns) {
                Algorithm temp = head.clone().concat(t).cancelOut();
                if (temp.size() > currSize)
                    solutions.add(temp);
            }
        }
        return null;
    }

    public static Algorithm bfsSolution(Cube start, CubeRegex end, Turn[] turns) {
        return bfsSolution(start, end, turns, Metrics.QTM);
    }

    public static Algorithm bfsSolution(Cube start, CubeRegex end, Turn[] turns, Metrics metric) {
        Queue<Algorithm> solutions = new LinkedList<>();
        Set<Cube> visited = new HashSet<>();
        solutions.add(new Algorithm());
        for (Turn t : turns)
            solutions.add(new Algorithm(t));
        int currSize = 1;
        long time = System.currentTimeMillis();
        while (!solutions.isEmpty()) {
            Algorithm head = solutions.poll();
            if (head.size() > currSize)
                System.out.printf("%d\t%d\n",++currSize, System.currentTimeMillis() - time);
            Cube currCube = start.clone().turn(head);
            if (end.matches(currCube))
                return head;
            if (visited.contains(currCube))
                continue;
            visited.add(currCube);
            for (Turn t : turns) {
                Algorithm temp = head.clone().concat(t).cancelOut();
                if (temp.length(metric) > currSize)
                    solutions.add(temp);
            }
        }
        return null;
    }

    public static Algorithm anySolve(Cube cube) {
        return solve(cube, 21);
    }

    public static Algorithm solve(Cube cube, int cap) {
        return Algorithm.fromScramble(Search.solution(cube.normalizeRotation().getFaceletDefinition(CubeConstants.Stickers.alienFaceletDefinition), cap, 300, false));
    }

}
