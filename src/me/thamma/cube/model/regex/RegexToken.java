package me.thamma.cube.model.regex;

import me.thamma.cube.model.Sticker;

public enum RegexToken {

    UP, FRONT, RIGHT, DOWN, BACK, LEFT, ANY;

    RegexToken() {

    }

    public boolean matches(Sticker sticker) {
        return (this == ANY || sticker.toString().charAt(0) == this.name().charAt(0));
    }
}
