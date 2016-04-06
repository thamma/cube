package me.thamma.tools.commutator;

import me.thamma.cube.Sticker;

/**
 * Created by Dominic on 3/31/2016.
 */
public class Cycle {

    public Sticker s1, s2, s3;

    public Cycle(Sticker s1, Sticker s2, Sticker s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public Cycle(String st1, String st2, String st3) {
        this(String.join(",", st1, st2, st3));
    }

    public Cycle(String s) {
        String[] split = s.split(",");
        try {
            this.s1 = Sticker.valueOf(split[0]);
            this.s2 = Sticker.valueOf(split[1]);
            this.s3 = Sticker.valueOf(split[2]);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            this.s1 = null;
            this.s2 = null;
            this.s3 = null;
        }
    }

    public boolean isPossible() {
        if (s1.getOrder() == 1 || s2.getOrder() == 1 || s3.getOrder() == 1) return false;
        if (s1.getOrder() != s2.getOrder() ||
                s2.getOrder() != s3.getOrder() ||
                s3.getOrder() != s1.getOrder())
            return false;
        if (s1 == s2 || s1.rotate() == s2 || s1.rotate().rotate() == s2)
            return false;
        if (s2 == s3 || s2.rotate() == s3 || s2.rotate().rotate() == s3)
            return false;
        if (s3 == s1 || s3.rotate() == s1 || s3.rotate().rotate() == s1)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int[] i = {0};
        (s1.toString() + s2.toString() + s3.toString()).chars().forEach(j -> i[0] += j);
        return i[0];
    }

    @Override
    public String toString() {
        if (s1 == null || s2 == null || s3 == null) return null;
        return String.join(",", s1.toString(), s2.toString(), s3.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cycle)) return false;
        Cycle cycle = (Cycle) o;
        if (cycle.s1.toString().length() != this.s1.toString().length()) return false;
        if (cycle.s2.toString().length() != this.s2.toString().length()) return false;
        if (cycle.s3.toString().length() != this.s3.toString().length()) return false;
        String x = cycle.s1.toString();
        String y = cycle.s2.toString();
        String z = cycle.s3.toString();
        for (int i = 0; i < x.length(); i++) {
            if (x.equals(s1.toString()) && y.equals(s2.toString()) && z.equals(s3.toString()) ||
                    x.equals(s2.toString()) && y.equals(s3.toString()) && z.equals(s1.toString()) ||
                    x.equals(s3.toString()) && y.equals(s1.toString()) && z.equals(s2.toString()))
                return true;
            x = wrap(x);
            y = wrap(y);
            z = wrap(z);
        }
        return false;
    }

    private String wrap(String s) {
        return s.substring(1, s.length()) + s.charAt(0);
    }

}
