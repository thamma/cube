package me.thamma;

import me.thamma.Cube;

public class Main {

    public static void main(String[] args) {
        Cube c = new Cube();
        new Cube().print();
        int i = 0;
        do {
            c.turn(Turn.RIGHT);
            c.turn(Turn.UP);
            c.turn(Turn.RIGHT_PRIME);
            c.turn(Turn.UP_PRIME);
            i++;
        } while (!c.isSolved());
        System.out.println(i);
//        c.turn(Turn.FRONT_PRIME);
        c.print();
        System.out.println(c.isSolved());
    }

}
