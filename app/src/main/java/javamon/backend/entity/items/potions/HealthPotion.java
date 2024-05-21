package javamon.backend.entity.items.potions;

import javamon.backend.entity.items.Item;

public class HealthPotion extends Item {

    public HealthPotion(int value, double price,String name) {
        super(name, value, price);
    }

    @Override
    public void use() {
        System.out.println("You used a " + name + " and gained " + value + " health points.");
    }
}
