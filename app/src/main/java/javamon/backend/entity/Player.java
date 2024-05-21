package javamon.backend.entity;

import javamon.backend.entity.items.Item;
import javamon.backend.entity.places.Place;

public class Player {
    private String name;
    private Monster[] monsters;
    private double gold;
    private Item[] items;

    public Player(String name, Monster[] monsters, double gold) {
        this.name = name;
        this.monsters = monsters;
        this.gold = gold;
        this.items = new Item[10];
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

    public double getGold() {
        return gold;
    }

    public Item[] getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    
}
