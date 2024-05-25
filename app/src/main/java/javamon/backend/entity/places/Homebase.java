package javamon.backend.entity.places;

import java.util.*;

import javamon.backend.Javamon;
import javamon.backend.entity.Element;
import javamon.backend.entity.Monster;
import javamon.backend.entity.Player;
import javamon.backend.entity.items.Item;
import javamon.backend.exceptions.CannotEvolveException;
import javamon.backend.exceptions.CannotHealException;
import javamon.backend.exceptions.FullInventoryException;
import javamon.backend.exceptions.NoItemException;
import javamon.backend.exceptions.NotEnoughExpException;
import javamon.backend.exceptions.NotEnoughGoldException;

public class Homebase extends Place {
    private Player player;

    public Homebase(Player player) {
        super(new Monster[0]);
        this.player = player;

        System.out.println("Welcome to Homebase");
    }

    public Homebase(Player player, Monster[] monsters) {
        super(monsters);
        this.player = player;

        System.out.println("Homebase with monsters created");
    }

    @Override
    public void exit() {
        System.out.println("Exiting Homebase");

        Javamon.setPOSITION(null);
    }

    public void saveMonster(Monster[] monsters) {
        // Hilangkan monster dari player
        int cnt = 0;
        Monster[] playerMonsters = player.getMonsters();
        for (int i = 0; i < playerMonsters.length; i++) {
            for (int j = 0; j < monsters.length; j++) {
                if (playerMonsters[i] == monsters[j]) {
                    playerMonsters[i] = null;
                    cnt++;
                    break;
                }
            }
        }

        // Buat array baru tanpa monster yang disave
        Monster[] newMonsters = new Monster[playerMonsters.length - cnt];
        int idx = 0;
        for (int i = 0; i < playerMonsters.length; i++) {
            if (playerMonsters[i] != null) {
                newMonsters[idx] = playerMonsters[i];
                idx++;

                System.out.printf("%s saved\n", playerMonsters[i]);
            }
        }

        // Set monsters baru ke player
        player.setMonsters(newMonsters);
        
        // Set monsters yang disave ke homebase
        setMonsters(monsters);

        System.out.println("Successfully saved monsters");
    }

    public void restoreMonster(Monster[] monsters) {
        // Hilangkan monster dari homebase
        int cnt = 0;
        Monster[] homebaseMonsters = getMonsters();
        for (int i = 0; i < homebaseMonsters.length; i++) {
            for (int j = 0; j < monsters.length; j++) {
                if (homebaseMonsters[i] == monsters[j]) {
                    homebaseMonsters[i] = null;
                    cnt++;
                    break;
                }
            }
        }

        // Buat array baru tanpa monster yang direstore
        Monster[] newMonsters = new Monster[homebaseMonsters.length - cnt];
        int idx = 0;
        for (int i = 0; i < homebaseMonsters.length; i++) {
            if (homebaseMonsters[i] != null) {
                newMonsters[idx] = homebaseMonsters[i];
                idx++;
            }
        }

        // Set monsters lama ke homebase
        setMonsters(newMonsters);

        Monster[] playerMonsters = player.getMonsters();
        Monster[] newPlayerMonsters = new Monster[playerMonsters.length + cnt];

        // Copy monsters lama ke array baru
        for (int i = 0; i < playerMonsters.length; i++) {
            newPlayerMonsters[i] = playerMonsters[i];
        }

        // Set monsters yang di restore ke player
        for (int i = 0; i < monsters.length; i++) {
            newPlayerMonsters[playerMonsters.length + i] = monsters[i];
            System.out.printf("%s restored\n", monsters[i]);
        }
        player.setMonsters(newPlayerMonsters);

        System.out.println("Successfully restored monsters");
    }

    public void levelUp(Monster monster) throws NotEnoughExpException {
        int currLevel = monster.getLevel();
        double monsterExp = monster.getExp();

        int expPerLevel = 10;
        int neededXp = currLevel * expPerLevel;

        // Cek apakah monster punya cukup exp untuk level up
        if (monsterExp < neededXp) {
            throw new NotEnoughExpException();
        }

        monster.levelUp(neededXp);
        System.out.printf("%s leveled up\n", monster);
    }

