package javamon.frontend.homebase;

import java.awt.event.*;

import javax.swing.Box;
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
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class SaveMonsterPanel extends Panel {
    public SaveMonsterPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Save Your Monsters!", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Label monsterLbl = new Label("Choose monsters to save:", Typography.BODY);

        add(pageLbl);
        add(Box.createVerticalStrut(8));
        add(usernameLbl);
        add(Box.createVerticalStrut(8));
        add(monsterLbl);
        add(Box.createVerticalStrut(8));

        if (Javamon.getPLAYER().getMonsters().length == 0) {
            Label noMonsterLbl = new Label("No monsters to save", Typography.BODY);
            add(noMonsterLbl);

            add(Box.createVerticalStrut(8));
            Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    homeGUI.setPanel("homebase");
                }
            });
            add(backBtn);
        } else {

            Row monsterPanel = new Row();
            JCheckBox[] monsterColumns = new JCheckBox[Javamon.getPLAYER().getMonsters().length];
            for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
                monsterColumns[i] = new JCheckBox(Javamon.getPLAYER().getMonsters()[i].getName() + " - "
                        + Javamon.getPLAYER().getMonsters()[i].getElement().toString());
                monsterColumns[i].setActionCommand(Javamon.getPLAYER().getMonsters()[i].getName());
                monsterColumns[i].setFont(Typography.BODY);
                monsterColumns[i].setForeground(Colors.TEXT);
                monsterColumns[i].setBackground(Colors.BACKGROUND);
                monsterColumns[i].setAlignmentX(CENTER_ALIGNMENT);

                monsterPanel.add(monsterColumns[i]);
            }

            add(monsterPanel);
            add(Box.createVerticalStrut(8));

            Column buttonPanel = new Column();

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
                    for (int i = 0; i < count; i++) {
                        for (int j = 0; j < Javamon.getPLAYER().getMonsters().length; j++) {
                            if (monsterNames[i].equals(Javamon.getPLAYER().getMonsters()[j].getName())) {
                                monsters[i] = Javamon.getPLAYER().getMonsters()[j];
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
            buttonPanel.add(Box.createHorizontalStrut(8));
            buttonPanel.add(saveBtn);

            add(buttonPanel);
        }
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}
