package pl.lanuda.kret.limb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.lanuda.kret.limb.benchmark.BenchmarkResult;

import java.text.NumberFormat;

class View {

    private TextField wrappedListSizeInputTextField;
    private TextField iteratedListSizeInputTextField;

    private Button runButton;
    private Button swapButton;

    private Label outputLabel;

    private Scene scene;

    private NumberFormat numberFormat;

    View() {
        numberFormat = NumberFormat.getIntegerInstance();

        Label wrappedListSizeInputLabel = new Label("Wrapped list size");
        wrappedListSizeInputTextField = new TextField("1000");
        wrappedListSizeInputTextField.setTextFormatter(new TextFormatter<>(new BenchmarkInputTextFilter()));

        Label iteratedListSizeInputLabel = new Label("Iterated list size");
        iteratedListSizeInputTextField = new TextField("1000");
        iteratedListSizeInputTextField.setTextFormatter(new TextFormatter<>(new BenchmarkInputTextFilter()));

        runButton = new Button("Run");
        runButton.setDefaultButton(true);

        swapButton = new Button("Swap");

        outputLabel = new Label();

        HBox buttons = new HBox(2, swapButton, runButton);
        VBox root = new VBox(2, wrappedListSizeInputLabel, wrappedListSizeInputTextField,
                iteratedListSizeInputLabel, iteratedListSizeInputTextField, buttons, outputLabel);
        root.setPadding(new Insets(2));

        scene = new Scene(root, 600, 180);
    }

    String getWrappedListSizeInputText() {
        return wrappedListSizeInputTextField.getText();
    }

    String getIteratedListSizeInputText() {
        return iteratedListSizeInputTextField.getText();
    }

    void showProcessingStartedMessage(int wrappedListSize, int iteratedListSize) {
        outputLabel.setText(String.format("Running (wrapped size: %s, iterated size: %s)",
                numberFormat.format(wrappedListSize),
                numberFormat.format(iteratedListSize)));
    }

    void showSuccessOutputMessage(BenchmarkResult result) {
        outputLabel.setText(String.format("Done. Intersection size: %s, execution duration: %sns.",
                numberFormat.format(result.getIntersection().size()),
                numberFormat.format(result.getExecutionDuration())));
    }

    void showFailureOutputMessage(Throwable exception) {
        outputLabel.setText(String.format("Failed. Failure reason: %s", exception.getMessage()));
    }

    void setRunButtonActionHandler(EventHandler<ActionEvent> actionHandler) {
        runButton.setOnAction(actionHandler);
    }

    void setSwapButtonActionHandler(EventHandler<ActionEvent> actionHandler) {
        swapButton.setOnAction(actionHandler);
    }

    Scene getScene() {
        return scene;
    }

    void enableControls() {
        setControlState(false);
    }

    void disableControls() {
        setControlState(true);
    }

    private void setControlState(boolean controlState) {
        runButton.setDisable(controlState);
        swapButton.setDisable(controlState);
        wrappedListSizeInputTextField.setDisable(controlState);
        iteratedListSizeInputTextField.setDisable(controlState);
    }

    void swapInputTextFieldValues() {
        String wrappedInputText = wrappedListSizeInputTextField.getText();
        wrappedListSizeInputTextField.setText(iteratedListSizeInputTextField.getText());
        iteratedListSizeInputTextField.setText(wrappedInputText);
    }
}
