package javamon.frontend.homebase;

import java.awt.event.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.backend.entity.items.Item;
import javamon.backend.exceptions.GameException;
import javamon.backend.exceptions.NotEnoughExpException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class ShopPanel extends Panel {
    public ShopPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label pageLbl = new Label("Welcome to Shop!", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Label monsterLbl = new Label("Choose an item to buy:", Typography.BODY);
        
        add(pageLbl);
        add(Box.createVerticalStrut(8));
        add(usernameLbl);
        add(Box.createVerticalStrut(8));
        add(monsterLbl);
        add(Box.createVerticalStrut(8));

        ButtonGroup items = new ButtonGroup();
        JRadioButton[] itemColumns = new JRadioButton[Javamon.getITEMS().length];
        for (int i = 0; i < Javamon.getITEMS().length; i++) {
            itemColumns[i] = new JRadioButton(Javamon.getITEMS()[i].getName() + " - "
                    + Javamon.getITEMS()[i].getPrice());
            itemColumns[i].setActionCommand(Javamon.getITEMS()[i].getName());
            itemColumns[i].setFont(Typography.BODY);
            itemColumns[i].setForeground(Colors.TEXT);
            itemColumns[i].setBackground(Colors.BACKGROUND);
            itemColumns[i].setAlignmentX(CENTER_ALIGNMENT);

            items.add(itemColumns[i]);
            add(itemColumns[i]);
        }

        add(Box.createVerticalStrut(8));
        
        Row buttonPanel = new Row();

        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("homebase");
            }
        });

        Button buyBtn = new Button("Buy", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = items.getSelection().getActionCommand();

                if (itemName == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "Please select an item", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Item item = null;
                for (int i = 0; i < Javamon.getITEMS().length; i++) {
                    if (Javamon.getITEMS()[i].getName().equals(itemName)) {
                        item = Javamon.getITEMS()[i];
                        break;
                    }
                }

                try {
                    Javamon.getHOMEBASE().buyItem(item);
                } catch (GameException err) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), err.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(homeGUI.getFrame(), "Successfully buy an item", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                homeGUI.setPanel("homebase");
            }
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(Box.createHorizontalStrut(8));
        buttonPanel.add(buyBtn);

        add(buttonPanel);
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        
    }
}