package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero {
    public GeneralKocioraw(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Uses Blood Thirst ability - add 1 attack damage for all cards on the row.
     *
     * @param board  game board
     * @param rowIdx index of row on which the ability is used
     */
    public void useAbility(final Board board, final int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
        // mark attack
        this.setAttackState(true);
    }
}
