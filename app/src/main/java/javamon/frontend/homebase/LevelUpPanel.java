package javamon.frontend.homebase;

import java.awt.event.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.backend.exceptions.NotEnoughExpException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class LevelUpPanel extends Panel {
    public LevelUpPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Evolve Your Monster!", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Label monsterLbl = new Label("Choose a monster to evolve:", Typography.BODY);
        
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

                Monster monster = null;
                for (int i = 0; i < Javamon.getPLAYER().getMonsters().length; i++) {
                    if (Javamon.getPLAYER().getMonsters()[i].getName().equals(monsterName)) {
                        monster = Javamon.getPLAYER().getMonsters()[i];
                        break;
                    }
                }

                try {
                    Javamon.getHOMEBASE().levelUp(monster);
                } catch (NotEnoughExpException err) {
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
