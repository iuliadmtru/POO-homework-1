package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class EmpressThorina extends Hero {
    public EmpressThorina(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Uses Low Blow ability - destroy the card with the highest health on the row.
     *
     * @param board  game board
     * @param rowIdx index of row on which the ability is used
     */
    public void useAbility(final Board board, final int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        if (!cards.isEmpty()) {
            Card toKill = cards.get(0);
            int maxHealth = toKill.getHealth();
            for (Card card : cards) {
                if (card.getHealth() > maxHealth) {
                    maxHealth = card.getHealth();
                    toKill = card;
                }
            }
            board.removeCard(toKill, rowIdx);
        }
        // mark attack
        this.setAttackState(true);
    }
}
