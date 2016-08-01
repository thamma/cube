package me.thamma.cube.model.regex;

import me.thamma.cube.model.Sticker;

public enum RegexToken {

    UP, FRONT, RIGHT, DOWN, BACK, LEFT, ANY;

    RegexToken() {

    }

    public boolean matches(Sticker sticker) {
        return (this == ANY || sticker.toString().charAt(0) == this.name().charAt(0));
    }

    public static RegexToken fromRegex(String s) {
        switch (s.toUpperCase()) {
            case ".":
                return RegexToken.ANY;
            case "U":
                return RegexToken.UP;
            case "F":
                return RegexToken.FRONT;
            case "R":
                return RegexToken.RIGHT;
            case "D":
                return RegexToken.DOWN;
            case "B":
                return RegexToken.BACK;
            case "L":
                return RegexToken.LEFT;
            default:
                return null;
        }
    }
}
