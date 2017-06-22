package pl.lanuda.kret.limb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pl.lanuda.kret.limb.benchmark.BenchmarkResult;

class View {

    private TextField wrappedListSizeInputTextField;
    private TextField iteratedListSizeInputTextField;

    private Button runButton;

    private Label outputLabel;

    private Scene scene;

    View() {
        Label wrappedListSizeInputLabel = new Label("Wrapped list size");
        wrappedListSizeInputTextField = new TextField();

        Label iteratedListSizeInputLabel = new Label("Iterated list size");
        iteratedListSizeInputTextField = new TextField();

        runButton = new Button("Run");
        runButton.setDefaultButton(true);

        outputLabel = new Label();

        VBox root = new VBox(2, wrappedListSizeInputLabel, wrappedListSizeInputTextField,
                iteratedListSizeInputLabel, iteratedListSizeInputTextField, runButton, outputLabel);
        root.setPadding(new Insets(2));

        scene = new Scene(root);
    }

    String getWrappedListSizeInputText() {
        return wrappedListSizeInputTextField.getText();
    }

    String getIteratedListSizeInputText() {
        return iteratedListSizeInputTextField.getText();
    }

    void showProcessingStartedMessage(int wrappedListSize, int iteratedListSize) {
        outputLabel.setText(String.format("Running (wrapped size: %d, iterated size: %d)",
                wrappedListSize, iteratedListSize));
    }

    void showSuccessOutputMessage(BenchmarkResult result) {
        outputLabel.setText(String.format("Done. Intersection size: %d, execution duration: %dns.",
                result.getIntersection().size(),
                result.getExecutionDuration()));
    }

    void showFailureOutputMessage(Throwable exception) {
        outputLabel.setText(String.format("Failed. Failure reason: %s", exception.getMessage()));
    }

    void setRunButtonActionHandler(EventHandler<ActionEvent> actionHandler) {
        runButton.setOnAction(actionHandler);
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
        wrappedListSizeInputTextField.setDisable(controlState);
        iteratedListSizeInputTextField.setDisable(controlState);
    }
}
