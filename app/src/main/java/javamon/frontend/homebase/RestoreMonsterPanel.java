package javamon.frontend.homebase;

import java.awt.event.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

public class RestoreMonsterPanel extends Panel {
    public RestoreMonsterPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Restore Your Monsters!", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Label monsterLbl = new Label("Choose monsters to restore:", Typography.BODY);
        
        add(pageLbl);
        add(Box.createVerticalStrut(8));
        add(usernameLbl);
        add(Box.createVerticalStrut(8));
        add(monsterLbl);
        add(Box.createVerticalStrut(8));

        if (Javamon.getHOMEBASE().getMonsters().length == 0) {
            Label noMonsterLbl = new Label("No monsters to restore", Typography.BODY);
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
            Column monsters = new Column();
            JCheckBox[] monsterColumns = new JCheckBox[Javamon.getHOMEBASE().getMonsters().length];
            for (int i = 0; i < Javamon.getHOMEBASE().getMonsters().length; i++) {
                monsterColumns[i] = new JCheckBox(Javamon.getHOMEBASE().getMonsters()[i].getName() + " - "
                        + Javamon.getHOMEBASE().getMonsters()[i].getElement().toString());
                monsterColumns[i].setActionCommand(Javamon.getHOMEBASE().getMonsters()[i].getName());
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
    
            Button saveBtn = new Button("Restore", Typography.BUTTON, new ActionListener() {
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
                        for (int j = 0; j < Javamon.getHOMEBASE().getMonsters().length; j++) {
                            if (monsterNames[i].equals(Javamon.getHOMEBASE().getMonsters()[j].getName())) {
                                monsters[i] = Javamon.getHOMEBASE().getMonsters()[j];
                            }
                        }
                    }
    
                    Javamon.getHOMEBASE().restoreMonster(monsters);
    
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Monster restored!", "Success",
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
