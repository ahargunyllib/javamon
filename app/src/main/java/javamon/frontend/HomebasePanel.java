package javamon.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;

public class HomebasePanel extends Panel {
    public HomebasePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BorderLayout());
        setBackground(new Color(9, 9, 11));

        JLabel label = new JLabel("Homebase");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setForeground(new Color(226, 226, 226));

        JLabel usernameLbl = new JLabel("Username: " + Javamon.getPLAYER().getName());
        usernameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        usernameLbl.setAlignmentX(CENTER_ALIGNMENT);
        usernameLbl.setForeground(new Color(226, 226, 226));

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        backBtn.setAlignmentX(CENTER_ALIGNMENT);
        backBtn.setBackground(new Color(226, 226, 226));
        backBtn.setForeground(new Color(9, 9, 11));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton duengonBtn = new JButton("Go to Dungeon");
        duengonBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        duengonBtn.setAlignmentX(CENTER_ALIGNMENT);
        duengonBtn.setBackground(new Color(226, 226, 226));
        duengonBtn.setForeground(new Color(9, 9, 11));
        duengonBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton restoreHpBtn = new JButton("Restore HP");
        restoreHpBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        restoreHpBtn.setAlignmentX(CENTER_ALIGNMENT);
        restoreHpBtn.setBackground(new Color(226, 226, 226));
        restoreHpBtn.setForeground(new Color(9, 9, 11));
        restoreHpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton evolveBtn = new JButton("Evolve");
        evolveBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        evolveBtn.setAlignmentX(CENTER_ALIGNMENT);
        evolveBtn.setBackground(new Color(226, 226, 226));
        evolveBtn.setForeground(new Color(9, 9, 11));
        evolveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton levelUpBtn = new JButton("Level Up");
        levelUpBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        levelUpBtn.setAlignmentX(CENTER_ALIGNMENT);
        levelUpBtn.setBackground(new Color(226, 226, 226));
        levelUpBtn.setForeground(new Color(9, 9, 11));
        levelUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton shopBtn = new JButton("Go to Shop");
        shopBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        shopBtn.setAlignmentX(CENTER_ALIGNMENT);
        shopBtn.setBackground(new Color(226, 226, 226));
        shopBtn.setForeground(new Color(9, 9, 11));
        shopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JButton saveMonsterBtn = new JButton("Save Monster");
        saveMonsterBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        saveMonsterBtn.setAlignmentX(CENTER_ALIGNMENT);
        saveMonsterBtn.setBackground(new Color(226, 226, 226));
        saveMonsterBtn.setForeground(new Color(9, 9, 11));
        saveMonsterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("welcome");
            }
        });

        JPanel topSidePanel = new JPanel();
        topSidePanel.setLayout(new BoxLayout(topSidePanel, BoxLayout.Y_AXIS));
        topSidePanel.setBackground(new Color(9, 9, 11));
        topSidePanel.setAlignmentX(CENTER_ALIGNMENT);
        topSidePanel.add(label);
        topSidePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        topSidePanel.add(usernameLbl);

        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS));
        leftSidePanel.setBackground(new Color(9, 9, 11));
        leftSidePanel.setAlignmentX(CENTER_ALIGNMENT);
        leftSidePanel.add(Box.createVerticalGlue());
        leftSidePanel.add(backBtn);
        leftSidePanel.add(Box.createVerticalGlue());

        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new BoxLayout(rightSidePanel, BoxLayout.Y_AXIS));
        rightSidePanel.setBackground(new Color(9, 9, 11));
        rightSidePanel.setAlignmentX(CENTER_ALIGNMENT);
        rightSidePanel.add(Box.createVerticalGlue());
        rightSidePanel.add(duengonBtn);
        rightSidePanel.add(Box.createVerticalStrut(8));
        rightSidePanel.add(shopBtn);
        rightSidePanel.add(Box.createVerticalGlue());

        JPanel bottomSidePanel = new JPanel();
        bottomSidePanel.setLayout(new BoxLayout(bottomSidePanel, BoxLayout.X_AXIS));
        bottomSidePanel.setBackground(new Color(9, 9, 11));
        bottomSidePanel.setAlignmentY(CENTER_ALIGNMENT);
        bottomSidePanel.add(Box.createHorizontalGlue());
        bottomSidePanel.add(restoreHpBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(evolveBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(levelUpBtn);
        bottomSidePanel.add(Box.createHorizontalStrut(8));
        bottomSidePanel.add(saveMonsterBtn);
        bottomSidePanel.add(Box.createHorizontalGlue());
        
        JLabel leftMonsterNameLbl = new JLabel(Javamon.PLAYER.getMonsters()[0].getName());
        leftMonsterNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        leftMonsterNameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        leftMonsterNameLbl.setAlignmentX(CENTER_ALIGNMENT);
        leftMonsterNameLbl.setForeground(new Color(226, 226, 226));

        JLabel leftMonsterHpLbl = new JLabel("HP: " + Javamon.PLAYER.getMonsters()[0].getHp());
        leftMonsterHpLbl.setHorizontalAlignment(SwingConstants.CENTER);
        leftMonsterHpLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
        leftMonsterHpLbl.setAlignmentX(CENTER_ALIGNMENT);
        leftMonsterHpLbl.setForeground(new Color(226, 226, 226));

        JPanel leftMonsterPanel = new JPanel();
        leftMonsterPanel.setLayout(new BoxLayout(leftMonsterPanel, BoxLayout.Y_AXIS));
        leftMonsterPanel.setBackground(new Color(9, 9, 11));
        leftMonsterPanel.setAlignmentX(CENTER_ALIGNMENT);
        leftMonsterPanel.add(Box.createVerticalGlue());
        leftMonsterPanel.add(leftMonsterNameLbl);
        leftMonsterPanel.add(Box.createVerticalStrut(8));
        leftMonsterPanel.add(leftMonsterHpLbl);
        leftMonsterPanel.add(Box.createVerticalGlue());

        JLabel centerMonsterNameLbl = new JLabel(Javamon.PLAYER.getMonsters()[1].getName());
        centerMonsterNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        centerMonsterNameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        centerMonsterNameLbl.setAlignmentX(CENTER_ALIGNMENT);
        centerMonsterNameLbl.setForeground(new Color(226, 226, 226));

        JLabel centerMonsterHpLbl = new JLabel("HP: " + Javamon.PLAYER.getMonsters()[1].getHp());
        centerMonsterHpLbl.setHorizontalAlignment(SwingConstants.CENTER);
        centerMonsterHpLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
        centerMonsterHpLbl.setAlignmentX(CENTER_ALIGNMENT);
        centerMonsterHpLbl.setForeground(new Color(226, 226, 226));

        JPanel centerMonsterPanel = new JPanel();
        centerMonsterPanel.setLayout(new BoxLayout(centerMonsterPanel, BoxLayout.Y_AXIS));
        centerMonsterPanel.setBackground(new Color(9, 9, 11));
        centerMonsterPanel.setAlignmentX(CENTER_ALIGNMENT);
        centerMonsterPanel.add(Box.createVerticalGlue());
        centerMonsterPanel.add(centerMonsterNameLbl);
        centerMonsterPanel.add(Box.createVerticalStrut(8));
        centerMonsterPanel.add(centerMonsterHpLbl);
        centerMonsterPanel.add(Box.createVerticalGlue());

        JLabel rightMonsterNameLbl = new JLabel(Javamon.PLAYER.getMonsters()[2].getName());
        rightMonsterNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        rightMonsterNameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        rightMonsterNameLbl.setAlignmentX(CENTER_ALIGNMENT);
        rightMonsterNameLbl.setForeground(new Color(226, 226, 226));

        JLabel rightMonsterHpLbl = new JLabel("HP: " + Javamon.PLAYER.getMonsters()[2].getHp());
        rightMonsterHpLbl.setHorizontalAlignment(SwingConstants.CENTER);
        rightMonsterHpLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
        rightMonsterHpLbl.setAlignmentX(CENTER_ALIGNMENT);
        rightMonsterHpLbl.setForeground(new Color(226, 226, 226));

        JPanel rightMonsterPanel = new JPanel();
        rightMonsterPanel.setLayout(new BoxLayout(rightMonsterPanel, BoxLayout.Y_AXIS));
        rightMonsterPanel.setBackground(new Color(9, 9, 11));
        rightMonsterPanel.setAlignmentX(CENTER_ALIGNMENT);
        rightMonsterPanel.add(Box.createVerticalGlue());
        rightMonsterPanel.add(rightMonsterNameLbl);
        rightMonsterPanel.add(Box.createVerticalStrut(8));
        rightMonsterPanel.add(rightMonsterHpLbl);
        rightMonsterPanel.add(Box.createVerticalGlue());

        JPanel centerSidePanel = new JPanel();
        centerSidePanel.setLayout(new BoxLayout(centerSidePanel, BoxLayout.X_AXIS));
        centerSidePanel.setBackground(new Color(9, 9, 11));
        centerSidePanel.setAlignmentY(CENTER_ALIGNMENT);
        centerSidePanel.add(Box.createHorizontalGlue());
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
