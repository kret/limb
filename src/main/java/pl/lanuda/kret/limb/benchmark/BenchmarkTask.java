package pl.lanuda.kret.limb.benchmark;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenchmarkTask extends Task<BenchmarkResult> {

    private BenchmarkInput input;
    private Random random;

    public BenchmarkTask(BenchmarkInput input,
                         EventHandler<WorkerStateEvent> successHandler,
                         EventHandler<WorkerStateEvent> failureHandler) {
        if (input == null) {
            throw new NullPointerException("input cannot be null");
        }

        this.input = input;
        this.random = new Random();

        if (successHandler != null) {
            setOnSucceeded(successHandler);
        }
        if (failureHandler != null) {
            setOnFailed(failureHandler);
        }
    }

    @Override
    protected BenchmarkResult call() {
        int wrappedSize = input.getWrappedListSize();
        int iteratedSize = input.getIteratedListSize();

        List<Integer> toBeWrapped = random.ints(wrappedSize).boxed().collect(Collectors.toList());
        List<Integer> iterated = random.ints(iteratedSize).boxed().collect(Collectors.toList());

        Set<Integer> wrapped = new HashSet<>(toBeWrapped);

        Stream<Integer> stream = iterated.stream();
        long startTime = System.nanoTime();
        List<Integer> intersection = stream.filter(wrapped::contains).collect(Collectors.toList());
        long endTime = System.nanoTime();

        return new BenchmarkResult(intersection, endTime - startTime);
    }
}
