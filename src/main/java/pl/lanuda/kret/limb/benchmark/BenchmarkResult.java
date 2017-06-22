package pl.lanuda.kret.limb.benchmark;

import java.util.List;

public class BenchmarkResult {

    private List<Integer> intersection;
    private long executionDuration;

    public BenchmarkResult(List<Integer> intersection, long executionDuration) {
        this.intersection = intersection;
        this.executionDuration = executionDuration;
    }

    public List<Integer> getIntersection() {
        return intersection;
    }

    public long getExecutionDuration() {
        return executionDuration;
    }
}
