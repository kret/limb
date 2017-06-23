package pl.lanuda.kret.limb.benchmark;

import java.util.HashSet;

/**
 * A read-only input DTO for {@link BenchmarkTask}.
 *
 * @author Andrzej Undzillo
 */
public class BenchmarkInput {

    private int wrappedListSize;
    private int iteratedListSize;

    /**
     * @param wrappedListSize size of a list to wrap in {@link HashSet}
     * @param iteratedListSize size of a list to iterate over
     */
    public BenchmarkInput(int wrappedListSize, int iteratedListSize) {
        this.wrappedListSize = wrappedListSize;
        this.iteratedListSize = iteratedListSize;
    }

    public int getWrappedListSize() {
        return wrappedListSize;
    }

    public int getIteratedListSize() {
        return iteratedListSize;
    }
}
