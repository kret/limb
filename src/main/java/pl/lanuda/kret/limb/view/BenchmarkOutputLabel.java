package pl.lanuda.kret.limb.view;

import javafx.scene.control.Label;
import pl.lanuda.kret.limb.benchmark.BenchmarkResult;

import java.text.NumberFormat;

public class BenchmarkOutputLabel extends Label {

    private NumberFormat numberFormat = NumberFormat.getIntegerInstance();

    public void showProcessingStartedMessage(int wrappedListSize, int iteratedListSize) {
        setText(String.format("Running (wrapped size: %s, iterated size: %s)",
            numberFormat.format(wrappedListSize),
            numberFormat.format(iteratedListSize)));
    }

    public void showSuccessOutputMessage(BenchmarkResult result) {
        setText(String.format("Done. Intersection size: %s, execution duration: %sns.",
            numberFormat.format(result.getIntersection().size()),
            numberFormat.format(result.getExecutionDuration())));
    }

    public void showFailureOutputMessage(Throwable exception) {
        setText(String.format("Failed. Failure reason: %s", exception.getMessage()));
    }
}
