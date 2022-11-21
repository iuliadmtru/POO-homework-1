package gameplay.cards.environment;

import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.cards.Environment;
import gameplay.cards.Minion;

import java.util.ArrayList;

public class HeartHound extends Environment {
    public HeartHound(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    /**
     * Steal opponent's minion with the highest health on the row and place it on the mirror row.
     *
     * @param rowIdx index of row on which the ability is used
     * @param board  game board
     */
    public int useAbilityOnRow(final int rowIdx, final Board board) {
        // check if the mirror row is full
        int mirrorRow = switch (rowIdx) {
            case 0 -> 3;
            case 1 -> 2;
            case 2 -> 1;
            case 3 -> 0;
            default -> -1;
        };
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
