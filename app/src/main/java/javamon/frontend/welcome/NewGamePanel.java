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
import javamon.frontend.components.TextField;
import javamon.frontend.homebase.HomebasePanel;
import javamon.frontend.components.Button;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class NewGamePanel extends Panel {
    public NewGamePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(9, 9, 11));

        Label label = new Label("New Game", Typography.TITLE);
        Label nameLabel = new Label("Name:", Typography.BODY);

        TextField nameField = new TextField();

        Label chooseMonsterLabel = new Label("Choose 3 monsters:", Typography.BODY);

        JCheckBox[] monsterButtons = new JCheckBox[5];
        JPanel monsterPanel = new JPanel();
        monsterPanel.setLayout(new BoxLayout(monsterPanel, BoxLayout.X_AXIS));
        monsterPanel.setBackground(Colors.BACKGROUND);

        Monster[] monsters = Javamon.getMONSTERS();

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * monsters.length);
            monsterButtons[i] = new JCheckBox(monsters[index].getName());
        monsterButtons[i].setActionCommand(monsters[index].getName());
            monsterButtons[i].setFont(Typography.SMALL);
            monsterButtons[i].setForeground(Colors.TEXT);
            monsterButtons[i].setBackground(Colors.BACKGROUND);
            monsterButtons[i].setAlignmentX(CENTER_ALIGNMENT);

            monsterPanel.add(monsterButtons[i]);
        }

        Row buttonPanel = new Row();

        Button cancelBtn = new Button("Cancel", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button createBtn = new Button("Create", Typography.BUTTON, new ActionListener() {
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
                        if (monstersName[i].equals(Javamon.getMONSTERS()[j].getName())) {
                            monsters[i] = Javamon.getMONSTERS()[j];
                        }
                    }
                }

                Javamon.newGame(name, monsters);

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.addPanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        });

        buttonPanel.add(cancelBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
        buttonPanel.add(createBtn);

        add(Box.createVerticalGlue());
        add(label);
        add(Box.createRigidArea(new Dimension(0, 16)));
        add(nameLabel);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(nameField);
        add(Box.createRigidArea(new Dimension(0, 16)));
        add(chooseMonsterLabel);
        add(monsterPanel);
        add(Box.createRigidArea(new Dimension(0, 16)));
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }

}
