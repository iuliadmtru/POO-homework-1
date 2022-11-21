package gameplay.cards.environment;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Environment;
import gameplay.cards.Minion;

import java.util.ArrayList;

public class HeartHound extends Environment {
    public HeartHound(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public int useAbilityOnRow(int rowIdx, Board board) {
        // check if the mirror row is full
        int mirrorRow;
        switch (rowIdx) {
            case 0 -> mirrorRow = 3;
            case 1 -> mirrorRow = 2;
            case 2 -> mirrorRow = 1;
            case 3 -> mirrorRow = 0;
            default -> mirrorRow = -1;
        }
        if (board.isFull(mirrorRow)) {
            return 6; // CANNOT_STEAL error code
        }
        // find opponent's minion with max health
        ArrayList<Card> cards = board.getCardsOnBoard().get(rowIdx);
        int stolenMinionCol = -1;
        int maxHealth = 0;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card instanceof Minion && card.getHealth() > maxHealth) {
                stolenMinionCol = i;
                maxHealth = card.getHealth();
            }
        }
        // place stolen minion on board
        board.moveCard(rowIdx, stolenMinionCol, mirrorRow, stolenMinionCol);
        return 0;
    }
}
