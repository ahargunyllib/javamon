package javamon.frontend.homebase;

import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.backend.exceptions.GameException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class RestoreHpPanel extends Panel {
    public RestoreHpPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Heal Your Monster!", Typography.TITLE);
        add(pageLbl);

        add(SizedBox.height(8));

        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        add(usernameLbl);

        add(SizedBox.height(8));

        Label monsterLbl = new Label("Choose a monster to heal:", Typography.BODY);
        add(monsterLbl);

        add(SizedBox.height(8));

        ButtonGroup monsters = getMonsterColumns();

        add(SizedBox.height(8));

        Row buttonPanel = getHealBtn(homeGUI, monsters);
        add(buttonPanel);
    }

    private ButtonGroup getMonsterColumns() {
        ButtonGroup monsters = new ButtonGroup();
        int monsterCount = Javamon.getPLAYER().getMonsters().length;
        JRadioButton[] monsterColumns = new JRadioButton[monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            Monster monster = Javamon.getPlayerMonster(i);
            monsterColumns[i] = new JRadioButton(monster.getName() + " - "
                    + monster.getElement().toString());
            monsterColumns[i].setActionCommand(monster.getName());
            monsterColumns[i].setFont(Typography.BODY);
            monsterColumns[i].setForeground(Colors.TEXT);
            monsterColumns[i].setBackground(Colors.BACKGROUND);
            monsterColumns[i].setAlignmentX(CENTER_ALIGNMENT);

            monsters.add(monsterColumns[i]);
            add(monsterColumns[i]);
        }
        return monsters;
    }

    private Row getHealBtn(HomeGUI homeGUI, ButtonGroup monsters) {
        Row buttonPanel = new Row();

        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("homebase");
            }
        });

        Button healBtn = new Button("Heal", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monsterName = monsters.getSelection().getActionCommand();

                if (monsterName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select a monster", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Monster monster = null;
                for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
                    if (Javamon.getPLAYER().getMonsters()[i].getName().equals(monsterName)) {
                        monster = Javamon.getPLAYER().getMonsters()[i];
                        break;
                    }
                }

                try {
                    Javamon.getHOMEBASE().restoreHp(monster);
                } catch (GameException err) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), err.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(homeGUI.getFrame(), "Monster evolved!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                homeGUI.setPanel("homebase");
            }
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(SizedBox.width(8));
        buttonPanel.add(healBtn);
        return buttonPanel;
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}