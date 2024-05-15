package javamon.backend.entity.places;

import javamon.backend.entity.Monster;
import javamon.backend.entity.Player;

public class Homebase extends Place {
    private Player player;

    public Homebase(Monster[] monsters, Player player) {
        super(monsters);
        this.player = player;
    }

    @Override
    public void exit() {
        // TODO: Implement this method
    }

    public void saveMonster(Monster monster) {
        // TODO: Implement this method
    }

    public void levelUp(Monster monster) {
        // TODO: Implement this method
    }

    public void restoreHp(Monster monster) {
        // TODO: Implement this method
    }

    public void evolve(Monster monster) {
        // TODO: Implement this method
    }

    public void addItem() {
        // TODO: Implement this method
    }
}
