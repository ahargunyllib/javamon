package javamon.backend.entity.items;

public abstract class Item {
    protected int value;
    protected double price;

    public Item(int value, double price) {
        this.value = value;
        this.price = price;
    }

    public abstract void use();

    public int getValue() {
        return value;
    }

    public double getPrice() {
        return price;
    }
}
