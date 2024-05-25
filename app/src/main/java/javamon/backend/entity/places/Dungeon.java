package javamon.backend.entity.places;

import javamon.backend.Javamon;
import javamon.backend.entity.BattleArena;
import javamon.backend.entity.Monster;

public class Dungeon extends Place {
    private String name;

    public Dungeon(Monster[] monsters, String name) {
        super(monsters);
        this.name = name;
    }

    @Override
    public void exit() {
        System.out.println("Exiting Dungeon");

        Javamon.setPOSITION(Javamon.getHOMEBASE());
    }

    public void startWandering() {
        System.out.println("Wandering in " + name);
        
        Monster[] monsters = getMonsters();
        for (Monster monster : monsters) {
            System.out.println("Encountered " + monster.getName());

            int playerMonsters = 0;
            Monster[] playerMonstersArray = Javamon.getPlayerMonsters();
            for (Monster playerMonster : playerMonstersArray) {
                if (playerMonster.getCurrHp() > 0) {
                    playerMonsters++;
                }
            }

            if (playerMonsters == 0) {
                System.out.println("Player loses all monsters, exiting dungeon");
                exit();
                break;
            }

            int playerMonsterIndex = (int) Math.random() * 100 % 3;
            Monster playerMonster = Javamon.getPlayerMonster(playerMonsterIndex);

            System.out.println("Player monster: " + playerMonster.getName());

            BattleArena battleArena = new BattleArena(playerMonster, monster);
            Javamon.setBATTLEARENA(battleArena);
            battleArena.battle();

            if (battleArena.getWinner() == monster) {
                playerMonsters--;
                System.out.println("Player loses " + playerMonster.getName());
            } 

        }

        System.out.println("Wandering in " + name + " finished");
        exit();
    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
