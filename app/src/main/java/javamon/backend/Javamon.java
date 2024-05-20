package javamon.backend;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javamon.backend.entity.Element;
import javamon.backend.entity.Monster;
import javamon.backend.entity.Player;

public class Javamon {
    public static Monster[] MONSTERS;
    public static Player PLAYER;

    public static void newGame(String name, Monster[] monsters) {
        Player player = new Player(name, monsters);
        setPLAYER(player);

        System.out.println("New game created for " + name + ".");
    }

    private void loadGame() {
        // TODO: Implement this method
    }

    public static void saveGame(String namaFile) {
        byte[] bufferData = new byte[1024];
        String data = PLAYER.toString();

        try {
            FileWriter writer = new FileWriter(namaFile);
            writer.write(data);
            writer.close();
            
            System.out.println("Game saved to " + namaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void play() {
        // TODO: Implement this method
    }

    private void exit() {
        // TODO: Implement this method
    }

    public static void registerMonsters() {
        MONSTERS = new Monster[12];
        String[] listNama = { "Bulbasaur", "Charmander", "Squirtle", "Pikachu", "Jigglypuff", "Psyduck", "Geodude",
                "Magnemite", "Eevee", "Snorlax", "Mewtwo", "Mew" };

        for (int i = 0; i < MONSTERS.length; i++) {
            int level = (int) (Math.random() * 10);
            int hp = (int) (Math.random() * 100 * level * 0.5);
            int exp = (int) (Math.random() * 50 * level * 0.5);
            Element element = Element.values()[(int) (Math.random() * Element.values().length)];
            int attackPower = (int) (Math.random() * 30 * level * 0.5);
            int defense = (int) (Math.random() * 30 * level * 0.5);

            MONSTERS[i] = new Monster(listNama[i], level, hp, exp, element, attackPower, defense);
            System.out.println(MONSTERS[i].toString() + " created.");
        }
    }

    public static Monster[] getMONSTERS() {
        return MONSTERS;
    }

    public static void setMONSTERS(Monster[] mONSTERS) {
        MONSTERS = mONSTERS;
    }

    public static Player getPLAYER() {
        return PLAYER;
    }

    public static void setPLAYER(Player pLAYER) {
        PLAYER = pLAYER;
    }

    
}
