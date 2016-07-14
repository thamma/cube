package me.thamma.cube.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum Metric {

    QTM(algorithm -> {
        throw new NotImplementedException();
        //return 0;
    }),
    HTM(algorithm -> {
        throw new NotImplementedException();
        //return 0;
    }),
    STM(algorithm -> purgeSTM(algorithm).length(HTM));

    private Algorithm.AlgorithmMetric metric;

    Metric(Algorithm.AlgorithmMetric metric) {
        this.metric = metric;
    }

    public int length(Algorithm algorithm) {
        return this.metric.run(algorithm);
    }

    private static Algorithm purgeSTM(Algorithm algorithm) {
        Algorithm out = new Algorithm();
        for (Turn t : algorithm) {
            switch (t) {
                case MIDDLE: {
                    out.add(Turn.RIGHT);
                    out.add(Turn.LEFT_PRIME);
                    out.add(Turn.X_PRIME);
                    break;
                }
                case MIDDLE_PRIME: {
                    out.add(Turn.RIGHT_PRIME);
                    out.add(Turn.LEFT);
                    out.add(Turn.X);
                    break;
                }
                case STANDING: {
                    out.add(Turn.FRONT_PRIME);
                    out.add(Turn.BACK);
                    out.add(Turn.Z);
                    break;
                }
                case STANDING_PRIME: {
                    out.add(Turn.FRONT);
                    out.add(Turn.BACK_PRIME);
                    out.add(Turn.Z_PRIME);
                    break;
                }
                case EQUATORIAL: {
                    out.add(Turn.UP);
                    out.add(Turn.DOWN_PRIME);
                    out.add(Turn.Y_PRIME);
                    break;
                }
                case EQUATORIAL_PRIME: {
                    out.add(Turn.UP_PRIME);
                    out.add(Turn.DOWN);
                    out.add(Turn.Y);
                    break;
                }
                default:
                    out.add(t);
            }
        }
        return out;
    }
}