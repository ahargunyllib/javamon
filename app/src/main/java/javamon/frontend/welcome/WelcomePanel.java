package javamon.frontend.welcome;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javamon.backend.Javamon;
import javamon.backend.exceptions.NoSaveGameException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.homebase.HomebasePanel;
import javamon.frontend.styles.Colors;
import javamon.frontend.styles.Typography;
import javamon.frontend.components.Button;

public class WelcomePanel extends Panel {
    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        Label label = new Label("WELCOME TO JAVAMON!", "jua", 32f, 8, Colors.WELCOME, Colors.TEXT);
        add(label);

        add(SizedBox.height(16));

        Button newGameBtn = new Button("New Game", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT, newGame(homeGUI));
        add(newGameBtn);

        add(SizedBox.height(8));

        Button saveGameBtn = new Button("Save Game", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT, saveGame(homeGUI));
        add(saveGameBtn);

        add(SizedBox.height(8));

        Button loadGameBtn = new Button("Load Game", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT, loadGame());
        add(loadGameBtn);

        add(SizedBox.height(8));

        Button exitBtn = new Button("Exit", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT, exitGame(homeGUI));
        add(exitBtn);

        add(Box.createVerticalGlue());

    }

    private ActionListener newGame(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGamePanel newGamePanel = new NewGamePanel(homeGUI);
                homeGUI.addPanel("newGame", newGamePanel);
                homeGUI.setPanel("newGame");
            }
        };
    }

    private ActionListener loadGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Javamon.loadGame();
                } catch (NoSaveGameException e1) {
                    JOptionPane.showMessageDialog(null, "No save game found!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.addPanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");

                // JDialog dialog = new JDialog(homeGUI.getFrame(), "Load Game", true);

                // dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                // dialog.setResizable(false);
                // dialog.getContentPane().setBackground(new Color(9, 9, 11));
                // dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                // Label label = new Label("Game loaded!", Typography.BODY);

                // Button okBtn = new Button("OK", Typography.BUTTON, new ActionListener() {
                //     @Override
                //     public void actionPerformed(ActionEvent e) {
                //         try {

                //         } catch (NoSaveGameException e1) {
                //             JOptionPane.showMessageDialog(homeGUI.getFrame(), "No save game found!", "Error",
                //                     JOptionPane.ERROR_MESSAGE);
                //         }
                //         dialog.dispose();
                //     }
                // });

                // setBorder(new EmptyBorder(16, 16, 16, 16));

                // dialog.add(Box.createVerticalGlue());
                // dialog.add(label);
                // dialog.add(Box.createRigidArea(new Dimension(0, 16)));
                // dialog.add(okBtn);
                // dialog.add(Box.createVerticalGlue());

                // dialog.setSize(300, 200);
                // dialog.setLocationRelativeTo(homeGUI.getFrame());
                // dialog.setVisible(true);
            }
        };
    }

    private ActionListener exitGame(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.exit();

                // JDialog dialog = new JDialog(homeGUI.getFrame(), "Exit", true);

                // dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                // dialog.setResizable(false);
                // dialog.getContentPane().setBackground(new Color(9, 9, 11));
                // dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                // Label label = new Label("Are you sure you want to exit?", Typography.BODY);

                // Button yesBtn = new Button("Yes", Typography.BUTTON, new ActionListener() {
                // @Override
                // public void actionPerformed(ActionEvent e) {

                // }
                // });

                // Button noBtn = new Button("No", Typography.BUTTON, new ActionListener() {
                // @Override
                // public void actionPerformed(ActionEvent e) {
                // dialog.dispose();
                // }
                // });

                // Row buttonPanel = new Row();
                // buttonPanel.add(noBtn);
                // buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
                // buttonPanel.add(yesBtn);

                // setBorder(new EmptyBorder(16, 16, 16, 16));
                // dialog.add(Box.createVerticalGlue());
                // dialog.add(label);
                // dialog.add(Box.createRigidArea(new Dimension(0, 16)));
                // dialog.add(buttonPanel);
                // dialog.add(Box.createVerticalGlue());

                // dialog.setSize(300, 200);
                // dialog.setLocationRelativeTo(homeGUI.getFrame());
                // dialog.setVisible(true);
            }

        };
    }

    private ActionListener saveGame(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Javamon.getPLAYER() == null) {
                    JOptionPane.showMessageDialog(homeGUI.getFrame(), "No game to save!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Javamon.saveGame(String.format("saves\\%s.txt", (new Date().getTime())));
            }

        };
    }

    @Override
    public void refresh() {
        // TODO: Implement this method
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/welcome_bg.png");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
