package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class TheRipper extends AbilityMinion {
    public TheRipper(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("The Ripper");
    }

    /**
     * Use Weak Knees ability - decreases opponent minion's attack damage by 2.
     *
     * @param minion opponent minion
     */
    public void useAbilityOn(final Minion minion) {
        if (minion.getAttackDamage() > 0) {
            minion.setAttackDamage(minion.getAttackDamage() - 2);
        }
        this.setAttackState(true);
    }

    /**
     * Places The Ripper minion on the front row of the player's board.
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
