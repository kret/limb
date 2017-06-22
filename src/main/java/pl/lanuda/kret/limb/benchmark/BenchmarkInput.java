package pl.lanuda.kret.limb.benchmark;

public class BenchmarkInput {

    private int wrappedListSize;
    private int iteratedListSize;

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
