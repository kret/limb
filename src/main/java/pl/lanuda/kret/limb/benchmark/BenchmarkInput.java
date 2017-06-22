package pl.lanuda.kret.limb.benchmark;

public class BenchmarkInput {

    private int wrappedListSize;
    private int iteratedListSize;

    public BenchmarkInput(int wrappedListSize, int iteratedListSize) {
        if (wrappedListSize < 1) {
            throw new IllegalArgumentException("Wrapped list size must be greater than 0");
        }
        this.wrappedListSize = wrappedListSize;

        if (iteratedListSize < 1) {
            throw new IllegalArgumentException("Iterated list size must be greater than 0");
        }
        this.iteratedListSize = iteratedListSize;
    }

    public int getWrappedListSize() {
        return wrappedListSize;
    }

    public int getIteratedListSize() {
        return iteratedListSize;
    }
}
