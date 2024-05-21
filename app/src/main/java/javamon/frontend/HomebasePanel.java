package javamon.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;

public class HomebasePanel extends Panel {
    public HomebasePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BorderLayout());
        setBackground(Colors.BACKGROUND);

        Label label = new Label("Homebase", Typography.TITLE);
        Label usernameLbl = new Label("Username: " + Javamon.getPLAYER().getName(), Typography.BODY);
        Button backBtn = new Button("Back", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });
        Button duengonBtn = new Button("Go to Dungeon", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button restoreHpBtn = new Button("Restore HP", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button evolveBtn = new Button("Evolve", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button levelUpBtn = new Button("Level Up", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button shopBtn = new Button("Go to Shop", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Button saveMonsterBtn = new Button("Save Monster", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        Column topSidePanel = new Column();
        topSidePanel.setAlignmentX(CENTER_ALIGNMENT);
        topSidePanel.add(label);
        topSidePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        topSidePanel.add(usernameLbl);

        Row leftSidePanel = new Row();
        leftSidePanel.add(Box.createVerticalGlue());
        leftSidePanel.add(backBtn);
        leftSidePanel.add(Box.createVerticalGlue());

        Column rightSidePanel = new Column();
        rightSidePanel.add(Box.createVerticalGlue());
        rightSidePanel.add(duengonBtn);
        rightSidePanel.add(Box.createVerticalStrut(8));
        rightSidePanel.add(shopBtn);
        rightSidePanel.add(Box.createVerticalGlue());

        Row bottomSidePanel = new Row();
        bottomSidePanel.add(Box.createHorizontalGlue());
        bottomSidePanel.add(restoreHpBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(evolveBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(levelUpBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(saveMonsterBtn);
        bottomSidePanel.add(Box.createHorizontalGlue());
        
        Label leftMonsterNameLbl = new Label(Javamon.getPLAYER().getMonsters()[0].getName(), Typography.LABEL);
        Label leftMonsterHpLbl = new Label("HP: " + Javamon.getPLAYER().getMonsters()[0].getCurrHp() + "/" + Javamon.getPLAYER().getMonsters()[0].getMaxHp(), Typography.LABEL);

        Column leftMonsterPanel = new Column();
        leftMonsterPanel.add(Box.createVerticalGlue());
        leftMonsterPanel.add(leftMonsterNameLbl);
        leftMonsterPanel.add(Box.createVerticalStrut(8));
        leftMonsterPanel.add(leftMonsterHpLbl);
        leftMonsterPanel.add(Box.createVerticalGlue());

        Label centerMonsterNameLbl = new Label(Javamon.getPLAYER().getMonsters()[1].getName(), Typography.LABEL);
        Label centerMonsterHpLbl = new Label("HP: " + Javamon.getPLAYER().getMonsters()[1].getCurrHp() + "/" + Javamon.getPLAYER().getMonsters()[1].getMaxHp(), Typography.LABEL);

        Column centerMonsterPanel = new Column();
        centerMonsterPanel.setLayout(new BoxLayout(centerMonsterPanel, BoxLayout.Y_AXIS));
        centerMonsterPanel.setBackground(new Color(9, 9, 11));
        centerMonsterPanel.setAlignmentX(CENTER_ALIGNMENT);
        centerMonsterPanel.add(Box.createVerticalGlue());
        centerMonsterPanel.add(centerMonsterNameLbl);
        centerMonsterPanel.add(Box.createVerticalStrut(8));
        centerMonsterPanel.add(centerMonsterHpLbl);
        centerMonsterPanel.add(Box.createVerticalGlue());

        Label rightMonsterNameLbl = new Label(Javamon.getPLAYER().getMonsters()[2].getName(), Typography.LABEL);
        Label rightMonsterHpLbl = new Label("HP: " + Javamon.getPLAYER().getMonsters()[2].getCurrHp() + "/" + Javamon.getPLAYER().getMonsters()[2].getMaxHp(), Typography.LABEL);

        Column rightMonsterPanel = new Column();
        rightMonsterPanel.add(Box.createVerticalGlue());
        rightMonsterPanel.add(rightMonsterNameLbl);
        rightMonsterPanel.add(Box.createVerticalStrut(8));
        rightMonsterPanel.add(rightMonsterHpLbl);
        rightMonsterPanel.add(Box.createVerticalGlue());

        Row centerSidePanel = new Row();
        centerSidePanel.add(leftMonsterPanel);
        centerSidePanel.add(Box.createHorizontalGlue());
        centerSidePanel.add(centerMonsterPanel);
        centerSidePanel.add(Box.createHorizontalGlue());
        centerSidePanel.add(rightMonsterPanel);
        centerSidePanel.add(Box.createHorizontalGlue());


        add(topSidePanel, BorderLayout.NORTH);
        add(leftSidePanel, BorderLayout.WEST);
        add(rightSidePanel, BorderLayout.EAST);
        add(bottomSidePanel, BorderLayout.SOUTH);
        add(centerSidePanel, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }
}
