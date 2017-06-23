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

/**
 * Specialized JavaFX {@link Task} subclass that is responsible for generating input lists and running the algorithm
 * in own thread. Notifies about success/failure if handlers are provided.
 *
 * @author Andrzej Undzillo
 */
public class BenchmarkTask extends Task<BenchmarkResult> {

    private BenchmarkInput input;
    private Random random;

    /**
     * Validates the presence of input object, and registers success/failure handlers.
     *
     * @param input input object for the algorithm
     * @param successHandler action to perform after successful completion
     * @param failureHandler action to perform after failed completion, for instance with an exception
     */
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

    /**
     * Generates input lists and runs the algorithm.
     *
     * @return results of algorithm - a list of intersection elements and execution duration in nanoseconds
     */
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

    /**
     * For unit tests - to be able to predict the results.
     *
     * Could be refactored to move the input lists generation to a separate class that would provide them for injecting
     * here. That would simplify testing.
     *
     * @param seed seed to pass to {@link Random} instance
     */
    void setSeed(long seed) {
        random.setSeed(seed);
    }
}
