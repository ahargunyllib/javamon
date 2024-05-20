package javamon.backend.entity;

public class Monster {
    private String name;
    private int level;
    private double hp;
    private double exp;
    private Element element;
    private double attackPower;
    private double defense;

    public Monster(String name, int level, double hp, double exp, Element element, double attackPower, double defense) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.exp = exp;
        this.element = element;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public void levelUp(int target) {
        // TODO: Implement this method
    }

    public void hangeElement(Element element) {
        // TODO: Implement this method
    }

    public void restoreHp(int target) {
        // TODO: Implement this method
    }

    public String getElement() {
        return element.toString();
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", hp=" + hp +
                ", exp=" + exp +
                ", element=" + element +
                ", attackPower=" + attackPower +
                ", defense=" + defense +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(double attackPower) {
        this.attackPower = attackPower;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

}
