package javamon.backend.entity.items.potions;

import javamon.backend.entity.items.Item;

public class ElementalPotion extends Item {
    public ElementalPotion(int value, double price, String name) {
        super(name, value, price);
    }

    @Override
    public void use() {
        System.out.println("You used a " + name + " and gained " + value + " elemental points.");
    }
}
