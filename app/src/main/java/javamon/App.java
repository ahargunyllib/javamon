package javamon;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javamon.backend.Javamon;
import javamon.frontend.HomeGUI;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Javamon"); // window title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the window
        frame.setResizable(false); // prevent resizing
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximize the window
        frame.setUndecorated(true); // remove the window border
        frame.setLocationRelativeTo(null); // center the window
        frame.setIconImage(new ImageIcon("assets/images/logo.png").getImage()); // set the window icon


        Javamon.registerMonsters(); // register monsters
        Javamon.registerItems(); // register items
        Javamon.registerDungeons(); // register dungeons
        
        HomeGUI homeGUI = new HomeGUI(frame); // create a new HomeGUI object
        homeGUI.setPanel("welcome"); // set the panel to "welcome"

        frame.setVisible(true); // show the window
    }
}
