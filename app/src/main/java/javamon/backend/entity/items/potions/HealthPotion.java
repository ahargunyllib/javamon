package javamon.backend.entity.items.potions;

import javamon.backend.entity.items.Item;

public class HealthPotion extends Item {
    private String name;

    public HealthPotion(int value, String name) {
        super(value);
        this.name = name;
    }

    @Override
    public void use() {
        System.out.println("You used a " + name + " and gained " + value + " health points.");
    }
}
