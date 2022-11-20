package gameplay.cards.environment;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Environment;
import gameplay.cards.Minion;

import java.util.ArrayList;

public class Firestorm extends Environment {
    public Firestorm(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public int useAbilityOnRow(int rowIdx, Board board) {
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
