package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class Disciple extends AbilityMinion {
    public Disciple(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(0);
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("Disciple");
    }

    // god's plan
    public void useAbilityOn(Minion minion) {
        minion.setHealth(minion.getHealth() + 2);
        this.setAttackState(true);
    }

    // place on back row
    public int placeOnBoardOf(Player player, Board board) {
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
