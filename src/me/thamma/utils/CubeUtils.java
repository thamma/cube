package me.thamma.utils;

import me.thamma.cube.algorithmInterpreter.Interpreter;
import me.thamma.cube.algorithmInterpreter.lexer.IllegalCharacterException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.algorithmInterpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Sticker;
import me.thamma.utils.solverModel.Search;

public class CubeUtils {

    public static Sticker[] mergeArrays(Sticker[]... objects) {
        int size = 0;
        for (Sticker[] outer : objects)
            for (Object ignored : outer)
                size++;
        Sticker[] out = new Sticker[size];
        int i = 0;
        for (Sticker[] inner: objects)
            for (Sticker e: inner)
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
        int i = 21;
        String status = "";
        String last = "";
        while (--i >0 && !(status.equals("Error 7") || status.equals("Error 8"))) {
            System.out.println("Trying depth: " + i);
            last = status;
            status = Search.solution(cube.normalize().getFaceletDefinition(), i, 30, false);
            System.out.println("\tfound: " + status);
        }
        return new Algorithm(last);
    }

    public static Algorithm anySolve(Cube cube) {
        return solve(cube, 21);
    }

    public static Algorithm solve(Cube cube, int cap) {
        return new Algorithm(Search.solution(cube.normalize().getFaceletDefinition(), cap, 300, false));
    }

}
