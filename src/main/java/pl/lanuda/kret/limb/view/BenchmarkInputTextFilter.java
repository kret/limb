package pl.lanuda.kret.limb.view;

import javafx.scene.control.TextFormatter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

/**
 * A filtering operator for {@link TextFormatter} to accept digits only.
 *
 * @author Andrzej Undzillo
 */
public class BenchmarkInputTextFilter implements UnaryOperator<TextFormatter.Change> {

    private NumberFormat numberFormat = NumberFormat.getIntegerInstance();

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.getControlNewText().isEmpty()) {
            return change;
        }

        ParsePosition parsePosition = new ParsePosition(0);
        Number parsed = numberFormat.parse(change.getControlNewText(), parsePosition);

        if (parsed == null || parsePosition.getIndex() < change.getControlNewText().length()) {
            return null;
        } else {
            return change;
        }
    }
}
