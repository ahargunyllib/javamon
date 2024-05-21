package javamon.frontend.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import javamon.frontend.styles.Colors;

public class Row extends JPanel {
    public Row() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Colors.BACKGROUND);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
