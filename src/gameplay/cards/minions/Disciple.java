package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class Disciple extends AbilityMinion {
    public Disciple(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(0);
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("Disciple");
    }

    /**
     * Use God's plan ability - increases allied minion life by 2.
     *
     * @param minion allied minion
     */
    public void useAbilityOn(final Minion minion) {
        minion.setHealth(minion.getHealth() + 2);
        this.setAttackState(true);
    }

    /**
     * Places the Disciple minion on the back row of the player's board.
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
