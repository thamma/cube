package me.thamma.cube.model;

public enum Metrics {

    QTM(algorithm ->
            algorithm.purgeSliceTurns().purgeRotations().cancelOut().size()
    ),
    STM(algorithm -> 0),
    HTM(algorithm -> algorithm.purgeSliceTurns().length(Metrics.STM))
    ;

    private AlgorithmMetric metric;

    Metrics(AlgorithmMetric metric) {
        this.metric = metric;
    }

    public int length(Algorithm algorithm) {
        return this.metric.run(algorithm);
    }

    private Algorithm groupAlgorithm(Algorithm algorithm) {
        Algorithm out = algorithm.clone();

        return out;
    }

}