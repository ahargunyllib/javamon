package javamon.frontend;

import javax.swing.*;

import javamon.frontend.welcome.NewGamePanel;
import javamon.frontend.welcome.WelcomePanel;

import java.awt.*;
import java.util.*;

public class HomeGUI {
    private final CardLayout cardLayout = new CardLayout();
    private final JFrame frame;
    private final JPanel panel = new JPanel();
    private final Map<String, Panel> panelMap = new HashMap<>();

    public HomeGUI(JFrame frame) {
        this.frame = frame;
        
        panel.setLayout(cardLayout);

        Panel welcomePanel = new WelcomePanel(this);
        panelMap.put("welcome", welcomePanel);
        panel.add(welcomePanel, "welcome");

        frame.setContentPane(panel);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setPanel(String target) {
        panelMap.get(target).refresh();
        cardLayout.show(panel, target);
    }

    public void addPanel(String name, Panel panel) {
        panelMap.put(name, panel);
        this.panel.add(panel, name);
    }

    public void replacePanel(String name, Panel panel) {
        panelMap.put(name, panel);
        this.panel.remove(panel);
        this.panel.add(panel, name);
    }

    public void exit() {
        frame.dispose();
    }
}
