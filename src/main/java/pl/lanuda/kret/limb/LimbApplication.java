package pl.lanuda.kret.limb;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LimbApplication extends Application {

    private View view;

    @Override
    public void start(Stage stage) {
        view = new View();

        stage.setTitle("LIMB");
        stage.setScene(view.scene);
        stage.show();
    }

    private static class View {

        private TextField wrappedListSizeInputTextField;
        private TextField iteratedListSizeInputTextField;

        private Button runButton;

        private Label outputLabel;

        private Scene scene;

        private View() {
            Label wrappedListSizeInputLabel = new Label("Wrapped list size");
            wrappedListSizeInputTextField = new TextField();

            Label iteratedListSizeInputLabel = new Label("Iterated list size");
            iteratedListSizeInputTextField = new TextField();

            runButton = new Button("Run");
            outputLabel = new Label();

            VBox root = new VBox(2, wrappedListSizeInputLabel, wrappedListSizeInputTextField, iteratedListSizeInputLabel, iteratedListSizeInputTextField, runButton, outputLabel);
            root.setPadding(new Insets(2));

            scene = new Scene(root);
        }
    }
}
