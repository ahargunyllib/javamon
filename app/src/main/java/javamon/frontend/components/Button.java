package javamon.frontend.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javamon.frontend.styles.Typography;

public class Button extends JButton {
    public Button(String text, String fontName, float fontSize, Color bgColor, Color fgColor,
            ActionListener actionListener) {
        super(text);
        Font font = null;

        if (fontName.equals("jua")) {
            font = new Typography().new Jua(fontSize).getFont();
        } else if (fontName.startsWith("Inter")) {
            font = new Typography().new Inter(fontName, fontSize).getFont();
        }

        setFont(font);
        addActionListener(actionListener);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setBackground(bgColor);
        setForeground(fgColor);
        setMaximumSize(new Dimension(150, 30));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

        super.paintComponent(g);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);

        g2.dispose();
    }
}
