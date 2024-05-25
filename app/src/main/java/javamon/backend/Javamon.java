package javamon.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import javamon.backend.entity.Element;
import javamon.backend.entity.Monster;
import javamon.backend.entity.Player;
import javamon.backend.entity.items.Item;
import javamon.backend.entity.items.potions.ElementalPotion;
import javamon.backend.entity.items.potions.HealthPotion;
import javamon.backend.entity.places.Homebase;
import javamon.backend.exceptions.NoSaveGameException;

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

    public static void loadGame() throws NoSaveGameException {
        File directory = new File("saves");
        File[] files = directory.listFiles();
        if (files == null) 
            throw new NoSaveGameException();
        
        try {
            FileReader file = new FileReader(files[files.length - 1]);
            
            BufferedReader reader = new BufferedReader(file);
            String data = reader.readLine();
            String[] splitData = data.split(";");
            String name = splitData[0];
            double gold = Double.parseDouble(splitData[1]);

            data = reader.readLine();
            splitData = data.split(";");
            Monster[] monsters = new Monster[splitData.length];
            for (int i = 0; i < splitData.length; i++) {
                String[] monsterData = splitData[i].split(",");
                String monsterName = monsterData[0];
                int level = Integer.parseInt(monsterData[1]);
                double maxHp = Double.parseDouble(monsterData[2]);
                double currHp = Double.parseDouble(monsterData[3]);
                double exp = Double.parseDouble(monsterData[4]);
                Element element = Element.valueOf(monsterData[5]);
                double attackPower = Double.parseDouble(monsterData[6]);
                double defense = Double.parseDouble(monsterData[7]);
                double monsterGold = Double.parseDouble(monsterData[8]);

                monsters[i] = new Monster(monsterName, level, maxHp, currHp, exp, element, attackPower,
                        defense, monsterGold);
            }

            data = reader.readLine();
            splitData = data.split(";");
            Item[] items = new Item[splitData.length];
            for (int i = 0; i < splitData.length; i++) {
                if (splitData[i].equals("null")) {
                    continue;
                }

                String[] itemData = splitData[i].split(",");
                String type = itemData[0].split(" ")[2];
                String itemName = itemData[1];
                int value = Integer.parseInt(itemData[2]);
                double price = Double.parseDouble(itemData[3]);

                if (type.equals("Health")) {
                    items[i] = new HealthPotion(value, price, itemName);
                } else if (type.equals("Elemental")) {
                    items[i] = new ElementalPotion(value, price, itemName);
                }
            }

            data = reader.readLine();

            // No homebase monsters
            if (data == null) {
                Player player = new Player(name, monsters, gold, items);
                Homebase homebase = new Homebase(player);

                setPLAYER(player);
                setHOMEBASE(homebase);

                file.close();
                return;
            }

            splitData = data.split(";");
            Monster[] homebaseMonsters = new Monster[splitData.length];
            for (int i = 0; i < splitData.length; i++) {
                if (splitData[i].equals("null")) {
                    continue;
                }

                String[] monsterData = splitData[i].split(",");
                String monsterName = monsterData[0];
                int level = Integer.parseInt(monsterData[1]);
                double maxHp = Double.parseDouble(monsterData[2]);
                double currHp = Double.parseDouble(monsterData[3]);
                double exp = Double.parseDouble(monsterData[4]);
                Element element = Element.valueOf(monsterData[5]);
                double attackPower = Double.parseDouble(monsterData[6]);
                double defense = Double.parseDouble(monsterData[7]);
                double monsterGold = Double.parseDouble(monsterData[8]);

                homebaseMonsters[i] = new Monster(monsterName, level, maxHp, currHp, exp, element, attackPower,
                        defense, monsterGold);
            }

            Player player = new Player(name, monsters, gold, items);
            Homebase homebase = new Homebase(player, homebaseMonsters);

            setPLAYER(player);
            setHOMEBASE(homebase);

            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public static void saveGame(String namaFile) {
        String name = PLAYER.getName();
        double gold = PLAYER.getGold();

        String[] monsters = new String[getPlayerMonsters().length];
        for (int i = 0; i < getPlayerMonsters().length; i++) {
            monsters[i] = getPlayerMonster(i).getName() + "," + getPlayerMonster(i).getLevel() + ","
                    + getPlayerMonster(i).getMaxHp() + "," + getPlayerMonster(i).getCurrHp() + ","
                    + getPlayerMonster(i).getExp() + "," + getPlayerMonster(i).getElement() + ","
                    + getPlayerMonster(i).getAttackPower() + "," + getPlayerMonster(i).getDefense() + ","
                    + getPlayerMonster(i).getGold();
        }

        String[] items = new String[getPlayerItems().length];
        for (int i = 0; i < getPlayerItems().length; i++) {
            if (getPlayerItem(i) == null) {
                continue;
            }

            items[i] = getPlayerItem(i).getClass() + getPlayerItem(i).getName() + "," + getPlayerItem(i).getValue()
                    + ","
                    + getPlayerItem(i).getPrice();
        }
        
        String[] homebaseMonsters = new String[getHomebaseMonsters().length];
        for (int i = 0; i < getHomebaseMonsters().length; i++) {
            homebaseMonsters[i] = getHomebaseMonster(i).getName() + "," + getHomebaseMonster(i).getLevel() + ","
                    + getHomebaseMonster(i).getMaxHp() + "," + getHomebaseMonster(i).getCurrHp() + ","
                    + getHomebaseMonster(i).getExp() + "," + getHomebaseMonster(i).getElement() + ","
                    + getHomebaseMonster(i).getAttackPower() + "," + getHomebaseMonster(i).getDefense() + ","
                    + getHomebaseMonster(i).getGold();
        }

        String data = name + ";" + gold + ";";
        data += "\n";
        for (String monster : monsters) {
            data += monster + ";";
        }
        data += "\n";
        for (String item : items) {
            data += item + ";";
        }
        data += "\n";
        for (String monster : homebaseMonsters) {
            data += monster + ";";
        }

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

    public static Monster[] getPlayerMonsters() {
        return PLAYER.getMonsters();
    }

    public static Monster getPlayerMonster(int index) {
        return PLAYER.getMonsters()[index];
    }

    public static Monster getPlayerMonster(String name) {
        for (Monster monster : PLAYER.getMonsters()) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }

        return null;
    }

    public static Item getPlayerItem(int index) {
        return PLAYER.getItems()[index];
    }

    public static Item getPlayerItem(String name) {
        for (Item item : PLAYER.getItems()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }

    public static Item[] getPlayerItems() {
        return PLAYER.getItems();
    }

    public static Monster[] getHomebaseMonsters() {
        return HOMEBASE.getMonsters();
    }

    public static Monster getHomebaseMonster(int index) {
        return HOMEBASE.getMonsters()[index];
    }

    public static Monster getHomebaseMonster(String name) {
        for (Monster monster : HOMEBASE.getMonsters()) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }

        return null;
    }

    public static Monster getMonster(int index) {
        return MONSTERS[index];
    }

    public static Monster getMonster(String name) {
        for (Monster monster : MONSTERS) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }

        return null;
    }

    public static Item getItem(int index) {
        return ITEMS[index];
    }

    public static Item getItem(String name) {
        for (Item item : ITEMS) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
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
