package javamon.frontend.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javamon.frontend.styles.Colors;

public class Button extends JButton {
    public Button(String text, Font font, ActionListener actionListener) { 
        super(text);
        setFont(font);
        addActionListener(actionListener);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setBackground(Colors.BACKGROUND);
        setForeground(Colors.TEXT);
        setMaximumSize(new Dimension(200, 30));

    }
}
