package gameplay.cards;

import fileio.CardInput;
import gameplay.Card;

public abstract class Minion extends Card {
    private boolean attackState = false;

    public Minion(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public Minion() {
    }

    /**
     * Returns the attack state of the minion (`true` if the hero has attacked).
     *
     * @return the attack state of the minion
     */
    public boolean hasAttacked() {
        return attackState;
    }

    /**
     * Sets minion attack state.
     *
     * @param attackState the attack state of the minion
     */
    public void setAttackState(final boolean attackState) {
        this.attackState = attackState;
    }

    /**
     * Resets minion attack state to `false`.
     */
    public void resetAttackState() {
        attackState = false;
    }

    /**
     * Attack an opponent's card.
     *
     * @param card opponent's card
     */
    public void attack(final Card card) {
        card.setHealth(card.getHealth() - this.getAttackDamage());
        this.setAttackState(true);
    }
}
