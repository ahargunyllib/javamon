package javamon.frontend.homebase;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Element;
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

public class EvolvePanel extends Panel {
    public EvolvePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.EVOLVE);

        Row header = new Row();

        Button backBtn = new Button("Back", "Inter-Bold", 16f, Colors.EVOLVE_ACCENT, Color.WHITE, back(homeGUI));
        Label pageLbl = new Label("Evolve Monster", "jua", 32f, 8, Colors.EVOLVE_ACCENT, Color.WHITE);
        Label goldLbl = new Label(Javamon.getPLAYER().getGold() + " gold", "jua", 16f, 8, Colors.EVOLVE_ACCENT, Color.WHITE);

        header.add(backBtn);
        header.add(Box.createHorizontalGlue());
        header.add(goldLbl);

        Label chooseMonsterLabel = new Label("Choose Your Pokemon", "jua", 20f, 8, Colors.EVOLVE_ACCENT, Color.WHITE);
        Label chooseElementLabel = new Label("Choose Element", "jua", 20f, 8, Colors.EVOLVE_ACCENT, Color.WHITE);
        JRadioButton[] monsterButtons = new JRadioButton[Javamon.getPlayerMonsters().length];
        ButtonGroup monsterGroup = new ButtonGroup();
        Row monsterPanel = getMonsters(monsterButtons, monsterGroup);
        JRadioButton[] elementalButtons = new JRadioButton[Element.values().length];
        ButtonGroup elementalGroup = new ButtonGroup();
        Row elementalPanel = getElements(elementalButtons, elementalGroup);
        Button evolveBtn = new Button("Evolve", "Inter-Bold", 20f, Colors.EVOLVE_ACCENT, Color.WHITE,
                evolve(homeGUI, monsterButtons, elementalButtons));

        add(SizedBox.height(8));
        add(header);
        add(SizedBox.height(16));
        add(pageLbl);
        add(SizedBox.height(8));
        add(chooseMonsterLabel);
        add(SizedBox.height(8));
        add(monsterPanel);
        add(SizedBox.height(16));
        add(chooseElementLabel);
        add(SizedBox.height(8));
        add(elementalPanel);
        add(SizedBox.height(16));
        add(evolveBtn);
        add(Box.createVerticalGlue());
    }

    private Row getElements(JRadioButton[] elementalButtons, ButtonGroup elementalGroup) {
        Row elementPanel = new Row();

        Element[] elements = Element.values();

        int cnt = 0;

        for (Element element : elements) {
            ImageIcon icon = new ImageIcon(String.format("assets/images/elemental/%s.jpg", element.toString()));
            int newWidth = 48;
            int newHeight = 48;
            Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            ImageIcon selectedIcon = new ImageIcon(
                    String.format("assets/images/elemental/%s-Selected.jpg", element.toString()));
            int newSelectedWidth = 48;
            int newSelectedHeight = 48;
            Image scaledSelectedImage = selectedIcon.getImage().getScaledInstance(newSelectedWidth, newSelectedHeight,
                    Image.SCALE_SMOOTH);
            ImageIcon resizedSelectedIcon = new ImageIcon(scaledSelectedImage);

            JRadioButton radioButton = new JRadioButton();
            radioButton.setActionCommand(element.toString());
            radioButton.setIcon(resizedIcon);
            radioButton.setSelectedIcon(resizedSelectedIcon);
            radioButton.setAlignmentX(CENTER_ALIGNMENT);

            Column elementInfo = new Column();
            elementInfo.setBorder(BorderFactory.createLineBorder(Colors.EVOLVE_ACCENT, 4));

            Label nameLabel = new Label(element.toString(), "jua", 16f, 0, Colors.TRANSPARENT, Color.WHITE);

            nameLabel.setAlignmentX(LEFT_ALIGNMENT);

            elementInfo.add(nameLabel);

            Column elementColumn = new Column();
            elementColumn.setBackground(Colors.EVOLVE_ACCENT);

            elementColumn.add(SizedBox.height(8));
            elementColumn.add(radioButton);
            elementColumn.add(SizedBox.height(8));
            elementColumn.add(elementInfo);
            elementColumn.add(SizedBox.height(8));

            elementPanel.add(SizedBox.width(32));
            elementPanel.add(elementColumn);
            elementPanel.add(SizedBox.width(32));

            elementalButtons[cnt++] = radioButton;

            elementalGroup.add(radioButton);
        }

        return elementPanel;
    }

    private ActionListener evolve(HomeGUI homeGUI, JRadioButton[] monsterButtons, JRadioButton[] elementalButtons) {
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

                String elementName = null;
                for (JRadioButton radioButton : elementalButtons) {
                    if (radioButton.isSelected()) {
                        elementName = radioButton.getActionCommand();
                        break;
                    }
                }

                if (monsterName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select a monster", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (elementName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select an element", "Error",
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

                Element element = Element.valueOf(elementName);

                try {
                    Javamon.getHOMEBASE().evolve(monster, element);
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
            monsterInfo.setBorder(BorderFactory.createLineBorder(Colors.EVOLVE_ACCENT, 4));

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
            monsterColumn.setBackground(Colors.EVOLVE_ACCENT);

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
