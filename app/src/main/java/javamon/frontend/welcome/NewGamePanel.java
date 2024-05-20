package javamon.frontend.welcome;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.frontend.HomeGUI;
import javamon.frontend.HomebasePanel;
import javamon.frontend.Panel;

public class NewGamePanel extends Panel {
    public NewGamePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(9, 9, 11));

        JLabel label = new JLabel("Create Player");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setForeground(new Color(226, 226, 226));

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setForeground(new Color(226, 226, 226));

        JTextField nameField = new JTextField();
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        nameField.setAlignmentX(CENTER_ALIGNMENT);
        nameField.setForeground(new Color(9, 9, 11));
        nameField.setMaximumSize(new Dimension(200, 30));

        JLabel chooseMonsterLabel = new JLabel("Choose Your Starter Monsters:");
        chooseMonsterLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        chooseMonsterLabel.setForeground(new Color(226, 226, 226));
        chooseMonsterLabel.setAlignmentX(CENTER_ALIGNMENT);

        JCheckBox[] monsterButtons = new JCheckBox[5];
        JPanel monsterPanel = new JPanel();
        monsterPanel.setLayout(new BoxLayout(monsterPanel, BoxLayout.X_AXIS));
        monsterPanel.setBackground(new Color(9, 9, 11));

        Monster[] monsters = Javamon.getMONSTERS();

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * monsters.length);
            monsterButtons[i] = new JCheckBox(monsters[index].getName());
            monsterButtons[i].setActionCommand(monsters[index].getName());
            monsterButtons[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            monsterButtons[i].setForeground(new Color(226, 226, 226));
            monsterButtons[i].setBackground(new Color(9, 9, 11));
            monsterButtons[i].setAlignmentX(CENTER_ALIGNMENT);

            monsterPanel.add(monsterButtons[i]);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(new Color(9, 9, 11));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        cancelBtn.setAlignmentX(CENTER_ALIGNMENT);
        cancelBtn.setMaximumSize(new Dimension(100, 50));
        cancelBtn.setBackground(new Color(226, 226, 226));
        cancelBtn.setForeground(new Color(9, 9, 11));
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton createBtn = new JButton("Create");
        createBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        createBtn.setAlignmentX(CENTER_ALIGNMENT);
        createBtn.setMaximumSize(new Dimension(100, 50));
        createBtn.setBackground(new Color(226, 226, 226));
        createBtn.setForeground(new Color(9, 9, 11));
        createBtn.addActionListener(new ActionListener() {
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
