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
    private boolean[] evolved;

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
        this.evolved = new boolean[100];

        System.out.printf("Monster %s created\n", this);
    }

    public Monster(String name, int level, double maxHp, double currHp, double exp, Element element, double attackPower,
            double defense, double gold) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.currHp = currHp;
        this.exp = exp;
        this.element = element;
        this.attackPower = attackPower;
        this.defense = defense;
        this.gold = gold;
        this.evolved = new boolean[100];

        System.out.printf("Monster %s created\n", this);
    }

    public void levelUp(int neededExp) {
        int hpPerLevel = 50;
        int expPerLevel = 25;
        int attackPowerPerLevel = 25;
        int defensePerLevel = 20;

        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(this.getMaxHp() + hpPerLevel);
        this.setExp(this.getExp() + expPerLevel);
        this.setAttackPower(this.getAttackPower() + attackPowerPerLevel);
        this.setDefense(this.getDefense() + defensePerLevel);

        this.setExp(this.getExp() - neededExp);

        System.out.printf("%s leveled up\n", this);
    }

    public void changeElement(Element element) {
        this.setElement(element);

        System.out.printf("%s changed element to %s\n", this, element);
    }

    public void restoremaxHp() {
        this.setCurrHp(this.getMaxHp());

        System.out.printf("%s's hp restored\n", this);
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

    public boolean[] getEvolved() {
        return evolved;
    }

    public void setEvolved(int level) {
        this.evolved[level] = true;
    }
}
