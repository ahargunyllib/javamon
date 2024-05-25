package javamon.frontend.homebase;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.frontend.styles.Colors;
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
        Column rightSidePanel = getRightSidePanel(homeGUI);
        Column leftSidePanel = getLeftSidePanel(homeGUI);
        Row centerSidePanel = getCenterSidePanel();

        add(topSidePanel, BorderLayout.NORTH);
        add(rightSidePanel, BorderLayout.EAST);
        add(leftSidePanel, BorderLayout.WEST);
        add(bottomSidePanel, BorderLayout.SOUTH);
        add(centerSidePanel, BorderLayout.CENTER);
    }

    private Row getCenterSidePanel() {
        int monsterCount = Javamon.getPLAYER().getMonsters().length;
        Column[] monstersPanel = new Column[monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            Monster monster = Javamon.getPlayerMonster(i);

            ImageIcon icon = new ImageIcon(String.format("assets/images/pokemon/%s.jpg", monster.getName()));
            int newWidth = 96; 
            int newHeight = 96; 
            Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            JLabel iconLabel = new JLabel(resizedIcon);
            Label nameLabel = new Label("Name: " + monster.getName(), "jua", 16f, 0, Colors.TRANSPARENT, Color.BLACK);
            Label elementLabel = new Label("Element: " + monster.getElement(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.BLACK);
            Label levelLabel = new Label("Level: " + monster.getLevel(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.BLACK);
            Label hpLabel = new Label("HP: " + monster.getCurrHp() + "/" + monster.getMaxHp(), "jua", 16f, 0,
                    Colors.TRANSPARENT, Color.BLACK);
            Label expLabel = new Label("EXP: " + monster.getExp(), "jua", 16f, 0, Colors.TRANSPARENT, Color.BLACK);
            Label attackPowerLabel = new Label("Attack Power: " + monster.getAttackPower(), "jua", 16f, 0,
                    Colors.TRANSPARENT, Color.BLACK);
            Label defenseLabel = new Label("Defense: " + monster.getDefense(), "jua", 16f, 0, Colors.TRANSPARENT, Color.BLACK);

            iconLabel.setAlignmentX(CENTER_ALIGNMENT);
            nameLabel.setAlignmentX(CENTER_ALIGNMENT);
            elementLabel.setAlignmentX(CENTER_ALIGNMENT);
            levelLabel.setAlignmentX(CENTER_ALIGNMENT);

            Column monsterColumn = new Column();
            monsterColumn.setAlignmentX(CENTER_ALIGNMENT);

            Column monsterInfo = new Column();
            monsterInfo.setBackground(Colors.NEWGAME_ACCENT);
            monsterInfo.add(SizedBox.height(32));
            monsterInfo.add(iconLabel);
            monsterInfo.add(SizedBox.height(8));
            monsterInfo.add(nameLabel);
            monsterInfo.add(elementLabel);
            monsterInfo.add(levelLabel);
            monsterInfo.add(hpLabel);
            monsterInfo.add(expLabel);
            monsterInfo.add(attackPowerLabel);
            monsterInfo.add(defenseLabel);
            monsterInfo.add(SizedBox.height(32));

            Row monsterRow = new Row();
            monsterRow.add(Box.createHorizontalGlue());
            monsterRow.add(monsterInfo);
            monsterRow.add(Box.createHorizontalGlue());


            monsterColumn.add(Box.createVerticalGlue());
            monsterColumn.add(monsterRow);
            monsterColumn.add(Box.createVerticalGlue());

            monstersPanel[i] = monsterColumn;
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
        Button dungeonBtn = new Button("Dungeon Map", "Inter-Bold", 16f, Color.WHITE, Color.black, goToDungeon(homeGUI));

        Column rightSidePanel = new Column();
        rightSidePanel.add(Box.createVerticalGlue());
        rightSidePanel.add(dungeonBtn);
        rightSidePanel.add(Box.createVerticalGlue());
        return rightSidePanel;
    }

    private ActionListener goToDungeon(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        };
    }

    private Column getLeftSidePanel(HomeGUI homeGUI) {
        Button shopBtn = new Button("Shop", "Inter-Bold", 16f, Color.WHITE, Color.black, goToShop(homeGUI));
        Column leftSidePanel = new Column();
        leftSidePanel.add(Box.createVerticalGlue());
        leftSidePanel.add(shopBtn);
        leftSidePanel.add(Box.createVerticalGlue());
        return leftSidePanel;
    }

    private ActionListener goToShop(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopPanel shopPanel = new ShopPanel(homeGUI);
                homeGUI.addPanel("shop", shopPanel);
                homeGUI.setPanel("shop");
            }
        };
    }

    private Column getTopSidePanel() {
        Button backBtn = new Button("Back", "Inter-Bold", 16f, Color.WHITE, Color.black, back());
        
        Label label = new Label("Homebase", "jua", 32f, 8, Color.WHITE, Color.BLACK);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), "jua", 16f, 8, Color.WHITE, Color.BLACK);

        Row buttonRow = new Row();
        buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        buttonRow.add(backBtn);

        Column topSidePanel = new Column();
        topSidePanel.add(buttonRow);
        topSidePanel.add(SizedBox.height(32));
        topSidePanel.add(label);
        topSidePanel.add(SizedBox.height(8));
        topSidePanel.add(usernameLbl);
        return topSidePanel;
    }

    private ActionListener back() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        };
    }

    private Row getBottomSidePanel(HomeGUI homeGUI) {
        Button restoreHpBtn = new Button("Restore Hp", "Inter-Bold", 16f, Color.WHITE, Color.black, restoreHp(homeGUI));
        Button evolveBtn = new Button("Evolve", "Inter-Bold", 16f, Color.WHITE, Color.black, evolve(homeGUI));
        Button levelUpBtn = new Button("Level Up", "Inter-Bold", 16f, Color.WHITE, Color.black, levelUp(homeGUI));
        Button saveMonsterBtn = new Button("Save Monster", "Inter-Bold", 16f, Color.WHITE, Color.black, saveMonster(homeGUI));
        Button restoreMonsterBtn = new Button("Restore Monster", "Inter-Bold", 16f, Color.WHITE, Color.black, restoreMonster(homeGUI));

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

    private ActionListener restoreMonster(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestoreMonsterPanel restoreMonsterPanel = new RestoreMonsterPanel(homeGUI);
                homeGUI.addPanel("restoreMonster", restoreMonsterPanel);
                homeGUI.setPanel("restoreMonster");
            }
        };
    }

    private ActionListener saveMonster(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveMonsterPanel saveMonsterPanel = new SaveMonsterPanel(homeGUI);
                homeGUI.addPanel("saveMonster", saveMonsterPanel);
                homeGUI.setPanel("saveMonster");
            }
        };
    }

    private ActionListener levelUp(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelUpPanel levelUpPanel = new LevelUpPanel(homeGUI);
                homeGUI.addPanel("levelUp", levelUpPanel);
                homeGUI.setPanel("levelUp");
            }
        };
    }

    private ActionListener evolve(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvolvePanel evolvePanel = new EvolvePanel(homeGUI);
                homeGUI.addPanel("evolve", evolvePanel);
                homeGUI.setPanel("evolve");
            }
        };
    }

    private ActionListener restoreHp(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestoreHpPanel restoreHpPanel = new RestoreHpPanel(homeGUI);
                homeGUI.addPanel("restoreHp", restoreHpPanel);
                homeGUI.setPanel("restoreHp");
            }
        };
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
