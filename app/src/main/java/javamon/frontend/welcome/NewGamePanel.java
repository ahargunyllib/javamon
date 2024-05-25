package javamon.frontend.welcome;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.components.TextField;
import javamon.frontend.homebase.HomebasePanel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.styles.Colors;

public class NewGamePanel extends Panel {
    public NewGamePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.NEWGAME);

        Label nameLabel = new Label("USERNAME:", "jua", 16f, 0, Colors.TRANSPARENT, Color.WHITE);
        TextField nameField = new TextField();
        Label chooseMonsterLabel = new Label("Choose Your Pokemon", "jua", 32f, 8, Color.WHITE, Color.BLACK);
        JCheckBox[] monsterButtons = new JCheckBox[5];
        Row monsterPanel = getMonsters(monsterButtons);
        Button backBtn = new Button("Back", "Inter-Bold", 16f, Color.WHITE, Color.black, cancelGame(homeGUI));
        Row buttonRow = new Row();
        Button createBtn = new Button("START", "Inter-Bold", 16f, Color.WHITE, Color.black,
                createGame(homeGUI, nameField, monsterButtons));

        buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, getHeight()));
        buttonRow.add(backBtn);

        add(SizedBox.height(8));
        add(buttonRow);
        add(SizedBox.height(32));
        add(nameLabel);
        add(nameField);
        add(SizedBox.height(32));
        add(chooseMonsterLabel);
        add(SizedBox.height(16));
        add(monsterPanel);
        add(SizedBox.height(16));
        add(createBtn);
        add(Box.createVerticalGlue());
    }

    private Row getMonsters(JCheckBox[] monsterButtons) {
        Row monsterPanel = new Row();

        boolean[] selected = new boolean[Javamon.getMONSTERS().length];
        int cnt = 0;

        while (cnt < 5) {
            int index = (int) (Math.random() * Javamon.getMONSTERS().length);

            if (selected[index]) {
                continue;
            }

            Monster monster = Javamon.getMonster(index);

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

            JCheckBox checkBox = new JCheckBox();
            checkBox.setActionCommand(monster.getName());
            checkBox.setIcon(resizedIcon);
            checkBox.setSelectedIcon(resizedSelectedIcon);
            checkBox.setAlignmentX(CENTER_ALIGNMENT);

            Column monsterInfo = new Column();
            monsterInfo.setBackground(Color.WHITE);
            monsterInfo.setBorder(BorderFactory.createLineBorder(Colors.NEWGAME_ACCENT, 4));

            Label nameLabel = new Label("Name: " + monster.getName(), "jua", 16f, 0, Colors.TRANSPARENT, Color.BLACK);
            Label elementLabel = new Label("Element: " + monster.getElement(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.BLACK);
            Label levelLabel = new Label("Level: " + monster.getLevel(), "jua", 16f, 0, Colors.TRANSPARENT,
                    Color.BLACK);

            nameLabel.setAlignmentX(LEFT_ALIGNMENT);
            elementLabel.setAlignmentX(LEFT_ALIGNMENT);
            levelLabel.setAlignmentX(LEFT_ALIGNMENT);

            monsterInfo.add(nameLabel);
            monsterInfo.add(elementLabel);
            monsterInfo.add(levelLabel);

            Column monsterColumn = new Column();
            monsterColumn.setBackground(Colors.NEWGAME_ACCENT);

            monsterColumn.add(SizedBox.height(8));
            monsterColumn.add(checkBox);
            monsterColumn.add(SizedBox.height(8));
            monsterColumn.add(monsterInfo);
            monsterColumn.add(SizedBox.height(8));

            monsterPanel.add(SizedBox.width(32));
            monsterPanel.add(monsterColumn);
            monsterPanel.add(SizedBox.width(32));
            

            monsterButtons[cnt] = checkBox;

            selected[index] = true;
            cnt++;
        }
        return monsterPanel;
    }

    private ActionListener cancelGame(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        };
    }

    private ActionListener createGame(HomeGUI homeGUI, TextField nameField, JCheckBox[] monsterButtons) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Name cannot be empty!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (monsterButtons[i].isSelected()) {
                        count++;
                    }
                }

                if (count != 3) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Choose three monster!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] monstersName = new String[3];
                int index = 0;
                for (int i = 0; i < 5; i++) {
                    if (monsterButtons[i].isSelected()) {
                        monstersName[index] = monsterButtons[i].getActionCommand();
                        index++;
                    }
                }

                Monster[] monsters = new Monster[3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < Javamon.getMONSTERS().length; j++) {
                        if (monstersName[i].equals(Javamon.getMonster(j).getName())) {
                            monsters[i] = Javamon.getMonster(j);
                        }
                    }
                }

                Javamon.newGame(name, monsters);

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.addPanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        };
    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }

}
