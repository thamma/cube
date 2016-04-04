package me.thamma;

import me.thamma.cube.Cube;
import me.thamma.cube.Sticker;
import me.thamma.cube.Turn;
import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws UnexpectedTokenException, IllegalCharacterException, UnexpectedEndOfLineException {


//        String in = "R U R' U' R' F R2 U' R' U' R U R' F'";
////        in = "y2 (L' B L') (F2 L B' L') (F2 L2)";
////        in = "(r2M3)2 R2 [R,U]";
////        in = "[R, U' L' U]";//Niklas
//        in = "[R'2: D2, [R:U]]";//A perm
//
//
//        in = "[RUR': D]";
//
//        Cube c = new Cube();
//        c.turn(new Algorithm("U' L'"));
//        c.print();
//
//
        Cube c = new Cube();
        System.out.println(c.getCurrentStickerAt(Sticker.UF));
        c.turn("L'U'");
        System.out.println(c.getCurrentStickerAt(Sticker.UF));

    }

}
