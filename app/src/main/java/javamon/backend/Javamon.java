package javamon.backend;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javamon.backend.entity.Element;
import javamon.backend.entity.Monster;
import javamon.backend.entity.Player;
import javamon.backend.entity.items.Item;
import javamon.backend.entity.items.potions.HealthPotion;
import javamon.backend.entity.places.Homebase;

public class Javamon {
    public static Monster[] MONSTERS;
    public static Player PLAYER;
    public static Homebase HOMEBASE;
    public static Item[] ITEMS;

    public static void newGame(String name, Monster[] monsters) {
        Player player = new Player(name, monsters, 1000);
        Homebase homebase = new Homebase(player);

        setPLAYER(player);
        setHOMEBASE(homebase);

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
            int gold = (int) (Math.random() * 100 * level * 0.5);

            MONSTERS[i] = new Monster(listNama[i], level, hp, exp, element, attackPower,
                    defense, gold);
            System.out.println(MONSTERS[i].toString() + " created.");
        }
    }

    public static void registerItems() {
        ITEMS = new Item[6];
        String[] listNama = { "Big Health Potion", "Medium Health Potion", "Small Health Potion",
                "Big Elemental Potion", "Medium Elemental Potion", "Small Elemental Potion" };

        for (int i = 1; i <= 6; i++) {
            int mod = i % 3;
            int value, price;

            if (mod == 1) { // Big
                value = 75;
                price = 100;
            } else if (mod == 2) { // Medium
                value = 50;
                price = 75;
            } else { // Small
                value = 25;
                price = 50;
            }

            ITEMS[i - 1] = new HealthPotion(value, price, listNama[i - 1]);
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

    public static Homebase getHOMEBASE() {
        return HOMEBASE;
    }

    public static void setHOMEBASE(Homebase hOMEBASE) {
        HOMEBASE = hOMEBASE;
    }

    public static Item[] getITEMS() {
        return ITEMS;
    }

    public static void setITEMS(Item[] iTEMS) {
        ITEMS = iTEMS;
    }
}
