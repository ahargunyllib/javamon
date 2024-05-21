package javamon.frontend.homebase;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Element;
import javamon.backend.entity.Monster;
import javamon.backend.exceptions.CannotEvolveException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class EvolvePanel extends Panel {
    public EvolvePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Evolve Your Monster!", Typography.TITLE);
        add(pageLbl);

        add(SizedBox.height(8));

        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        add(usernameLbl);

        add(SizedBox.height(8));

        Label monsterLbl = new Label("Choose a monster to evolve:", Typography.BODY);
        add(monsterLbl);

        add(SizedBox.height(8));

        ButtonGroup monsters = getMonsterColumns();

        add(SizedBox.height(8));

        Label evolveLbl = new Label("Evolve to:", Typography.BODY);
        add(evolveLbl);

        add(SizedBox.height(8));

        ButtonGroup evolves = getEvolveColumns();

        add(SizedBox.height(8));

        Row buttonPanel = getEvolveBtn(homeGUI, monsters, evolves);
        add(buttonPanel);
    }

    private ButtonGroup getMonsterColumns() {
        ButtonGroup monsters = new ButtonGroup();
        int monsterCount = Javamon.getPlayerMonsters().length;
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

    private Row getEvolveBtn(HomeGUI homeGUI, ButtonGroup monsters, ButtonGroup evolves) {
        Row buttonPanel = new Row();

        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("homebase");
            }
        });

        Button evolveBtn = new Button("Evolve", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monsterName = monsters.getSelection().getActionCommand();
                if (monsterName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select a monster", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String evolveName = evolves.getSelection().getActionCommand();
                if (evolveName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select an element", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Monster monster = null;
                for (int i = 0; i < Javamon.getPlayerMonsters().length; i++) {
                    Monster dummyMonster = Javamon.getPlayerMonster(i);
                    if (dummyMonster.getName().equals(monsterName)) {
                        monster = dummyMonster;
                        break;
                    }
                }
                Element element = Element.valueOf(evolveName);
                try {
                    Javamon.getHOMEBASE().evolve(monster, element);
                } catch (CannotEvolveException err) {
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
        buttonPanel.add(evolveBtn);
        return buttonPanel;
    }

    private ButtonGroup getEvolveColumns() {
        ButtonGroup evolves = new ButtonGroup();
        JRadioButton[] evolveColumns = new JRadioButton[Element.values().length];
        for (int i = 0; i < Element.values().length; i++) {
            Element element = Element.values()[i];

            evolveColumns[i] = new JRadioButton(element.toString());
            evolveColumns[i].setActionCommand(element.toString());
            evolveColumns[i].setFont(Typography.BODY);
            evolveColumns[i].setForeground(Colors.TEXT);
            evolveColumns[i].setBackground(Colors.BACKGROUND);
            evolveColumns[i].setAlignmentX(CENTER_ALIGNMENT);

            evolves.add(evolveColumns[i]);
            add(evolveColumns[i]);
        }
        return evolves;
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}
