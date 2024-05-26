package javamon.frontend.components;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class TextField extends JTextField {
    public TextField() {
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Typography().new Inter("Regular", 20f).getFont());
        setAlignmentX(CENTER_ALIGNMENT);
        setForeground(Colors.BACKGROUND);
        setMaximumSize(new Dimension(200, 30));
    }
}
