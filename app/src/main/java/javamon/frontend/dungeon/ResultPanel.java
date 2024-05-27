package javamon.frontend.dungeon;

import java.awt.*;
import javax.swing.*;

import javamon.backend.Javamon;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Label;
import javamon.frontend.styles.Colors;

public class ResultPanel extends Panel {
    public ResultPanel(HomeGUI homeGUI, String result) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.WELCOME);

        Label resultLabel = new Label(result, "jua", 32f, 8, Colors.TRANSPARENT,
                Color.BLACK);

        add(Box.createVerticalGlue());
        add(resultLabel);
        add(Box.createVerticalGlue());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    if (result.contains("defeated")) {
                        WanderingPanel wanderingPanel = new WanderingPanel(homeGUI);
                        homeGUI.addPanel("wandering", wanderingPanel);
                        homeGUI.setPanel("wandering");
                        return;
                    }

                    Javamon.setPOSITION(null);
                    homeGUI.setPanel("map");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/welcome_bg.png");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
