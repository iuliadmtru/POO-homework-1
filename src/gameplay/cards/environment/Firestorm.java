package gameplay.cards.environment;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Environment;
import gameplay.cards.Minion;

import java.util.ArrayList;

public class Firestorm extends Environment {
    public Firestorm(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    /**
     * Decrease the health of all minions on the row by 1.
     *
     * @param rowIdx index of row on which the ability is used
     * @param board  game board
     */
    public int useAbilityOnRow(final int rowIdx, final Board board) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            if (card instanceof Minion) {
                card.setHealth(card.getHealth() - 1);
            }
        }
        // remove dead cards
        board.removeDeadCards(rowIdx);
        return 0;
    }
}
