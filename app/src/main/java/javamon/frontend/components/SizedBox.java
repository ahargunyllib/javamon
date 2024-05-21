package javamon.frontend.components;

import java.awt.Component;

import javax.swing.Box;

public class SizedBox {
    public static Component height(int height) {
        return Box.createVerticalStrut(height);
    }

    public static Component width(int width) {
        return Box.createHorizontalStrut(width);
    }
}
