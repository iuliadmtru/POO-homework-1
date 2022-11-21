package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class LordRoyce extends Hero {
    public LordRoyce(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Uses Sub-Zero ability - freeze the card with the highest attack damage on the row.
     *
     * @param board  game board
     * @param rowIdx index of row on which the ability is used
     */
    public void useAbility(final Board board, final int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        if (!cards.isEmpty()) {
            Card toFreeze = cards.get(0);
            int maxAttackDamage = toFreeze.getAttackDamage();
            for (Card card : cards) {
                if (card.getAttackDamage() > maxAttackDamage) {
                    maxAttackDamage = card.getAttackDamage();
                    toFreeze = card;
                }
            }
            toFreeze.setFrozen(true);
        }
        // mark attack
        this.setAttackState(true);
    }
}
