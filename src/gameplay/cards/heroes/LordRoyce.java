package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class LordRoyce extends Hero {
    public LordRoyce(CardInput cardInput) {
        super(cardInput);
    }

    // sub-zero
    public void useAbility(Board board, int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        Card toFreeze = cards.get(0);
        int maxAttackDamage = toFreeze.getAttackDamage();
        for (Card card : cards) {
            if (card.getAttackDamage() > maxAttackDamage) {
                maxAttackDamage = card.getAttackDamage();
                toFreeze = card;
            }
        }
        toFreeze.setFrozen(true);
        // mark attack
        this.setAttackState(true);
    }
}
