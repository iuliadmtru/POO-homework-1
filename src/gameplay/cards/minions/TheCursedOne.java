package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class TheCursedOne extends AbilityMinion {
    public TheCursedOne(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(0);
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("The Cursed One");
    }

    /**
     * Use Shapeshift ability - swaps an opponent minion's health and attack damage.
     *
     * @param minion opponent minion
     */
    public void useAbilityOn(final Minion minion) {
        int newOpponentAttackDamage = minion.getHealth();
        minion.setHealth(minion.getAttackDamage());
        minion.setAttackDamage(newOpponentAttackDamage);
        this.setAttackState(true);
    }

    /**
     * Places The Cursed One minion on the back row of the player's board.
     *
     * @param player player whose board the card is placed on
     * @param board  game board
     * @return       0 if successful, 1 if the target row is full
     */
    public int placeOnBoardOf(final Player player, final Board board) {
        int row = player.getPlayerIdx() == 1 ? 3 : 0;
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
