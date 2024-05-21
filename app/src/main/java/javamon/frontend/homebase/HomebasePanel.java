package javamon.frontend.homebase;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;

public class HomebasePanel extends Panel {
    public HomebasePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BorderLayout());
        setBackground(Colors.BACKGROUND);

        Label label = new Label("Homebase", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button dungeonBtn = new Button("Go to Dungeon", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button restoreHpBtn = new Button("Restore HP", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestoreHpPanel restoreHpPanel = new RestoreHpPanel(homeGUI);
                homeGUI.addPanel("restoreHp", restoreHpPanel);
                homeGUI.setPanel("restoreHp");
            }
        });

        Button evolveBtn = new Button("Evolve", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvolvePanel evolvePanel = new EvolvePanel(homeGUI);
                homeGUI.addPanel("evolve", evolvePanel);
                homeGUI.setPanel("evolve");
            }
        });

        Button levelUpBtn = new Button("Level Up", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelUpPanel levelUpPanel = new LevelUpPanel(homeGUI);
                homeGUI.addPanel("levelUp", levelUpPanel);
                homeGUI.setPanel("levelUp");
            }
        });

        Button shopBtn = new Button("Go to Shop", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopPanel shopPanel = new ShopPanel(homeGUI);
                homeGUI.addPanel("shop", shopPanel);
                homeGUI.setPanel("shop");
            }
        });

        Button saveMonsterBtn = new Button("Save Monster", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveMonsterPanel saveMonsterPanel = new SaveMonsterPanel(homeGUI);
                homeGUI.addPanel("saveMonster", saveMonsterPanel);
                homeGUI.setPanel("saveMonster");
            }
        });

        Button restoreMonsterBtn = new Button("Restore Monster", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestoreMonsterPanel restoreMonsterPanel = new RestoreMonsterPanel(homeGUI);
                homeGUI.addPanel("restoreMonster", restoreMonsterPanel);
                homeGUI.setPanel("restoreMonster");
            }
        });

        Column topSidePanel = new Column();
        topSidePanel.setAlignmentX(CENTER_ALIGNMENT);
        topSidePanel.add(label);
        topSidePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        topSidePanel.add(usernameLbl);

        Row leftSidePanel = new Row();
        leftSidePanel.add(Box.createVerticalGlue());
        leftSidePanel.add(backBtn);
        leftSidePanel.add(Box.createVerticalGlue());

        Column rightSidePanel = new Column();
        rightSidePanel.add(Box.createVerticalGlue());
        rightSidePanel.add(dungeonBtn);
        rightSidePanel.add(Box.createVerticalStrut(8));
        rightSidePanel.add(shopBtn);
        rightSidePanel.add(Box.createVerticalGlue());

        Row bottomSidePanel = new Row();
        bottomSidePanel.add(Box.createHorizontalGlue());
        bottomSidePanel.add(restoreHpBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(evolveBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(levelUpBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(saveMonsterBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(restoreMonsterBtn);
        bottomSidePanel.add(Box.createHorizontalGlue());

        Column[] monstersPanel = new Column[Javamon.getPLAYER().getMonsters().length];
        for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
            monstersPanel[i] = new Column();
            monstersPanel[i].add(Box.createVerticalGlue());
            monstersPanel[i].add(new Label(Javamon.getPLAYER().getMonsters()[i].getName(), Typography.LABEL));
            monstersPanel[i].add(Box.createVerticalStrut(8));
            monstersPanel[i].add(new Label("HP: " + Javamon.getPLAYER().getMonsters()[i].getCurrHp() + "/"
                    + Javamon.getPLAYER().getMonsters()[i].getMaxHp(), Typography.LABEL));
            monstersPanel[i].add(Box.createVerticalGlue());
        }

        Row centerSidePanel = new Row();
        centerSidePanel.add(Box.createHorizontalGlue());
        for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
            centerSidePanel.add(monstersPanel[i]);
            if (i != Javamon.getPLAYER().getMonsters().length - 1)
                centerSidePanel.add(Box.createHorizontalStrut(8));
        }
        centerSidePanel.add(Box.createHorizontalGlue());

        add(topSidePanel, BorderLayout.NORTH);
        add(leftSidePanel, BorderLayout.WEST);
        add(rightSidePanel, BorderLayout.EAST);
        add(bottomSidePanel, BorderLayout.SOUTH);
        add(centerSidePanel, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }
}
