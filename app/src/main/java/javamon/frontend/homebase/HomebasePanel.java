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
import javamon.frontend.dungeon.MapPanel;

public class HomebasePanel extends Panel {
    public HomebasePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.BACKGROUND);

        Button backBtn = new Button("Back", "Inter-Bold", 20f, Colors.WELCOME, Color.WHITE, back());
        Label goldLbl = new Label(Javamon.getPLAYER().getGold() + " gold", "jua", 16f, 8, Colors.WELCOME, Color.WHITE);
        Label label = new Label("Homebase", "jua", 32f, 8, Colors.WELCOME, Color.WHITE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), "jua", 20f, 8, Colors.WELCOME, Color.WHITE);
        
        Row header = new Row();
        header.add(backBtn);
        header.add(Box.createHorizontalGlue());
        header.add(goldLbl);

        Row centerRow = new Row();
        Button shopBtn = new Button("Shop", "Inter-Bold", 20f, Colors.WELCOME, Color.WHITE, goToShop(homeGUI));
        Button dungeonBtn = new Button("Dungeon", "Inter-Bold", 20f, Colors.WELCOME, Color.WHITE,
                goToDungeon(homeGUI, this));

        shopBtn.setSize(150, 50);
        shopBtn.setPreferredSize(new Dimension(150, 50));
        shopBtn.setMaximumSize(new Dimension(150, 50));
        shopBtn.setMinimumSize(new Dimension(150, 50));  
        dungeonBtn.setSize(150, 50);
        dungeonBtn.setPreferredSize(new Dimension(150, 50));
        dungeonBtn.setMaximumSize(new Dimension(150, 50));
        dungeonBtn.setMinimumSize(new Dimension(150, 50));

        Row monstersPanel = getMonsters();

        centerRow.add(shopBtn);
        centerRow.add(Box.createHorizontalGlue());
        centerRow.add(monstersPanel);
        centerRow.add(Box.createHorizontalGlue());
        centerRow.add(dungeonBtn);

        Button restoreHpBtn = new Button("Restore Hp", "Inter-Bold", 16f, Colors.WELCOME, Color.WHITE, restoreHp(homeGUI));
        Button evolveBtn = new Button("Evolve", "Inter-Bold", 16f, Colors.WELCOME, Color.WHITE, evolve(homeGUI));
        Button levelUpBtn = new Button("Level Up", "Inter-Bold", 16f, Colors.WELCOME, Color.WHITE, levelUp(homeGUI));
        Button saveMonsterBtn = new Button("Save Monster", "Inter-Bold", 16f, Colors.WELCOME, Color.WHITE, saveMonster(homeGUI));
        Button restoreMonsterBtn = new Button("Restore Monster", "Inter-Bold", 16f, Colors.WELCOME, Color.WHITE, restoreMonster(homeGUI));

        Row buttonRow = new Row();
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(restoreHpBtn);
        buttonRow.add(SizedBox.width(8));
        buttonRow.add(evolveBtn);
        buttonRow.add(SizedBox.width(8));
        buttonRow.add(levelUpBtn);
        buttonRow.add(SizedBox.width(8));
        buttonRow.add(saveMonsterBtn);
        buttonRow.add(SizedBox.width(8));
        buttonRow.add(restoreMonsterBtn);
        buttonRow.add(Box.createHorizontalGlue());

        add(SizedBox.height(8));
        add(header);
        add(SizedBox.height(16));
        add(label);
        add(SizedBox.height(8));
        add(usernameLbl);
        add(Box.createVerticalGlue());
        add(centerRow);
        add(SizedBox.height(16));
        add(buttonRow);
        add(Box.createVerticalGlue());
    }
    
    private Row getMonsters() {
        Row monsterPanel = new Row();

        Monster[] monsters = Javamon.getPlayerMonsters();

        for (Monster monster : monsters) {
            ImageIcon icon = new ImageIcon(String.format("assets/images/pokemon/%s.jpg", monster.getName()));
            int newWidth = 96;
            int newHeight = 96;
            Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            JLabel iconLabel = new JLabel(resizedIcon);
            Label nameLabel = new Label("Name: " + monster.getName(), "jua", 16f, 0, Colors.TRANSPARENT, Color.WHITE);
            Label elementLabel = new Label("Element: " + monster.getElement(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.WHITE);
            Label levelLabel = new Label("Level: " + monster.getLevel(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.WHITE);
            Label hpLabel = new Label("HP: " + monster.getCurrHp() + "/" + monster.getMaxHp(), "jua", 16f, 0,
                    Colors.TRANSPARENT, Color.WHITE);
            Label expLabel = new Label("EXP: " + monster.getExp(), "jua", 16f, 0, Colors.TRANSPARENT, Color.WHITE);
            Label attackPowerLabel = new Label("Attack Power: " + monster.getAttackPower(), "jua", 16f, 0,
                    Colors.TRANSPARENT, Color.WHITE);
            Label defenseLabel = new Label("Defense: " + monster.getDefense(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.WHITE);

            iconLabel.setAlignmentX(CENTER_ALIGNMENT);
            nameLabel.setAlignmentX(CENTER_ALIGNMENT);
            elementLabel.setAlignmentX(CENTER_ALIGNMENT);
            levelLabel.setAlignmentX(CENTER_ALIGNMENT);
            hpLabel.setAlignmentX(CENTER_ALIGNMENT);
            expLabel.setAlignmentX(CENTER_ALIGNMENT);
            attackPowerLabel.setAlignmentX(CENTER_ALIGNMENT);
            defenseLabel.setAlignmentX(CENTER_ALIGNMENT);

            Column monsterInfo = new Column();
            monsterInfo.setAlignmentX(CENTER_ALIGNMENT);
            monsterInfo.setBackground(Colors.WELCOME);
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

            monsterPanel.add(SizedBox.width(32));
            monsterPanel.add(monsterInfo);
            monsterPanel.add(SizedBox.width(32));
        }

        return monsterPanel;
    }

    private ActionListener goToDungeon(HomeGUI homeGUI, Panel panel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Javamon.getPlayerMonsters().length > 3) {
                    JOptionPane.showMessageDialog(panel, "You can only bring maximum 3 monsters to the dungeon", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                MapPanel mapPanel = new MapPanel(homeGUI);
                homeGUI.addPanel("map", mapPanel);
                homeGUI.setPanel("map");
            }
        };
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

    private ActionListener back() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        };
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/homebase_bg.jpg");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
