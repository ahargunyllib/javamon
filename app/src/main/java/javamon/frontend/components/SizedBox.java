package javamon.frontend.components;

import java.awt.Component;

import javax.swing.Box;

import javamon.frontend.styles.Colors;

public class SizedBox {
    public static Component height(int height) {
        Component comp =  Box.createVerticalStrut(height);
        comp.setBackground(Colors.TRANSPARENT);
        return comp;
    }

    public static Component width(int width) {
        Component comp =  Box.createHorizontalStrut(width);
        comp.setBackground(Colors.TRANSPARENT);
        return comp;
    }
}
