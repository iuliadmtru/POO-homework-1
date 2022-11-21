package gameplay.cards.heroes;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Hero;

import java.util.ArrayList;

public class EmpressThorina extends Hero {
    public EmpressThorina(CardInput cardInput) {
        super(cardInput);
    }

    // low blow
    public void useAbility(Board board, int rowIdx) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        Card toKill = cards.get(0);
        int maxHealth = toKill.getHealth();
        for (Card card : cards) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                toKill = card;
            }
        }
        board.removeCard(toKill, rowIdx);
        // mark attack
        this.setAttackState(true);
    }
}
