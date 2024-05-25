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
        }
    }

    private void wildMonsterTurn() {
        int choice = (int) (Math.random() * 100) % 3;

        if (choice == 0) {
            basicAttack(wildMonster, playerMonster);
        } else if (choice == 1) {
            elementalAttack(wildMonster, playerMonster);
        } else {
            specialAttack(wildMonster, playerMonster);
        }
    }

    public Monster getWinner() {
        return winner;
    }

    @Override
    public void basicAttack(Monster from, Monster to) {
        System.out.println(from.getName() + " attacks " + to.getName() + " with basic attack");
        double damage = from.getAttackPower() - to.getDefense();
        to.setCurrHp(to.getCurrHp() - damage);

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

        // api bisa ke angin dan angin bisa ke api
        isEffective[0][1] = true;
        isEffective[1][0] = true;

        // angin bisa ke air dan air bisa ke api
        isEffective[1][2] = true;
        isEffective[2][1] = true;

        // air bisa ke es dan es bisa ke air
        isEffective[2][3] = true;
        isEffective[3][2] = true;

        // es bisa ke tanah dan tanah bisa ke es
        isEffective[3][4] = true;
        isEffective[4][3] = true;

        // tanah bisa ke api dan api bisa ke tanah
        isEffective[4][0] = true;
        isEffective[0][4] = true;

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
            to.setCurrHp(to.getCurrHp() - damage);
        } else {
            System.out.println("Elemental attack is not effective");
            double damage = from.getAttackPower() * 0.5 - to.getDefense();
            if (elementalPotion != null) {
                damage += elementalPotion.getValue();
            }
            to.setCurrHp(to.getCurrHp() - damage);
        }
    }

    @Override
    public void escape() {
        System.out.println("Player escapes from battle");
        winner = wildMonster;
    }

    @Override
    public void specialAttack(Monster from, Monster to) {
        System.out.println(from.getName() + " attacks " + to.getName() + " with special attack");
        double damage = from.getAttackPower() * 2 - to.getDefense();
        to.setCurrHp(to.getCurrHp() - damage);
        
        from.setCurrHp(from.getMaxHp() * 0.95);
    }

    @Override
    public void useItem(Item item) {
        System.out.println("Player uses " + item.getName());

        String[] itemNames = item.getName().split(" ");

        if (itemNames[1].equals("Health")) {
            playerMonster.setCurrHp(playerMonster.getCurrHp() + item.getValue());

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

}
