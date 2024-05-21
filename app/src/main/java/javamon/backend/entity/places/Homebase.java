package javamon.backend.entity.places;

import javamon.backend.entity.Element;
import javamon.backend.entity.Monster;
import javamon.backend.entity.Player;
import javamon.backend.entity.items.Item;
import javamon.backend.exceptions.CannotEvolveException;
import javamon.backend.exceptions.CannotHealException;
import javamon.backend.exceptions.NotEnoughExpException;
import javamon.backend.exceptions.NotEnoughGoldException;

public class Homebase extends Place {
    private Player player;

    public Homebase(Player player) {
        super(new Monster[1]);
        this.player = player;
    }

    @Override
    public void exit() {
        // TODO: Implement this method
    }

    public void saveMonster(Monster[] monsters) {
        // hilangkan monster dari player
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

        // buat array baru tanpa monster yang disave
        Monster[] newMonsters = new Monster[playerMonsters.length - cnt];
        int idx = 0;
        for (int i = 0; i < playerMonsters.length; i++) {
            if (playerMonsters[i] != null) {
                newMonsters[idx] = playerMonsters[i];
                idx++;
            }
        }

        // set monsters baru ke player
        player.setMonsters(newMonsters);
        // set monsters ke homebase
        setMonsters(monsters);
    }

    public void restoreMonster(Monster[] monsters) {
        // hilangkan monster dari homebase
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

        // buat array baru tanpa monster yang direstore
        Monster[] newMonsters = new Monster[homebaseMonsters.length - cnt];
        int idx = 0;
        for (int i = 0; i < homebaseMonsters.length; i++) {
            if (homebaseMonsters[i] != null) {
                newMonsters[idx] = homebaseMonsters[i];
                idx++;
            }
        }

        // set monsters baru ke homebase
        setMonsters(newMonsters);
        // set monsters ke player
        Monster[] playerMonsters = player.getMonsters();
        Monster[] newPlayerMonsters = new Monster[playerMonsters.length + cnt];
        for (int i = 0; i < playerMonsters.length; i++) {
            newPlayerMonsters[i] = playerMonsters[i];
        }
        for (int i = 0; i < monsters.length; i++) {
            newPlayerMonsters[playerMonsters.length + i] = monsters[i];
        }
        player.setMonsters(newPlayerMonsters);
    }

    public void levelUp(Monster monster) throws NotEnoughExpException {
        int currLevel = monster.getLevel();
        int neededXp = currLevel * 10;

        if (monster.getExp() >= neededXp) {
            monster.levelUp(neededXp);
            System.out.printf("%s leveled up\n", monster);
        } else {
            throw new NotEnoughExpException("Not enough exp to level up");
        }
    }

    public void restoreHp(Monster monster) throws CannotHealException, NotEnoughGoldException {
        double currHp = monster.getCurrHp();
        double maxHp = monster.getMaxHp();

        if (currHp == maxHp) {
            throw new CannotHealException("Monster is already at full health");
        } else {
            if (player.getGold() >= 50) {
                player.setGold(player.getGold() - 50);
                monster.restoremaxHp();
            } else {
                throw new NotEnoughGoldException("Not enough gold to heal monster");
            }
        }
    }

    public void evolve(Monster monster, Element element) throws CannotEvolveException {
        Element[] elements = new Element[]{Element.Api, Element.Angin, Element.Air, Element.Es, Element.Tanah};

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

        int currIndex = 0;
        int elementIndex = 0;
        for (int i = 0; i < elements.length; i++) {
            if (currElement == elements[i]) {
                currIndex = i;
            }

            if (element == elements[i]) {
                elementIndex = i;
            }
        }

        if (canEvolve[currIndex][elementIndex]) {;
            // monster.setName(""); // TODO: set the name of the monster
            monster.changeElement(element);
            System.out.printf("%s evolved\n", monster);
        } else {
            throw new CannotEvolveException("Cannot evolve to the specified element");
        }

    }

    public void addItem(Item item) {
        double playerGold = player.getGold();
        double itemPrice = item.getPrice();

        if (player.getItems()[9] != null) {
            // throw inventory full exception
        }

        if (playerGold >= itemPrice) {
            for (int i = 0; i < player.getItems().length; i++) {
                if (player.getItems()[i] == null) {
                    player.getItems()[i] = item;
                    break;
                }
            }
            player.setGold(playerGold - itemPrice);
        } else {
            // throw not enough gold exception
        }
    }
}
