package javamon.frontend.dungeon;

import java.awt.*;
import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.places.Place;
import javamon.backend.exceptions.NoMonsterException;
import javamon.backend.entity.BattleArena;
import javamon.backend.entity.Monster;
import javamon.backend.entity.places.Dungeon;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Label;
import javamon.frontend.styles.Colors;

public class WanderingPanel extends Panel {
    public WanderingPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.WELCOME);

        Label wanderingLabel = new Label("You are wandering in the dungeon", "jua", 32f, 8, Colors.TRANSPARENT,
                Color.BLACK);

        add(Box.createVerticalGlue());
        add(wanderingLabel);
        add(Box.createVerticalGlue());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    wanderingLabel.setText("You are wandering in the dungeon.");
                    Thread.sleep(1000);
                    wanderingLabel.setText("You are wandering in the dungeon..");
                    Thread.sleep(1000);
                    wanderingLabel.setText("You are wandering in the dungeon...");
                    Thread.sleep(1000);
                    wanderingLabel.setText("You are wandering in the dungeon....");

                    Place place = (Dungeon) Javamon.getPOSITION();

                    Monster[] monsters = ((Dungeon) place).startWandering();

                    BattleArena battleArena = new BattleArena(monsters[0], monsters[1]);
                
                    BattleArenaPanel battleArenaPanel = new BattleArenaPanel(homeGUI, battleArena);
                    homeGUI.addPanel("battleArena", battleArenaPanel);
                    homeGUI.setPanel("battleArena");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NoMonsterException e) {
                    ResultPanel resultPanel = new ResultPanel(homeGUI, "No monster found, exiting dungeon...");
                    homeGUI.addPanel("result", resultPanel);
                    homeGUI.setPanel("result");
                }
            }
        }).start();
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/welcome_bg.png");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
