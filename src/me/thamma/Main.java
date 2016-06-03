package me.thamma;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import me.thamma.cube.Algorithm;
import me.thamma.cube.Cube;
import me.thamma.cube.Sticker;
import me.thamma.cube.interpreter.lexer.IllegalCharacterException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.interpreter.parser.expressions.Exceptions.UnexpectedTokenException;
import me.thamma.solver.Search;

import javax.naming.directory.SearchControls;
import javax.xml.soap.SAAJMetaFactory;
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {
//        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
//        in = "[R, U' L' U]";//Niklas
//        in = "[R'2: D2, [R:U]]";//A perm
        //System.out.println(new Cube().normalize());
        //Cube c = new Cube(" [[RBU: RU' RU RU RU' R'U' R2], x2 y']");
//        Cube c = new Cube(" [[RBU: RU' RU RU RU' R'U' R2], x2 y'] [B2: R U2 R' U' R U' R' L' U2 L U L' U L]");
        System.out.println(new Algorithm("ru").order());
        //c.turn("[R, U'L'U]");
        //System.out.println(Search.solution("DLBLUBBFLBDRDRFDRBLUUUFRFLRUBFRDUFRULFDULLUBRDDFDBBRFL", 20, 500, false));
        //c.turn("((M'U)4 xy' )3");
        //Algorithm mirrorCube = new Algorithm(Search.solution("FBLBURBRLBDFFRLRDFLUUUFDFBDRUBRDLBLRDLDRLFUBDUDLFBFUUR", 20, 500, false));
        //System.out.println(mirrorCube.inverse());
        //c.turn("U L' L' B' B' L' L' D U' U' L R' U' U' F' F' D' R' R' B' D F L' R' B' B' F' U' U L' L' B' B' L' L' D U' U' L R' U' U' F' F' D' R' R' B' D F L' R' B' B' F' U'");
//       System.out.println(Search.solution(c.normalize().getFaceletDefinition(),14,300,false));
        //System.out.println(solve(c));
    }

    public static String solve(Cube cube) {
        int i = 21;
        String status = "";
        while (--i >0 && !status.equals("Error 7")) {
            System.out.println("Trying depth: " + i);
            status = Search.solution(cube.getFaceletDefinition(), i, 300, false);
            System.out.println("\tfound: " + status);
        }
        System.out.println("Difficulty: " + i);
        return status;
    }

}
