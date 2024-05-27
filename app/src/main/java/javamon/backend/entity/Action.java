package javamon.backend.entity;

import javamon.backend.entity.items.Item;

public interface Action {
    public void basicAttack(Monster from, Monster to);

    public boolean specialAttack(Monster from, Monster to);

    public void elementalAttack(Monster from, Monster to);

    public void useItem(Item item);

    public boolean escape();
} 
