package javamon.frontend.welcome;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.exceptions.GameException;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Label;
import javamon.frontend.components.SizedBox;
import javamon.frontend.homebase.HomebasePanel;
import javamon.frontend.styles.Colors;
import javamon.frontend.components.Button;

public class WelcomePanel extends Panel {
    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        Label label = new Label("WELCOME TO JAVAMON!", "jua", 32f, 8, Colors.WELCOME, Colors.TEXT);
        add(label);

        add(SizedBox.height(16));

        Button newGameBtn = new Button("New Game", "Inter-Bold", 20f, Colors.WELCOME, Colors.TEXT, newGame(homeGUI));
        add(newGameBtn);

        add(SizedBox.height(8));

        Button saveGameBtn = new Button("Save Game", "Inter-Bold", 20f, Colors.WELCOME, Colors.TEXT, saveGame(homeGUI));
        add(saveGameBtn);

        add(SizedBox.height(8));

        Button loadGameBtn = new Button("Load Game", "Inter-Bold", 20f, Colors.WELCOME, Colors.TEXT, loadGame());
        add(loadGameBtn);

        add(SizedBox.height(8));

        Button exitBtn = new Button("Exit", "Inter-Bold", 20f, Colors.WELCOME, Colors.TEXT, exitGame(homeGUI));
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
                } catch (GameException e1) {
                    JOptionPane.showMessageDialog(null, "No save game found!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                HomebasePanel homebasePanel = new HomebasePanel(homeGUI);
                homeGUI.addPanel("homebase", homebasePanel);
                homeGUI.setPanel("homebase");
            }
        };
    }

    private ActionListener exitGame(HomeGUI homeGUI) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.exit();
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon("assets/images/background/welcome_bg.png");
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
