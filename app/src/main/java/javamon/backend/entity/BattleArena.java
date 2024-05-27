package javamon.backend.entity;

import javamon.backend.Javamon;
import javamon.backend.entity.items.Item;
import javamon.backend.entity.items.potions.ElementalPotion;

public class BattleArena implements Action {
    private Monster playerMonster;
    private Monster wildMonster;
    private Monster winner;
    private ElementalPotion elementalPotion = null;

    public BattleArena(Monster playerMonster, Monster wildMonster) {
        this.playerMonster = playerMonster;
        this.wildMonster = wildMonster;
    }

    public void battle() {
        System.out.println("Battle between " + playerMonster.getName() + " and " + wildMonster.getName());

        while (playerMonster.getCurrHp() > 0 && wildMonster.getCurrHp() > 0) {
            System.out.println(playerMonster.getName() + " HP: " + playerMonster.getCurrHp());
            System.out.println(wildMonster.getName() + " HP: " + wildMonster.getCurrHp());

            if (playerMonster.getCurrHp() > 0) {
                System.out.println("Player's turn");
            }

            if (wildMonster.getCurrHp() > 0) {
                System.out.println("Wild monster's turn");
                wildMonsterTurn();
            }
        }

        if (playerMonster.getCurrHp() <= 0) {
            System.out.println("Player loses");
            winner = wildMonster;
        } else {
            System.out.println("Player wins");
            winner = playerMonster;

            // Add gold to player
            double gold = playerMonster.getGold();
            double playerGold = Javamon.getPLAYER().getGold();
            Javamon.getPLAYER().setGold(playerGold + gold);

            // Add exp to monster
            double exp = playerMonster.getExp();
            double monsterExp = wildMonster.getExp();
            playerMonster.setExp(exp + monsterExp);

            Javamon.getPLAYER().addMonster(wildMonster);
        }
    }

    public String wildMonsterTurn() {
        int choice = (int) (Math.random() * 100) % 3;

        if (choice == 0) {
            basicAttack(wildMonster, playerMonster);
            return "Basic attack";
        } else if (choice == 1) {
            elementalAttack(wildMonster, playerMonster);
            return "Elemental attack";
        } else {
            specialAttack(wildMonster, playerMonster);
            return "Special attack";
        }
    }

    public Monster getWinner() {
        return winner;
    }

    @Override
    public void basicAttack(Monster from, Monster to) {
        System.out.println(from.getName() + " attacks " + to.getName() + " with basic attack");
        double damage = from.getAttackPower() - to.getDefense();
        if (damage <= 0)
            damage = 1;
        to.setCurrHp(to.getCurrHp() - damage);

        System.out.println("Damage: " + damage);
    }

    @Override
    public void elementalAttack(Monster from, Monster to) {
        System.out.println(from.getName() + " attacks " + to.getName() + " with elemental attack");

        Element[] elements = new Element[] { Element.Api, Element.Angin, Element.Air, Element.Es, Element.Tanah };

        /*
         * 0: Api
         * 1: Angin
         * 2: Air
         * 3: Es
         * 4: Tanah
         */
        boolean[][] isEffective = new boolean[5][5];
        isEffective[0][0] = false;
        isEffective[1][1] = false;
        isEffective[2][2] = false;
        isEffective[3][3] = false;
        isEffective[4][4] = false;

        // api ke es
        isEffective[0][3] = true;

        // es ke angin
        isEffective[3][1] = true;

        // angin ke tanah
        isEffective[1][4] = true;

        // tanah ke air
        isEffective[4][2] = true;

        // air ke api
        isEffective[2][0] = true;

        Element fromElement = from.getElement();
        Element toElement = to.getElement();

        // Ambil index dari element monster
        int fromIndex = 0;
        int toIndex = 0;

        for (int i = 0; i < elements.length; i++) {
            if (fromElement == elements[i])
                fromIndex = i;

            if (toElement == elements[i])
                toIndex = i;
        }

        // Cek apakah elemental attack efektif
        if (isEffective[fromIndex][toIndex]) {
            System.out.println("Elemental attack is effective");
            double damage = from.getAttackPower() * 1.5 - to.getDefense();
            if (elementalPotion != null) {
                damage += elementalPotion.getValue();
            }
            if (damage <= 0)
                damage = 1;
            to.setCurrHp(to.getCurrHp() - damage);

            System.out.println("Damage: " + damage);
        } else {
            System.out.println("Elemental attack is not effective");
            double damage = from.getAttackPower() * 0.5 - to.getDefense();
            if (elementalPotion != null) {
                damage += elementalPotion.getValue();
            }
            if (damage <= 0)
                damage = 1;
            to.setCurrHp(to.getCurrHp() - damage);

            System.out.println("Damage: " + damage);
        }
    }

    @Override
    public boolean escape() {
        double missRate = 0.3;
        double random = Math.random();
        if (random < missRate) {
            System.out.println("Player fails to escape from battle");
            return false;
        }

        System.out.println("Player escapes from battle");
        winner = wildMonster;
        return true;
    }

    @Override
    public boolean specialAttack(Monster from, Monster to) {
        double missRate = 0.25;
        double random = Math.random();
        if (random < missRate) {
            System.out.println(from.getName() + " misses the special attack");
            return false;
        }        

        System.out.println(from.getName() + " attacks " + to.getName() + " with special attack");
        double damage = from.getAttackPower() * 2 - to.getDefense();
        if (damage <= 0)
            damage = 1;
        to.setCurrHp(to.getCurrHp() - damage);
        from.setCurrHp(from.getMaxHp() * 0.95);

        System.out.printf("Damage: %f\n", damage);
        return true;
    }

    @Override
    public void useItem(Item item) {
        System.out.println("Player uses " + item.getName());

        String[] itemNames = item.getName().split(" ");

        if (itemNames[1].equals("Health")) {
            if (playerMonster.getCurrHp() + item.getValue() > playerMonster.getMaxHp()) {
                playerMonster.setCurrHp(playerMonster.getMaxHp());
            } else {
                playerMonster.setCurrHp(playerMonster.getCurrHp() + item.getValue());
            }

            // remove the item
            Item[] items = Javamon.getPlayerItems();
            for (int i = 0; i < items.length; i++) {
                if (items[i].getName().equals(item.getName())) {
                    items[i] = null;
                    break;
                }
            }
        } else if (itemNames[1].equals("Elemental")) {
            elementalPotion = (ElementalPotion) item;

            // remove the item
            Item[] items = Javamon.getPlayerItems();
            for (int i = 0; i < items.length; i++) {
                if (items[i].getName().equals(item.getName())) {
                    items[i] = null;
                    break;
                }
            }
        }

    }

    public Monster getPlayerMonster() {
        return playerMonster;
    }

    public Monster getWildMonster() {
        return wildMonster;
    }

}
