package gameplay.cards;

import fileio.CardInput;
import gameplay.Card;

public abstract class Minion extends Card {
    private boolean attackState = false;

    public Minion(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public Minion() {
    }

    public boolean hasAttacked() {
        return attackState;
    }

    public void setAttackState(boolean attackState) {
        this.attackState = attackState;
    }

    public void resetAttackState() {
        attackState = false;
    }

    public void attack(Card card) {
        card.setHealth(card.getHealth() - this.getAttackDamage());
        this.setAttackState(true);
    }
}
