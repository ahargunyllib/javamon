package javamon.backend.entity.items;

public abstract class Item {
    protected int value;

    public Item(int value) {
        this.value = value;
    }

    public abstract void use();
}
