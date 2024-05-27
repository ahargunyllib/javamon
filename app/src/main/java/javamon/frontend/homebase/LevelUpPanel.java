package javamon.frontend.homebase;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.backend.exceptions.GameException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.styles.Colors;

public class LevelUpPanel extends Panel {
    public LevelUpPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.LEVELUP);

        Row header = new Row();

        Button backBtn = new Button("Back", "Inter-Bold", 16f, Colors.LEVELUP_ACCENT, Color.WHITE, back(homeGUI));
        Label pageLbl = new Label("Level Up", "jua", 32f, 8, Colors.LEVELUP_ACCENT, Color.WHITE);
        Label goldLbl = new Label(Javamon.getPLAYER().getGold() + " gold", "jua", 16f, 8, Colors.LEVELUP_ACCENT, Color.WHITE);
        
        header.add(backBtn);
        header.add(Box.createHorizontalGlue());
        header.add(goldLbl);

        Label chooseMonsterLabel = new Label("Choose Your Pokemon", "jua", 20f, 8, Colors.LEVELUP_ACCENT, Color.WHITE);
        JRadioButton[] monsterButtons = new JRadioButton[Javamon.getPlayerMonsters().length];
        ButtonGroup monsterGroup = new ButtonGroup();
        Row monsterPanel = getMonsters(monsterButtons, monsterGroup);
        Button levelUpBtn = new Button("Level Up", "Inter-Bold", 20f, Colors.LEVELUP_ACCENT, Color.WHITE,
                levelUp(homeGUI, monsterButtons));


                add(SizedBox.height(8));
        add(header);
        add(SizedBox.height(16));
        add(pageLbl);
        add(SizedBox.height(8));
        add(chooseMonsterLabel);
        add(SizedBox.height(8));
        add(monsterPanel);
        add(SizedBox.height(16));
        add(levelUpBtn);
        add(Box.createVerticalGlue());
    }

    private ActionListener levelUp(HomeGUI homeGUI, JRadioButton[] monsterButtons) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monsterName = null;
                for (JRadioButton radioButton : monsterButtons) {
                    if (radioButton.isSelected()) {
                        monsterName = radioButton.getActionCommand();
                        break;
                    }
                }

                if (monsterName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select a monster", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Monster monster = null;
                int monsterCount = Javamon.getPLAYER().getMonsters().length;
                for (int i = 0; i < monsterCount; i++) {
                    Monster dummyMonster = Javamon.getPlayerMonster(i);
                    if (dummyMonster.getName().equals(monsterName)) {
                        monster = dummyMonster;
                        break;
                    }
                }

                try {
                    Javamon.getHOMEBASE().levelUp(monster);
                } catch (GameException err) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), err.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(homeGUI.getFrame(), "Monster evolved!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.replacePanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        };
    }

    private Row getMonsters(JRadioButton[] monsterButtons, ButtonGroup monsterGroup) {
        Row monsterPanel = new Row();

        Monster[] monsters = Javamon.getPlayerMonsters();

        int cnt = 0;
        for (Monster monster : monsters) {
            ImageIcon icon = new ImageIcon(String.format("assets/images/pokemon/%s.jpg", monster.getName()));
            int newWidth = 96;
            int newHeight = 96;
            Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            ImageIcon selectedIcon = new ImageIcon(
                    String.format("assets/images/pokemon/%s-Selected.jpg", monster.getName()));
            int newSelectedWidth = 96;
            int newSelectedHeight = 96;
            Image scaledSelectedImage = selectedIcon.getImage().getScaledInstance(newSelectedWidth, newSelectedHeight,
                    Image.SCALE_SMOOTH);
            ImageIcon resizedSelectedIcon = new ImageIcon(scaledSelectedImage);

            JRadioButton radioButton = new JRadioButton();
            radioButton.setActionCommand(monster.getName());
            radioButton.setIcon(resizedIcon);
            radioButton.setSelectedIcon(resizedSelectedIcon);
            radioButton.setAlignmentX(CENTER_ALIGNMENT);

            Column monsterInfo = new Column();

            Label nameLabel = new Label("Name: " + monster.getName(), "jua", 16f, 0, Colors.TRANSPARENT, Color.WHITE);
            Label elementLabel = new Label("Element: " + monster.getElement(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.WHITE);
            Label levelLabel = new Label("Level: " + monster.getLevel(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.WHITE);

            nameLabel.setAlignmentX(LEFT_ALIGNMENT);
            elementLabel.setAlignmentX(LEFT_ALIGNMENT);
            levelLabel.setAlignmentX(LEFT_ALIGNMENT);

            monsterInfo.add(nameLabel);
            monsterInfo.add(elementLabel);
            monsterInfo.add(levelLabel);

            Column monsterColumn = new Column();
            monsterColumn.setBackground(Colors.LEVELUP_ACCENT);

            monsterColumn.add(SizedBox.height(8));
            monsterColumn.add(radioButton);
            monsterColumn.add(SizedBox.height(8));
            monsterColumn.add(monsterInfo);
            monsterColumn.add(SizedBox.height(8));

            monsterPanel.add(SizedBox.width(32));
            monsterPanel.add(monsterColumn);
            monsterPanel.add(SizedBox.width(32));

            monsterButtons[cnt++] = radioButton;

            monsterGroup.add(radioButton);
        }

        return monsterPanel;
    }

    private ActionListener back(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.replacePanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        };
    }
}
