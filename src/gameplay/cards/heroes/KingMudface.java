package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class KingMudface extends Hero {
    public KingMudface(CardInput cardInput) {
        super(cardInput);
    }

    // earth born
    public void useAbility(Board board, int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            card.setHealth(card.getHealth() + 1);
        }
        // mark attack
        this.setAttackState(true);
    }
}
