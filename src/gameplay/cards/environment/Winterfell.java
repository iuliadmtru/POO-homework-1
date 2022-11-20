package gameplay.cards.environment;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Environment;

import java.util.ArrayList;

public class Winterfell extends Environment {
    public Winterfell(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public int useAbilityOnRow(int rowIdx, Board board) {
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        for (Card card : cards) {
            card.setFrozen(true);
        }
        return 0;
    }
}
