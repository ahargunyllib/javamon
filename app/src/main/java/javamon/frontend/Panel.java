package javamon.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javamon.frontend.styles.Colors;

public abstract class Panel extends JPanel {
    protected final HomeGUI homeGUI;

    public Panel(HomeGUI homeGUI) {
        this.homeGUI = homeGUI;
        setBorder(new EmptyBorder(16, 16, 16, 16));
        setBackground(Colors.BACKGROUND);
    }
}
