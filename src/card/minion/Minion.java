package card.minion;

import card.FightCard;

abstract public class Minion extends FightCard {
    public BoardPlacementInfo boardPlacement;
    private int attackDamage;

    private boolean hasAttack = true;
    protected int defendPriority;

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void increaseAttackDamageBy(int amount) {
        attackDamage += amount;
    }

    public void decreaseAttackDamageBy(int amount) {
        attackDamage -= amount;
    }

    public boolean canAttack() {
        return (hasAttack && !isFrozen());
    }

    public void attack(FightCard opponent) {
        opponent.defend(attackDamage);
        hasAttack = false;
    }
}
