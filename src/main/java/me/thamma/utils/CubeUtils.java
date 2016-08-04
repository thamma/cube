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
        for (int i = 0; i < 21 && solution.contains("Error"); i++) {
            System.out.println("Trying depth: " + i);
            solution = Search.solution(cube.normalizeRotation().getFaceletDefinition(CubeConstants.Stickers.alienFaceletDefinition), i, 12, false);
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