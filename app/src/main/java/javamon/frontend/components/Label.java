package javamon.frontend.components;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class Label extends JLabel {
    public Label(String text, Font font) {
        super(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(font);
        setAlignmentX(CENTER_ALIGNMENT);
        setForeground(Colors.TEXT);
        // setBorder(new EmptyBorder(padding*1, padding*2, padding*1, padding*2));
    }

    public Label(String text, String fontName, float fontSize, int padding, Color bgColor, Color fgColor) {
        super(text);
        Font font = null;

        Typography typography = new Typography();
        if (fontName.equals("jua")) {
            font = typography.new Jua(fontSize).getFont();
        } else if (fontName.equals("inter")) {
            font = typography.new Inter(fontName, fontSize).getFont();
        }

        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(font);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setForeground(fgColor);
        setBackground(bgColor);
        setOpaque(false);
        setBorder(new EmptyBorder(padding * 1, padding * 2, padding * 0, padding * 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);

        super.paintComponent(g);

        g2.dispose();
    }
}
