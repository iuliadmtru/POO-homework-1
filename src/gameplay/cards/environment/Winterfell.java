package gameplay.cards.environment;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Environment;

import java.util.ArrayList;

public class Winterfell extends Environment {
    public Winterfell(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    /**
     * Freeze all cards on the row.
     *
     * @param rowIdx index of row on which the ability is used
     * @param board  game board
     */
    public int useAbilityOnRow(final int rowIdx, final Board board) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            card.setFrozen(true);
        }
        return 0;
    }
}
