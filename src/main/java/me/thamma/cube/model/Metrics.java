package me.thamma.cube.model;

public enum Metrics {

    QTM(algorithm ->
            algorithm.clone().purgeSliceTurns().purgeRotations().cancelOut().size()
    ),
    STM(algorithm -> {
        Algorithm alg = algorithm.clone().groupTurns().purgeRotations();
        int count = 0;
        Turn curr = null;
        for (int i = 0; i < alg.size(); i++) {
            if (curr == null || curr != alg.get(i)) {
                count++;
            }
            curr = alg.get(i);
        }
        return count;
    }),
    HTM(algorithm -> algorithm.clone().purgeSliceTurns().length(Metrics.STM));

    private AlgorithmMetric metric;

    Metrics(AlgorithmMetric metric) {
        this.metric = metric;
    }

    public int length(Algorithm algorithm) {
        return this.metric.eval(algorithm);
    }
}