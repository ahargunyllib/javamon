package javamon.backend.entity.places;

import javamon.backend.Javamon;
import javamon.backend.entity.Monster;
import javamon.backend.exceptions.NoMonsterException;

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

    public Monster[] startWandering() throws NoMonsterException {
        Monster[] wildMonsters = getMonsters();
        Monster wildMonster = null;

        int cnt = 0;
        do {
            if (cnt > wildMonsters.length) {
                throw new NoMonsterException();
            }

            wildMonster = wildMonsters[(int) (Math.random() * wildMonsters.length)];
            cnt++;


        } while (wildMonster.getCurrHp() <= 0);

        Monster[] playerMonsters = Javamon.getPlayerMonsters();
        Monster playerMonster = null;

        cnt = 0;
        do {
            if (cnt > playerMonsters.length) {
                throw new NoMonsterException();
            }

            playerMonster = playerMonsters[(int) (Math.random() * playerMonsters.length)];
            cnt++;
            
        } while (playerMonster.getCurrHp() <= 0);


        return new Monster[] { playerMonster, wildMonster };
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
