package javamon.backend.entity.places;

import javamon.backend.entity.Monster;

public class Dungeon extends Place {
    private String name;

    public Dungeon(Monster[] monsters, String name) {
        super(monsters);
        this.name = name;
    }

    @Override
    public void exit() {
        // TODO: Implement this method
    }

    public void startWandering() {
        // TODO: Implement this method
    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "name='" + name + '\'' +
                '}';
    }
}
