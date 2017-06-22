package pl.lanuda.kret.limb;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.lanuda.kret.limb.benchmark.BenchmarkInput;
import pl.lanuda.kret.limb.benchmark.BenchmarkResult;
import pl.lanuda.kret.limb.benchmark.BenchmarkTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LimbApplication extends Application {

    private View view;
    private ExecutorService executorService;

    public LimbApplication() {
        executorService = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        });

    }

    @Override
    public void start(Stage stage) {
        view = new View();
        hookupEvents();

        stage.setTitle("LIMB");
        stage.setScene(view.getScene());
        stage.show();
    }

    private void hookupEvents() {
        view.setRunButtonActionHandler(event -> {
            view.disableControls();

            int wrappedListSize = Integer.parseInt(view.getWrappedListSizeInputText());
            int iteratedListSize = Integer.parseInt(view.getIteratedListSizeInputText());

            view.showProcessingStartedMessage(wrappedListSize, iteratedListSize);

            BenchmarkTask benchmarkTask = new BenchmarkTask(new BenchmarkInput(wrappedListSize, iteratedListSize),
                    successEvent -> {
                        BenchmarkResult result = (BenchmarkResult) successEvent.getSource().getValue();
                        view.showSuccessOutputMessage(result);
                        view.enableControls();
                    },
                    failureEvent -> {
                        view.showFailureOutputMessage(failureEvent.getSource().getException());
                        view.enableControls();
                   });
            executorService.execute(benchmarkTask);
        });

        view.setSwapButtonActionHandler(event -> view.swapInputTextFieldValues());
    }

}