    public void restoreHp(Monster monster) throws CannotHealException, NotEnoughGoldException {
        double currHp = monster.getCurrHp();
        double maxHp = monster.getMaxHp();

        int hpPrice = 10;
        double playerGold = player.getGold();
        double hpToRestore = maxHp - currHp;
        double price = hpToRestore * hpPrice;

        // Hp monster penuh
        if (currHp == maxHp) {
            throw new CannotHealException();
        }

        // Cek apakah player punya cukup gold
        if (playerGold < price) {
            throw new NotEnoughGoldException();
        }

        // Kurangi gold player
        player.setGold(playerGold - price);
        System.out.printf("Player gold: %f\n", playerGold - price);

        // Restore hp monster
        monster.setCurrHp(maxHp);
        System.out.printf("%s's hp restored\n", monster);
    }

    public void evolve(Monster monster, Element element) throws CannotEvolveException {
        Element[] elements = new Element[] { Element.Api, Element.Angin, Element.Air, Element.Es, Element.Tanah };

        /*
         * 0: Api
         * 1: Angin
         * 2: Air
         * 3: Es
         * 4: Tanah
         */
        boolean[][] canEvolve = new boolean[5][5];
        canEvolve[0][0] = false;
        canEvolve[1][1] = false;
        canEvolve[2][2] = false;
        canEvolve[3][3] = false;
        canEvolve[4][4] = false;

        // api bisa ke angin dan angin bisa ke api
        canEvolve[0][1] = true;
        canEvolve[1][0] = true;

        // angin bisa ke air dan air bisa ke api
        canEvolve[1][2] = true;
        canEvolve[2][1] = true;

        // air bisa ke es dan es bisa ke air
        canEvolve[2][3] = true;
        canEvolve[3][2] = true;

        // es bisa ke tanah dan tanah bisa ke es
        canEvolve[3][4] = true;
        canEvolve[4][3] = true;

        // tanah bisa ke api dan api bisa ke tanah
        canEvolve[4][0] = true;
        canEvolve[0][4] = true;

        Element currElement = monster.getElement();

        // Ambil index dari element yang mau di-evolve dan element monster
        int currIndex = 0;
        int elementIndex = 0;
        for (int i = 0; i < elements.length; i++) {
            if (currElement == elements[i])
                currIndex = i;

            if (element == elements[i])
                elementIndex = i;

        }

        // Cek apakah monster bisa di-evolve ke element yang diinginkan
        if (!canEvolve[currIndex][elementIndex]) {
            throw new CannotEvolveException();
        }

        // monster.setName(""); // TODO: set the name of the monster
        monster.changeElement(element);
        System.out.printf("%s evolved\n", monster);
    }

    public void buyItem(Map<Item, Integer> items)
            throws FullInventoryException, NotEnoughGoldException, NoItemException {
        double playerGold = player.getGold();

        int cnt = 0;
        for (Item item : items.keySet()) {
            cnt++;
        }

        // Cek apakah ada item yang dibeli
        if (cnt == 0) {
            throw new NoItemException();
        }

        int playerItem = 0;
        for (Item item : player.getItems()) {
            if (item != null)
                playerItem++;
        }

        // Cek apakah inventory player penuh
        if (playerItem + cnt > 10)
            throw new FullInventoryException();

        int itemPrice = 0;
        for (Item item : items.keySet()) {
            itemPrice += item.getPrice() * items.get(item);
        }

        // Cek apakah player punya cukup gold
        if (playerGold < itemPrice)
            throw new NotEnoughGoldException();

        // Tambah item ke inventory player
        Item[] playerItems = player.getItems();
        for (Item item : items.keySet()) {
            int count = items.get(item);

            for (int i = 0; i < count; i++) {
                for (int j = 0; j < playerItems.length; j++) {
                    if (playerItems[j] == null) {
                        playerItems[j] = item;
                        break;
                    }
                }
            }
        }

        // Kurangi gold player
        player.setGold(playerGold - itemPrice);
        System.out.println("Items bought");
        System.out.printf("Player gold: %f\n", playerGold - itemPrice);
    }
    
    public void goToDungeon(Dungeon dungeon) {
        System.out.println("Going to Dungeon " + dungeon.getName() + "...");
    
        Javamon.setPOSITION(dungeon);
        
        dungeon.startWandering();
    }
}
