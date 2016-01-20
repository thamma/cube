package me.thamma;

import me.thamma.Cube;

public class Main {

    public static void main(String[] args) {
        Cube c = new Cube();
        c.print();
        c.turn(Turn.X);
        c.turn(Turn.X.inverse());


        c.print();
        System.out.println(c.isSolved());
    }

}
