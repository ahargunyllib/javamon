package javamon.frontend.homebase;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;

public class HomebasePanel extends Panel {
    public HomebasePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BorderLayout());
        setBackground(Colors.BACKGROUND);

        Column topSidePanel = getTopSidePanel();
        Row bottomSidePanel = getBottomSidePanel(homeGUI);
        Row leftSidePanel = getLeftSidePanel(homeGUI);
        Column rightSidePanel = getRightSidePanel(homeGUI);
        Row centerSidePanel = getCenterSidePanel();

        add(topSidePanel, BorderLayout.NORTH);
        add(leftSidePanel, BorderLayout.WEST);
        add(rightSidePanel, BorderLayout.EAST);
        add(bottomSidePanel, BorderLayout.SOUTH);
        add(centerSidePanel, BorderLayout.CENTER);
    }

    private Row getCenterSidePanel() {
        int monsterCount = Javamon.getPLAYER().getMonsters().length;
        Column[] monstersPanel = new Column[monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            Monster monster = Javamon.getPlayerMonster(i);

            monstersPanel[i] = new Column();
            monstersPanel[i].add(Box.createVerticalGlue());
            monstersPanel[i].add(new Label(monster.getName(), Typography.LABEL));
            monstersPanel[i].add(SizedBox.height(8));
            monstersPanel[i].add(new Label("HP: " + monster.getCurrHp() + "/"
                    + monster.getMaxHp(), Typography.LABEL));
            monstersPanel[i].add(Box.createVerticalGlue());
        }

        Row centerSidePanel = new Row();
        centerSidePanel.add(Box.createHorizontalGlue());
        
        for (int i = 0; i < monsterCount; i++) {
            centerSidePanel.add(monstersPanel[i]);
            if (i != monsterCount - 1)
                centerSidePanel.add(SizedBox.width(8));
        }

        centerSidePanel.add(Box.createHorizontalGlue());
        return centerSidePanel;
    }

    private Column getRightSidePanel(HomeGUI homeGUI) {
        Button dungeonBtn = new Button("Go to Dungeon", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
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
        Column rightSidePanel = new Column();
        rightSidePanel.add(Box.createVerticalGlue());
        rightSidePanel.add(dungeonBtn);
        rightSidePanel.add(SizedBox.height(8));
        rightSidePanel.add(shopBtn);
        rightSidePanel.add(Box.createVerticalGlue());
        return rightSidePanel;
    }

    private Row getLeftSidePanel(HomeGUI homeGUI) {
        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Row leftSidePanel = new Row();
        leftSidePanel.add(Box.createVerticalGlue());
        leftSidePanel.add(backBtn);
        leftSidePanel.add(Box.createVerticalGlue());
        return leftSidePanel;
    }

    private Column getTopSidePanel() {
        Label label = new Label("Homebase", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);

        Column topSidePanel = new Column();
        topSidePanel.setAlignmentX(CENTER_ALIGNMENT);
        topSidePanel.add(label);
        topSidePanel.add(SizedBox.height(8));
        topSidePanel.add(usernameLbl);
        return topSidePanel;
    }

    private Row getBottomSidePanel(HomeGUI homeGUI) {
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

        Row bottomSidePanel = new Row();
        bottomSidePanel.add(Box.createHorizontalGlue());
        bottomSidePanel.add(restoreHpBtn);
        bottomSidePanel.add(SizedBox.width(8));
        bottomSidePanel.add(evolveBtn);
        bottomSidePanel.add(SizedBox.width(8));
        bottomSidePanel.add(levelUpBtn);
        bottomSidePanel.add(SizedBox.width(8));
        bottomSidePanel.add(saveMonsterBtn);
        bottomSidePanel.add(SizedBox.width(8));
        bottomSidePanel.add(restoreMonsterBtn);
        bottomSidePanel.add(Box.createHorizontalGlue());
        return bottomSidePanel;
    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/homebase_bg.jpg");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
