package me.thamma.cube.algorithmInterpreter.lexer;

/**
 * Created by Dominic on 2/26/2016.
 */
public class IllegalCharacterException extends Exception {

    private char character;

    public IllegalCharacterException() {

    }

    public IllegalCharacterException(char character) {
        super("Illegal character found: '" + character + "'");
        this.character = character;
    }

    public IllegalCharacterException(String message) {
        super(message);
    }

}
