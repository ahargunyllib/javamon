package javamon.backend.entity;

public class Monster {
    private String name;
    private int level;
    private double maxHp;
    private double currHp;
    private double exp;
    private Element element;
    private double attackPower;
    private double defense;
    private double gold;

    public Monster(String name, int level, double maxHp, double exp, Element element, double attackPower,
            double defense, double gold) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.currHp = maxHp;
        this.exp = exp;
        this.element = element;
        this.attackPower = attackPower;
        this.defense = defense;
        this.gold = gold;
    }

    public void levelUp(int neededExp) {
        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(this.getMaxHp() + 50);
        this.setExp(this.getExp() + 25);
        this.setAttackPower(this.getAttackPower() + 25);
        this.setDefense(this.getDefense() + 20);

        this.setExp(this.getExp() - neededExp);
    }

    public void changeElement(Element element) {
        this.setElement(element);
    }

    public void restoremaxHp() {
        this.setCurrHp(this.getMaxHp());
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", maxHp=" + maxHp +
                ", currHp=" + currHp +
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

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
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

    public double getCurrHp() {
        return currHp;
    }

    public void setCurrHp(double currHp) {
        this.currHp = currHp;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }
}
