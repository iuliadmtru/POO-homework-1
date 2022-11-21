package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class Miraj extends AbilityMinion {
    public Miraj(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("Miraj");
    }
    // skyjack
    public void useAbilityOn(Minion minion) {
        int newOpponentHealth = this.getHealth();
        this.setHealth(minion.getHealth());
        minion.setHealth(newOpponentHealth);
        this.setAttackState(true);
    }

    // place on front row
    public int placeOnBoardOf(Player player, Board board) {
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
