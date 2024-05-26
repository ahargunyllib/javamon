package javamon.frontend.homebase;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.styles.Colors;

public class RestoreMonsterPanel extends Panel {
    public RestoreMonsterPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.RESTORE);

        Row header = new Row();

        Button backBtn = new Button("Back", "Inter-Bold", 16f, Color.WHITE, Color.BLACK, back(homeGUI));
        Label pageLbl = new Label("Restore Monsters", "jua", 32f, 8, Color.WHITE, Color.BLACK);
        Label goldLbl = new Label(Javamon.getPLAYER().getGold() + " gold", "jua", 16f, 8, Color.WHITE, Color.BLACK);
        Label chooseMonsterLabel = new Label("Choose Your Pokemon", "jua", 32f, 8, Color.WHITE, Color.BLACK);
        JCheckBox[] monsterButtons = new JCheckBox[Javamon.getHomebaseMonsters().length];
        Row monsterPanel = getMonsters(monsterButtons);
        Button restoreBtn = new Button("Restore", "Inter-Bold", 16f, Color.WHITE, Color.BLACK,
                restore(homeGUI, monsterButtons));

        header.add(backBtn);
        header.add(Box.createHorizontalGlue());
        header.add(goldLbl);

        add(header);
        add(SizedBox.height(32));
        add(pageLbl);
        add(SizedBox.height(8));
        add(chooseMonsterLabel);
        add(SizedBox.height(8));
        add(monsterPanel);
        add(SizedBox.height(8));
        add(restoreBtn);
    }

    private ActionListener restore(HomeGUI homeGUI, JCheckBox[] monsterButtons) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                for (JCheckBox checkbox : monsterButtons) {
                    if (checkbox.isSelected()) {
                        count++;
                    }
                }
                
                if (count == 0) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select exactly one monster", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] selectedMonsters = new String[count];
                int index = 0;
                for (JCheckBox checkbox : monsterButtons) {
                    if (checkbox.isSelected()) {
                        selectedMonsters[index++] = checkbox.getActionCommand();
                    }
                }

                Monster[] monsters = new Monster[count];
                for (int i = 0; i < count; i++) {
                    for (Monster monster : Javamon.getHomebaseMonsters()) {
                        if (monster.getName().equals(selectedMonsters[i])) {
                            monsters[i] = monster;
                            break;
                        }
                    }
                }

                Javamon.getHOMEBASE().restoreMonster(monsters);

                JOptionPane.showMessageDialog(homeGUI.getFrame(), "Monster saved!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.replacePanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        };
    }

    private Row getMonsters(JCheckBox[] monsterButtons) {
        Row monsterPanel = new Row();

        Monster[] monsters = Javamon.getHomebaseMonsters();

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

            JCheckBox checkbox = new JCheckBox();
            checkbox.setActionCommand(monster.getName());
            checkbox.setIcon(resizedIcon);
            checkbox.setSelectedIcon(resizedSelectedIcon);
            checkbox.setAlignmentX(CENTER_ALIGNMENT);

            Column monsterInfo = new Column();
            monsterInfo.setBackground(Color.WHITE);
            monsterInfo.setBorder(BorderFactory.createLineBorder(Colors.RESTOREHP_ACCENT, 4));

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
            monsterColumn.setBackground(Colors.RESTOREHP_ACCENT);

            monsterColumn.add(SizedBox.height(8));
            monsterColumn.add(checkbox);
            monsterColumn.add(SizedBox.height(8));
            monsterColumn.add(monsterInfo);
            monsterColumn.add(SizedBox.height(8));

            monsterPanel.add(SizedBox.width(32));
            monsterPanel.add(monsterColumn);
            monsterPanel.add(SizedBox.width(32));

            monsterButtons[cnt++] = checkbox;
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
