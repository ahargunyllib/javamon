package javamon.frontend.homebase;

import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

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
import javamon.frontend.styles.Typography;

public class SaveMonsterPanel extends Panel {
    public SaveMonsterPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Save Your Monsters!", Typography.TITLE);
        add(pageLbl);

        add(SizedBox.height(8));

        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        add(usernameLbl);

        add(SizedBox.height(8));

        Label monsterLbl = new Label("Choose monsters to save:", Typography.BODY);
        add(monsterLbl);

        add(SizedBox.height(8));

        if (Javamon.getPlayerMonsters().length == 0) {
            Label noMonsterLbl = new Label("No monsters to save", Typography.BODY);
            add(noMonsterLbl);

            add(SizedBox.height(8));

            Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    homeGUI.setPanel("homebase");
                }
            });
            add(backBtn);
        } else {
            add(SizedBox.height(8));

            JCheckBox[] monsterColumns = getMonsterColumns();
            Row buttonPanel = getSaveBtn(homeGUI, monsterColumns);
            add(buttonPanel);
        }
    }

    private JCheckBox[] getMonsterColumns() {
        Row monsterPanel = new Row();

        int monsterCount = Javamon.getPlayerMonsters().length;
        JCheckBox[] monsterColumns = new JCheckBox[monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            Monster monster = Javamon.getPlayerMonster(i);
            monsterColumns[i] = new JCheckBox(monster.getName() + " - "
                    + monster.getElement().toString());
            monsterColumns[i].setActionCommand(monster.getName());
            monsterColumns[i].setFont(Typography.BODY);
            monsterColumns[i].setForeground(Colors.TEXT);
            monsterColumns[i].setBackground(Colors.BACKGROUND);
            monsterColumns[i].setAlignmentX(CENTER_ALIGNMENT);

            monsterPanel.add(monsterColumns[i]);
        }

        add(monsterPanel);
        return monsterColumns;
    }

    private Row getSaveBtn(HomeGUI homeGUI, JCheckBox[] monsterColumns) {
        Row buttonPanel = new Row();

        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("homebase");
            }
        });

        Button saveBtn = new Button("Save", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                for (int i = 0; i < monsterColumns.length; i++) {
                    if (monsterColumns[i].isSelected()) {
                        count++;
                    }
                }

                if (count == 0) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select at least one monster", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] monsterNames = new String[count];
                int index = 0;
                for (int i = 0; i < monsterColumns.length; i++) {
                    if (monsterColumns[i].isSelected()) {
                        monsterNames[index] = monsterColumns[i].getActionCommand();
                        index++;
                    }
                }

                Monster[] monsters = new Monster[count];
                int monsterCount = Javamon.getPlayerMonsters().length;
                for (int i = 0; i < count; i++) {
                    for (int j = 0; j < monsterCount; j++) {
                        Monster dummyMonster = Javamon.getPlayerMonster(j);
                        if (monsterNames[i].equals(dummyMonster.getName())) {
                            monsters[i] = dummyMonster;
                        }
                    }
                }

                Javamon.getHOMEBASE().saveMonster(monsters);

                JOptionPane.showMessageDialog(homeGUI.getFrame(), "Monster saved!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.replacePanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(SizedBox.width(8));
        buttonPanel.add(saveBtn);
        return buttonPanel;
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}
