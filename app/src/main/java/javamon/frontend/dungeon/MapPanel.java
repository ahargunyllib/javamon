package javamon.frontend.dungeon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.places.Dungeon;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;

public class MapPanel extends Panel {
    public MapPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(null);

        Button backButton = new Button("Back", "Inter-Bold", 16f, Color.WHITE, Color.BLACK, back());
        backButton.setBounds(10, 10, 200, 30);

        Dungeon[] dungeons = Javamon.getDUNGEONS();

        Button[] dungeonButtons = new Button[dungeons.length];
        for (int i = 0; i < dungeons.length; i++) {
            Dungeon dungeon = dungeons[i];
            dungeonButtons[i] = new Button(dungeon.getName(), "Inter-Bold", 16f, Color.WHITE, Color.BLACK,
                    wandering(homeGUI, dungeon));
        }

        dungeonButtons[0].setBounds(270, 140, 150, 30);
        dungeonButtons[1].setBounds(670, 180, 150, 30);
        dungeonButtons[2].setBounds(800, 500, 150, 30);

        add(dungeonButtons[0]);
        add(dungeonButtons[1]);
        add(dungeonButtons[2]);
        add(backButton);
    }

    private ActionListener wandering(HomeGUI homeGUI, Dungeon dungeon) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Javamon.setPOSITION(dungeon);
                WanderingPanel wanderingPanel = new WanderingPanel(homeGUI);
                homeGUI.addPanel("wandering", wanderingPanel);
                homeGUI.setPanel("wandering");
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/dungeon_map_bg.jpg");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }

    private ActionListener back() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("homebase");
            }
        };
    }
}
