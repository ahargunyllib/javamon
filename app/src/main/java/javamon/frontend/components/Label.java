package javamon.frontend.components;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javamon.frontend.styles.Colors;

public class Label extends JLabel {
    public Label(String text, Font font) {
        super(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(font);
        setAlignmentX(CENTER_ALIGNMENT);
        setForeground(Colors.TEXT);
    }
}
