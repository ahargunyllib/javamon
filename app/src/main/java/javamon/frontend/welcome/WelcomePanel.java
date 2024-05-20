package javamon.frontend.welcome;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javamon.backend.Javamon;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;

public class WelcomePanel extends Panel {
    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(9, 9, 11));

        JLabel label = new JLabel("Welcome to Javamon!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setForeground(new Color(226, 226, 226));

        JButton newGameBtn = new JButton("New Game");
        newGameBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
        newGameBtn.setMaximumSize(new Dimension(200, 50));
        newGameBtn.setBackground(new Color(226, 226, 226));
        newGameBtn.setForeground(new Color(9, 9, 11));
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("newGame");
            }
        });

        JButton loadGameBtn = new JButton("Load Game");
        loadGameBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        loadGameBtn.setAlignmentX(CENTER_ALIGNMENT);
        loadGameBtn.setMaximumSize(new Dimension(200, 50));
        loadGameBtn.setBackground(new Color(226, 226, 226));
        loadGameBtn.setForeground(new Color(9, 9, 11));

        JButton saveGameBtn = new JButton("Save Game");
        saveGameBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        saveGameBtn.setAlignmentX(CENTER_ALIGNMENT);
        saveGameBtn.setMaximumSize(new Dimension(200, 50));
        saveGameBtn.setBackground(new Color(226, 226, 226));
        saveGameBtn.setForeground(new Color(9, 9, 11));
        saveGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Javamon.getPLAYER() == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "No game to save!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JDialog dialog = new JDialog(homeGUI.getFrame(), "Save Game", true);

                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setResizable(false);
                dialog.getContentPane().setBackground(new Color(9, 9, 11));
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Game saved successfully!");
                label.setAlignmentX(CENTER_ALIGNMENT);
                label.setForeground(new Color(226, 226, 226));

                JButton okBtn = new JButton("OK");
                okBtn.setAlignmentX(CENTER_ALIGNMENT);
                okBtn.setMaximumSize(new Dimension(100, 50));
                okBtn.setBackground(new Color(226, 226, 226));
                okBtn.setForeground(new Color(9, 9, 11));
                okBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Javamon.saveGame(String.format("saves\\%s.txt", (new Date().getTime())));
                        dialog.dispose();
                    }
                });

                setBorder(new EmptyBorder(16, 16, 16, 16));
                dialog.add(Box.createVerticalGlue());
                dialog.add(label);
                dialog.add(Box.createRigidArea(new Dimension(0, 16)));
                dialog.add(okBtn);
                dialog.add(Box.createVerticalGlue());

                dialog.setSize(300, 200);
                dialog.setLocationRelativeTo(homeGUI.getFrame());
                dialog.setVisible(true);
            }

        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        exitBtn.setAlignmentX(CENTER_ALIGNMENT);
        exitBtn.setMaximumSize(new Dimension(200, 50));
        exitBtn.setBackground(new Color(226, 226, 226));
        exitBtn.setForeground(new Color(9, 9, 11));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(homeGUI.getFrame(), "Exit", true);

                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setResizable(false);
                dialog.getContentPane().setBackground(new Color(9, 9, 11));
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Are you sure you want to exit?");
                label.setAlignmentX(CENTER_ALIGNMENT);
                label.setForeground(new Color(226, 226, 226));

                JButton yesBtn = new JButton("Yes");
                yesBtn.setAlignmentX(CENTER_ALIGNMENT);
                yesBtn.setMaximumSize(new Dimension(100, 50));
                yesBtn.setBackground(new Color(226, 226, 226));
                yesBtn.setForeground(new Color(9, 9, 11));
                yesBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        homeGUI.exit();
                    }
                });

                JButton noBtn = new JButton("No");
                noBtn.setAlignmentX(CENTER_ALIGNMENT);
                noBtn.setMaximumSize(new Dimension(100, 50));
                noBtn.setBackground(new Color(226, 226, 226));
                noBtn.setForeground(new Color(9, 9, 11));
                noBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                JPanel buttoPanel = new JPanel();
                buttoPanel.setBackground(new Color(9, 9, 11));
                buttoPanel.setLayout(new BoxLayout(buttoPanel, BoxLayout.X_AXIS));
                buttoPanel.add(noBtn);
                buttoPanel.add(Box.createRigidArea(new Dimension(8, 0)));
                buttoPanel.add(yesBtn);

                setBorder(new EmptyBorder(16, 16, 16, 16));
                dialog.add(Box.createVerticalGlue());
                dialog.add(label);
                dialog.add(Box.createRigidArea(new Dimension(0, 16)));
                dialog.add(buttoPanel);
                dialog.add(Box.createVerticalGlue());

                dialog.setSize(300, 200);
                dialog.setLocationRelativeTo(homeGUI.getFrame());
                dialog.setVisible(true);
            }
        });

        add(Box.createVerticalGlue());
        add(label);
        add(Box.createRigidArea(new Dimension(0, 24)));
        add(newGameBtn);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(loadGameBtn);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(saveGameBtn);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(exitBtn);
        add(Box.createVerticalGlue());

    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }

}
