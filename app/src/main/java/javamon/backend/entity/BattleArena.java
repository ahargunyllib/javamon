package javamon.backend.entity;

import javamon.backend.entity.items.Item;

public class BattleArena implements Action {
    private Monster playerMonster;
    private Monster wildMonster;
    private Monster winner;

    public BattleArena(Monster playerMonster, Monster wildMonster) {
        this.playerMonster = playerMonster;
        this.wildMonster = wildMonster;
    }

    public void battle() {
        // TODO: Implement this method
    }

    public void getWinner() {
        // TODO: Implement this method
    }

    @Override
    public void basicAttack(Monster from, Monster to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void elementalAttack(Monster from, Monster to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void escape() {
        // TODO Auto-generated method stub

    }

    @Override
    public void specialAttack(Monster from, Monster to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void useItem(Item item) {
        // TODO Auto-generated method stub

    }

}
