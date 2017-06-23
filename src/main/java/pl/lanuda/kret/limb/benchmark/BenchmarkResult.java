package pl.lanuda.kret.limb.benchmark;

import java.util.List;

/**
 * A read-only output DTO for {@link BenchmarkTask}.
 * Represents result of running a benchmark - a {@link List} with intersection of both input lists
 * and a duration in nanoseconds.
 *
 * @author Andrzej Undzillo
 */
public class BenchmarkResult {

    private List<Integer> intersection;
    private long executionDuration;

    /**
     * @param intersection a {@link List} that is an intersection of both input lists
     * @param executionDuration execution duration returned in nanoseconds
     */
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
