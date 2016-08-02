package me.thamma.cube.model.regex;

import me.thamma.cube.model.Sticker;

public enum RegexToken {

    UP, FRONT, RIGHT, DOWN, BACK, LEFT, ANY, ORIENTED;

    RegexToken() {

    }

    public boolean matches(Sticker sticker, int[] piece) {
        return (this == ANY || sticker.toString().charAt(0) == this.name().charAt(0) || this == ORIENTED && piece[piece.length - 1] == 0);
    }

    public static RegexToken fromRegex(char c) {
        switch ((c + "").toUpperCase().charAt(0)) {
            case '.':
                return RegexToken.ANY;
            case 'U':
                return RegexToken.UP;
            case 'F':
                return RegexToken.FRONT;
            case 'R':
                return RegexToken.RIGHT;
            case 'D':
                return RegexToken.DOWN;
            case 'B':
                return RegexToken.BACK;
            case 'L':
                return RegexToken.LEFT;
            case 'O':
                return RegexToken.ORIENTED;
            default:
                return null;
        }
    }

    public static RegexToken[] fromRegex(String s) {
        RegexToken[] out = new RegexToken[s.split("\\|").length];
        String[] split = s.split("\\|");
        for (int i = 0; i < split.length; i++) {
            String t = split[i];
            out[i] = fromRegex(t.charAt(0));
        }
        return out;
    }
}
