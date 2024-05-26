package javamon.backend.entity;

import javamon.backend.entity.items.Item;

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

        System.out.printf("Player %s created\n", this);
    }

    public Player(String name, Monster[] monsters, double gold, Item[] items) {
        this.name = name;
        this.monsters = monsters;
        this.gold = gold;
        this.items = items;

        System.out.printf("Player %s created\n", this);
    }

    public void addMonster(Monster monster) {
        Monster[] newMonsters = new Monster[monsters.length + 1];
        for (int i = 0; i < monsters.length; i++) {
            newMonsters[i] = monsters[i];
        }
        newMonsters[monsters.length] = monster;

        monsters = newMonsters;

        System.out.println("Monster added to player");
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", monsters=" + monsters +
                ", gold=" + gold +
                ", items=" + items +
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
