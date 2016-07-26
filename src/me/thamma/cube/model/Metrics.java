package me.thamma.cube.model;

public enum Metrics {

    QTM(algorithm ->
            algorithm.purgeSliceTurns().purgeRotations().size()
    ),
    HTM(algorithm -> {
        return 0;
    }),
    STM(algorithm -> purgeSTM(algorithm).length(HTM));

    private AlgorithmMetric metric;

    Metrics(AlgorithmMetric metric) {
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

    private Algorithm groupAlgorithm(Algorithm algorithm) {
        Algorithm out = algorithm.clone();


        return out;
    }

}