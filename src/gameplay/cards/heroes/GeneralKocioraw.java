package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero {
    public GeneralKocioraw(CardInput cardInput) {
        super(cardInput);
    }

    // blood thirst
    public void useAbility(Board board, int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
        // mark attack
        this.setAttackState(true);
    }
}
