package javamon.frontend.homebase;

import java.util.*;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.items.Item;
import javamon.backend.exceptions.GameException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;

public class ShopPanel extends Panel {
    public ShopPanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.SHOP);

        Row header = new Row();

        Button backBtn = new Button("Back", "Inter-Bold", 16f, Color.WHITE, Color.BLACK, back(homeGUI));
        Label pageLbl = new Label("Shop", "jua", 32f, 8, Color.WHITE, Color.BLACK);
        Label goldLbl = new Label(Javamon.getPLAYER().getGold() + " gold", "jua", 16f, 8, Color.WHITE, Color.BLACK);

        header.add(backBtn);
        header.add(Box.createHorizontalGlue());
        header.add(pageLbl);
        header.add(Box.createHorizontalGlue());
        header.add(goldLbl);

        add(header);

        add(SizedBox.height(32));

        Map<Item, Integer> itemToBuy = new HashMap<>();
        for (Item item : Javamon.getITEMS()) {
            itemToBuy.put(item, 0);
        }

        int total = 0;
        for (Item item : itemToBuy.keySet()) {
            total += item.getPrice() * itemToBuy.get(item);
        }
        Label totalLbl = new Label("Total: " + total + " gold", "jua", 16f, 8, Color.WHITE, Color.BLACK);

        Row itemPanel = new Row();

        Column healPanel = getHealItems(itemToBuy, totalLbl);

        Column elementalPanel = new Column();
        elementalPanel.setBackground(Colors.Elemental);

        Label elementalLbl = new Label("Elemental Items", "jua", 16f, 8, Color.WHITE, Color.BLACK);
        
        ImageIcon elementalItemIcon = new ImageIcon("assets/images/items/elemental.png");
        int newWidth = 256;
        int newHeight = 256;
        Image img = elementalItemIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon elementalItemIconScaled = new ImageIcon(img);
        JLabel elementalItemIconLbl = new JLabel(elementalItemIconScaled);
        elementalItemIconLbl.setAlignmentX(CENTER_ALIGNMENT);

        elementalPanel.add(SizedBox.height(32));
        elementalPanel.add(elementalLbl);
        elementalPanel.add(SizedBox.height(64));
        elementalPanel.add(elementalItemIconLbl);
        elementalPanel.add(SizedBox.height(64));

        Row[] elementalItems = new Row[3];
        for (int i = 3; i < 6; i++) {
            elementalItems[i - 3] = new Row();
            Label elementalItemLbl = new Label(
                    Javamon.getItem(i).getName() + " - " + Javamon.getItem(i).getPrice() + " gold",
                    "jua", 16f, 8, Color.WHITE, Color.BLACK);
            Button addItem = new Button("+", "Inter-Bold", 16f, Color.WHITE, Color.BLACK,
                    addItem(itemToBuy, i, totalLbl));
            Button removeItem = new Button("-", "Inter-Bold", 16f, Color.WHITE, Color.BLACK,
                    removeItem(itemToBuy, i, totalLbl));

            elementalItems[i - 3].add(Box.createHorizontalGlue());
            elementalItems[i - 3].add(elementalItemLbl);
            elementalItems[i - 3].add(SizedBox.width(8));
            elementalItems[i - 3].add(addItem);
            elementalItems[i - 3].add(SizedBox.width(8));
            elementalItems[i - 3].add(removeItem);
            elementalItems[i - 3].add(Box.createHorizontalGlue());

            elementalPanel.add(elementalItems[i - 3]);
        }
        
        elementalPanel.add(SizedBox.height(32));

        itemPanel.add(healPanel);
        itemPanel.add(Box.createHorizontalStrut(8));
        itemPanel.add(elementalPanel);

        add(itemPanel);
        add(SizedBox.height(32));
        add(totalLbl);
        add(SizedBox.height(8));

        Button buyBtn = getBuyBtn(homeGUI, itemToBuy);
        add(buyBtn);
    }

    private Column getHealItems(Map<Item, Integer> itemToBuy, Label totalLbl) {
        Column healPanel = new Column();
        healPanel.setBackground(Colors.HP);

        Label healLbl = new Label("Healing Items", "jua", 16f, 8, Color.WHITE, Color.BLACK);

        ImageIcon healItemIcon = new ImageIcon("assets/images/items/heal.png");
        int newWidth = 256;
        int newHeight = 256;
        Image img = healItemIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon healItemIconScaled = new ImageIcon(img);
        JLabel healItemIconLbl = new JLabel(healItemIconScaled);
        healItemIconLbl.setAlignmentX(CENTER_ALIGNMENT);

        healPanel.add(SizedBox.height(32));
        healPanel.add(healLbl);
        healPanel.add(SizedBox.height(64));
        healPanel.add(healItemIconLbl);
        healPanel.add(SizedBox.height(64));

        Row[] healItems = new Row[3];
        for (int i = 0; i < 3; i++) {
            healItems[i] = new Row();
            Label healItemLbl = new Label(
                    Javamon.getItem(i).getName() + " - " + Javamon.getItem(i).getPrice() + " gold",
                    "jua", 16f, 8, Color.WHITE, Color.BLACK);
            Button addItem = new Button("+", "Inter-Bold", 16f, Color.WHITE, Color.BLACK,
                    addItem(itemToBuy, i, totalLbl));
            Button removeItem = new Button("-", "Inter-Bold", 16f, Color.WHITE, Color.BLACK,
                    removeItem(itemToBuy, i, totalLbl));

            healItems[i].add(Box.createHorizontalGlue());
            healItems[i].add(healItemLbl);
            healItems[i].add(SizedBox.width(8));
            healItems[i].add(addItem);
            healItems[i].add(SizedBox.width(8));
            healItems[i].add(removeItem);
            healItems[i].add(Box.createHorizontalGlue());

            healPanel.add(healItems[i]);
        }

        healPanel.add(SizedBox.height(32));
        return healPanel;
    }

    private ActionListener addItem(Map<Item, Integer> itemToBuy, int i, Label label) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemToBuy.put(Javamon.getItem(i), itemToBuy.get(Javamon.getItem(i)) + 1);

                int total = 0;
                for (Item item : itemToBuy.keySet()) {
                    total += item.getPrice() * itemToBuy.get(item);
                }
                label.setText("Total: " + total + " gold");

                System.out.println(itemToBuy);
            }
        };
    }

    private ActionListener removeItem(Map<Item, Integer> itemToBuy, int i, Label label) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemToBuy.get(Javamon.getItem(i)) > 0) {
                    itemToBuy.put(Javamon.getItem(i), itemToBuy.get(Javamon.getItem(i)) - 1);
                }

                int total = 0;
                for (Item item : itemToBuy.keySet()) {
                    total += item.getPrice() * itemToBuy.get(item);
                }
                label.setText("Total: " + total + " gold");

                System.out.println(itemToBuy);
            }
        };
    }

    private ActionListener back(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("homebase");
            }
        };
    }

    private Button getBuyBtn(HomeGUI homeGUI, Map<Item, Integer> items) {
        Button buyBtn = new Button("Buy", "jua", 16f, Color.WHITE, Color.BLACK, buyItems(homeGUI, items));
        return buyBtn;
    }

    private ActionListener buyItems(HomeGUI homeGUI, Map<Item, Integer> items) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Javamon.getHOMEBASE().buyItem(items);
                    
                    homeGUI.setPanel("homebase");
                } catch (GameException err) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), err.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        };
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}