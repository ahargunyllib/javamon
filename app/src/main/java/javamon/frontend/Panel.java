package javamon.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class Panel extends JPanel {
    protected final HomeGUI homeGUI;

    public Panel(HomeGUI homeGUI) {
        this.homeGUI = homeGUI;
        setBorder(new EmptyBorder(16, 16, 16, 16));
    }

    public abstract void refresh();
}
