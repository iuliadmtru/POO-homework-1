package gameplay.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.Player;

@JsonIgnoreProperties({"attackDamage", "frozen"})
public abstract class Hero extends Card {
    private boolean attackState = false;

    public Hero(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setHealth(30);
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    /**
     * Returns the attack state of the hero (`true` if the hero has attacked).
     *
     * @return the attack state of the hero
     */
    public boolean hasAttacked() {
        return attackState;
    }

    /**
     * Sets hero attack state.
     *
     * @param attackState the attack state of the hero
     */
    public void setAttackState(final boolean attackState) {
        this.attackState = attackState;
    }

    /**
     * Resets hero attack state to `false`.
     */
    public void resetAttackState() {
        attackState = false;
    }

    /**
     * Uses hero ability.
     *
     * @param board  game board
     * @param rowIdx index of row on which the ability is used
     */
    public abstract void useAbility(Board board, int rowIdx);

    /**
     * Does nothing since hero cards should not be placed on the board.
     *
     * @param player player whose board the card is placed on
     * @param board  game board
     */
    public int placeOnBoardOf(final Player player, final Board board) {
        return 0;
    }
}
