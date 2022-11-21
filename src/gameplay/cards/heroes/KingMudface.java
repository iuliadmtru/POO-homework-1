package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class KingMudface extends Hero {
    public KingMudface(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Uses Earth Born ability - add 1 health for all cards on the row.
     *
     * @param board  game board
     * @param rowIdx index of row on which the ability is used
     */
    public void useAbility(final Board board, final int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            card.setHealth(card.getHealth() + 1);
        }
        // mark attack
        this.setAttackState(true);
    }
}
