package me.thamma.utils;

/**
 * Created by Dominic on 6/3/2016.
 */
public class MathUtils {

    public static int lcm(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int gcd(int a, int b) {
        return (a * b) / lcm(a, b);
    }
}
