package pl.lanuda.kret.limb.view;

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

/**
 * A view class for LIMB application. Groups and lays out all UI controls. Allows for basic operations on them.
 *
 * @author Andrzej Undzillo
 */
public class View {

    private TextField wrappedListSizeInputTextField;
    private TextField iteratedListSizeInputTextField;

    private Button runButton;
    private Button swapButton;

    private BenchmarkOutputLabel outputLabel;

    private Scene scene;

    public View() {
        Label wrappedListSizeInputLabel = new Label("Wrapped list size");
        wrappedListSizeInputTextField = new TextField("1000");
        wrappedListSizeInputTextField.setTextFormatter(new TextFormatter<>(new BenchmarkInputTextFilter()));

        Label iteratedListSizeInputLabel = new Label("Iterated list size");
        iteratedListSizeInputTextField = new TextField("1000");
        iteratedListSizeInputTextField.setTextFormatter(new TextFormatter<>(new BenchmarkInputTextFilter()));

        runButton = new Button("Run");
        runButton.setDefaultButton(true);

        swapButton = new Button("Swap");

        outputLabel = new BenchmarkOutputLabel();

        HBox buttons = new HBox(2, swapButton, runButton);
        VBox root = new VBox(2, wrappedListSizeInputLabel, wrappedListSizeInputTextField,
                iteratedListSizeInputLabel, iteratedListSizeInputTextField, buttons, outputLabel);
        root.setPadding(new Insets(2));

        scene = new Scene(root, 600, 180);
    }

    public String getWrappedListSizeInputText() {
        return wrappedListSizeInputTextField.getText();
    }

    public String getIteratedListSizeInputText() {
        return iteratedListSizeInputTextField.getText();
    }

    public void showProcessingStartedMessage(int wrappedListSize, int iteratedListSize) {
        outputLabel.showProcessingStartedMessage(wrappedListSize, iteratedListSize);
    }

    public void showSuccessOutputMessage(BenchmarkResult result) {
        outputLabel.showSuccessOutputMessage(result);
    }

    public void showFailureOutputMessage(Throwable exception) {
        outputLabel.showFailureOutputMessage(exception);
    }

    public void setRunButtonActionHandler(EventHandler<ActionEvent> actionHandler) {
        runButton.setOnAction(actionHandler);
    }

    public void setSwapButtonActionHandler(EventHandler<ActionEvent> actionHandler) {
        swapButton.setOnAction(actionHandler);
    }

    public void swapInputTextFieldValues() {
        String wrappedInputText = wrappedListSizeInputTextField.getText();
        wrappedListSizeInputTextField.setText(iteratedListSizeInputTextField.getText());
        iteratedListSizeInputTextField.setText(wrappedInputText);
    }

    public Scene getScene() {
        return scene;
    }

    public void enableControls() {
        setControlState(false);
    }

    public void disableControls() {
        setControlState(true);
    }

    private void setControlState(boolean controlState) {
        runButton.setDisable(controlState);
        swapButton.setDisable(controlState);
        wrappedListSizeInputTextField.setDisable(controlState);
        iteratedListSizeInputTextField.setDisable(controlState);
    }
}
