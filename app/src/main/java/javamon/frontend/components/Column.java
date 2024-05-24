package javamon.frontend.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import javamon.frontend.styles.Colors;


public class Column extends JPanel {
    public Column() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(CENTER_ALIGNMENT);
        setBackground(Colors.TRANSPARENT);
    }
    
}
