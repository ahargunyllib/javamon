package javamon.backend.entity;

import javamon.backend.entity.places.Place;

public class Player {
    private String name;
    private Monster[] monsters;

    public Player(String name, Monster[] monsters) {
        this.name = name;
        this.monsters = monsters;
    }

    public void addMonster(Monster monster) {
        // TODO: Implement this method
    }

    public void enterPlace(Place place) {
        // TODO: Implement this method
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public Monster[] getMonsters() {
        return monsters;
    }
}
