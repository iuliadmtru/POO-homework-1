package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Player;
import gameplay.cards.Minion;

public class TheCursedOne extends Minion implements SpecialAbility {
    public TheCursedOne(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(0);
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("The Cursed One");
    }
    // shapeshift
    public void useAbilityOn(Minion minion) {
        int newOpponentAttackDamage = minion.getHealth();
        minion.setHealth(minion.getAttackDamage());
        minion.setAttackDamage(newOpponentAttackDamage);
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
