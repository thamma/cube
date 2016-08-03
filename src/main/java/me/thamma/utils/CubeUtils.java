package me.thamma.utils;

import me.thamma.cube.algorithmInterpreter.Interpreter;
import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.CubeConstants;
import me.thamma.cube.model.Sticker;
import me.thamma.utils.solverModel.Search;

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
        int[] smartOrder = {0, 20, 1, 19, 2, 18, 3, 17, 4, 16, 5, 15, 6, 14, 7, 13, 8, 12, 9, 11, 10}; //to iterate extreme cases much faster
        for (int i = 0; i < 21 && solution.contains("Error"); i++) {
            System.out.println("Trying depth: " + smartOrder[i]);
            solution = Search.solution(cube.normalizeRotation().getFaceletDefinition(CubeConstants.Stickers.alienFaceletDefinition), smartOrder[i], 30, false);
            System.out.println("\tfound: " + solution);
        }
        return Algorithm.fromScramble(solution);
    }

    public static Algorithm anySolve(Cube cube) {
        return solve(cube, 21);
    }

    public static Algorithm solve(Cube cube, int cap) {
        return Algorithm.fromScramble(Search.solution(cube.normalizeRotation().getFaceletDefinition(CubeConstants.Stickers.alienFaceletDefinition), cap, 300, false));
    }

}
