package javamon.backend.entity.places;

import javamon.backend.entity.Monster;

public abstract class Place {
    protected Monster[] monsters;

    public Place(Monster[] monsters) {
        this.monsters = monsters;
    }

    public abstract void exit();
}
