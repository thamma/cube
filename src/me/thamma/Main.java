package me.thamma;

import me.thamma.cube.Algorithm;
import me.thamma.cube.Cube;
import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.solver.Search;

public class Main {

    public static void main(String[] args) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {
//        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
//        in = "[R, U' L' U]";//Niklas
//        in = "[R'2: D2, [R:U]]";//A perm
        Cube c = new Cube();
        c.turn("[R, U'L'U]");
        System.out.println(Search.solution("DLBLUBBFLBDRDRFDRBLUUUFRFLRUBFRDUFRULFDULLUBRDDFDBBRFL", 20, 500, false));
    }

}
