package me.thamma.tools.commutator;

import me.thamma.cube.Algorithm;
import me.thamma.cube.Cube;
import me.thamma.cube.Sticker;
import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;

import static me.thamma.cube.Sticker.*;

/**
 * Created by Dominic on 3/30/2016.
 */
public class CommutatorHelperMain {

    public static void main(String[] args) throws IllegalCharacterException, UnexpectedTokenException, UnexpectedEndOfLineException {

        String[] colors = {"{an error occured}", "B", "R", "Y", "G", "O", "W"};

        Cube c = new Cube();
        Algorithm alg = new Algorithm("RUR'U'R'FR2U'R'U'RUR'F'");
        c.turn(alg);
        Sticker s = RFU;
        int colorIndex = c.getColor(s);

        Cycle a = new Cycle("RFU,BLD,LBU");
        Cycle b = new Cycle(URF, DBL, ULB);
        System.out.println(a.equals(b));

    }
}
