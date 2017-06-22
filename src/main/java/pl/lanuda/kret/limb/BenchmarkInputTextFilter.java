package pl.lanuda.kret.limb;

import javafx.scene.control.TextFormatter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class BenchmarkInputTextFilter implements UnaryOperator<TextFormatter.Change> {

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.getControlNewText().isEmpty()) {
            return change;
        }

        ParsePosition parsePosition = new ParsePosition(0);
        Number parsed = NumberFormat.getIntegerInstance().parse(change.getControlNewText(), parsePosition);

        if (parsed == null || parsePosition.getIndex() < change.getControlNewText().length()) {
            return null;
        } else {
            return change;
        }
    }
}
