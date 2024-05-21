package javamon.frontend.welcome;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javamon.backend.Javamon;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.styles.Typography;
import javamon.frontend.components.Button;

public class WelcomePanel extends Panel {
    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(9, 9, 11));

        Label label = new Label("Welcome to Javamon", Typography.TITLE);

        Button newGameBtn = new Button("New Game", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGamePanel newGamePanel = new NewGamePanel(homeGUI);
                homeGUI.addPanel("newGame", newGamePanel);
                homeGUI.setPanel("newGame");
            }
        });

        Button loadGameBtn = new Button("Load Game", Typography.BUTTON, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        Button saveGameBtn = new Button("Save Game", Typography.BUTTON, new ActionListener() {
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

                Label label = new Label("Game saved!", Typography.BODY);

                Button okBtn = new Button("OK", Typography.BUTTON, new ActionListener() {
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

        Button exitBtn = new Button("Exit", Typography.BUTTON,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDialog dialog = new JDialog(homeGUI.getFrame(), "Exit", true);

                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setResizable(false);
                        dialog.getContentPane().setBackground(new Color(9, 9, 11));
                        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                        Label label = new Label("Are you sure you want to exit?", Typography.BODY);

                        Button yesBtn = new Button("Yes", Typography.BUTTON, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                homeGUI.exit();
                            }
                        });

                        Button noBtn = new Button("No", Typography.BUTTON, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                            }
                        });

                        Row buttonPanel = new Row();
                        buttonPanel.add(noBtn);
                        buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
                        buttonPanel.add(yesBtn);

                        setBorder(new EmptyBorder(16, 16, 16, 16));
                        dialog.add(Box.createVerticalGlue());
                        dialog.add(label);
                        dialog.add(Box.createRigidArea(new Dimension(0, 16)));
                        dialog.add(buttonPanel);
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
