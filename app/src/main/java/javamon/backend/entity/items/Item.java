package javamon.backend.entity.items;

public abstract class Item {
    protected String name;
    protected int value;
    protected double price;

    public Item(String name, int value, double price) {
        this.name = name;
        this.value = value;
        this.price = price;
    }

    public abstract void use();

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public double getPrice() {
        return price;
    }
}
