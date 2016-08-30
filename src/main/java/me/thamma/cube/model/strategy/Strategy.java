package me.thamma.cube.model.strategy;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;

public abstract class Strategy {

    public Algorithm getSolution(Cube cube){
        Cube temp = cube.clone();
        Algorithm out = new Algorithm();
        for (SubStrategy strat: getSteps()) {
            Algorithm solution = strat.getSolution(temp);
            out.concat(solution);
            System.out.println(solution);
            temp.turn(solution);
        }
        return out;
    }

    abstract SubStrategy[] getSteps();

}
