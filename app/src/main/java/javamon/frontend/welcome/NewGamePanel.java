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
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class NewGamePanel extends Panel {
    public NewGamePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        Label label = new Label("New Game", Typography.TITLE);
        add(label);

        add(SizedBox.width(16));

        Label nameLabel = new Label("Name:", Typography.BODY);
        add(nameLabel);

        add(SizedBox.height(8));

        TextField nameField = new TextField();
        add(nameField);

        add(SizedBox.width(16));

        Label chooseMonsterLabel = new Label("Choose 3 monsters:", Typography.BODY);
        add(chooseMonsterLabel);

        JCheckBox[] monsterButtons = new JCheckBox[5];
        Row monsterPanel = getMonsters(monsterButtons);
        add(monsterPanel);

        add(SizedBox.height(16));
        
        Row buttonPanel = getCreateBtn(homeGUI, nameField, monsterButtons);
        add(buttonPanel);

        add(Box.createVerticalGlue());
    }

    private Row getMonsters(JCheckBox[] monsterButtons) {
        Row monsterPanel = new Row();

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * Javamon.getMONSTERS().length);
            monsterButtons[i] = new JCheckBox(Javamon.getMonster(index).getName());
            monsterButtons[i].setActionCommand(Javamon.getMonster(index).getName());
            monsterButtons[i].setFont(Typography.SMALL);
            monsterButtons[i].setForeground(Colors.TEXT);
            monsterButtons[i].setBackground(Colors.BACKGROUND);
            monsterButtons[i].setAlignmentX(CENTER_ALIGNMENT);

            monsterPanel.add(monsterButtons[i]);
        }
        return monsterPanel;
    }

    private Row getCreateBtn(HomeGUI homeGUI, TextField nameField, JCheckBox[] monsterButtons) {
        Row buttonPanel = new Row();

        Button cancelBtn = new Button("Cancel", Typography.BUTTON, cancelGame(homeGUI));
        buttonPanel.add(cancelBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        Button createBtn = new Button("Create", Typography.BUTTON, createGame(homeGUI, nameField, monsterButtons));
        buttonPanel.add(createBtn);
        return buttonPanel;
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
