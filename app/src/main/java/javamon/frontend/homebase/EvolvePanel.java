package javamon.frontend.homebase;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class EvolvePanel extends Panel {
    public EvolvePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Evolve Your Monster!", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Label monsterLbl = new Label("Choose a monster to evolve:", Typography.BODY);
        Label evolveLbl = new Label("Evolve to:", Typography.BODY);
        
        add(pageLbl);
        add(Box.createVerticalStrut(8));
        add(usernameLbl);
        add(Box.createVerticalStrut(8));
        add(monsterLbl);
        add(Box.createVerticalStrut(8));

        ButtonGroup monsters = new ButtonGroup();
        JRadioButton[] monsterColumns = new JRadioButton[Javamon.getPLAYER().getMonsters().length];
        for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
            monsterColumns[i] = new JRadioButton(Javamon.getPLAYER().getMonsters()[i].getName() + " - "
                    + Javamon.getPLAYER().getMonsters()[i].getElement().toString());
            monsterColumns[i].setActionCommand(Javamon.getPLAYER().getMonsters()[i].getName());
            monsterColumns[i].setFont(Typography.BODY);
            monsterColumns[i].setForeground(Colors.TEXT);
            monsterColumns[i].setBackground(Colors.BACKGROUND);
            monsterColumns[i].setAlignmentX(CENTER_ALIGNMENT);

            monsters.add(monsterColumns[i]);
            add(monsterColumns[i]);
        }
        
        add(Box.createVerticalStrut(8));
        add(evolveLbl);
        add(Box.createVerticalStrut(8));

        ButtonGroup evolves = new ButtonGroup();
        JRadioButton[] evolveColumns = new JRadioButton[Element.values().length];
        for (int i = 0; i < Element.values().length; i++) {
            evolveColumns[i] = new JRadioButton(Element.values()[i].toString());
            evolveColumns[i].setActionCommand(Element.values()[i].toString());
            evolveColumns[i].setFont(Typography.BODY);
            evolveColumns[i].setForeground(Colors.TEXT);
            evolveColumns[i].setBackground(Colors.BACKGROUND);
            evolveColumns[i].setAlignmentX(CENTER_ALIGNMENT);

            evolves.add(evolveColumns[i]);
            add(evolveColumns[i]);
        }

        add(Box.createVerticalStrut(8));
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
                String evolveName = evolves.getSelection().getActionCommand();
                Monster monster = null;
                for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
                    if (Javamon.getPLAYER().getMonsters()[i].getName().equals(monsterName)) {
                        monster = Javamon.getPLAYER().getMonsters()[i];
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
        buttonPanel.add(Box.createHorizontalStrut(8));
        buttonPanel.add(evolveBtn);

        add(buttonPanel);
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}
