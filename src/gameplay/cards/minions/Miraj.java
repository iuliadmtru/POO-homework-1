package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class Miraj extends AbilityMinion {
    public Miraj(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("Miraj");
    }

    /**
     * Use Skijack ability - swaps its life with the life of an opponent minion.
     *
     * @param minion opponent minion
     */
    public void useAbilityOn(final Minion minion) {
        int newOpponentHealth = this.getHealth();
        this.setHealth(minion.getHealth());
        minion.setHealth(newOpponentHealth);
        this.setAttackState(true);
    }

    /**
     * Places the Miraj minion on the front row of the player's board.
     *
     * @param player player whose board the card is placed on
     * @param board  game board
     * @return       0 if successful, 1 if the target row is full
     */
    public int placeOnBoardOf(final Player player, final Board board) {
        int row = player.getPlayerIdx() == 1 ? 2 : 1;
        if (board.isFull(row)) {
            return 1; // ROW_IS_FULL error code
        }
        // add the card
        board.addCard(this, row);
        // decrease player mana
        player.decreaseManaBy(this.getMana());
        return 0;
    }
}
