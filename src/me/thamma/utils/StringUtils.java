package me.thamma.utils;

/**
 * Created by Dominic on 6/3/2016.
 */
public class StringUtils {

    // wrap("wrap") == "rapw"
    public static String wrap(String s) {
        return s.substring(1, s.length()) + s.charAt(0);
    }
}
