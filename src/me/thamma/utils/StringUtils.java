package me.thamma.utils;

public class StringUtils {

    // wrap("wrap") == "rapw"
    public static String wrap(String s) {
        return s.substring(1, s.length()) + s.charAt(0);
    }
}
