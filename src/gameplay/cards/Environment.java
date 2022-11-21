package gameplay.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.Player;

@JsonIgnoreProperties({"attackDamage", "health", "frozen"})
public abstract class Environment extends Card {
    public Environment(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public Environment() {
    }

    /**
     * Uses environment card ability.
     *
     * @param rowIdx index of row on which the ability is used
     * @param board  game board
     */
    public abstract int useAbilityOnRow(int rowIdx, Board board);

    /**
     * Returns an error code; environment cards cannot be placed on the board.
     *
     * @param player player whose board the card is placed on
     * @param board  game board
     */
    public int placeOnBoardOf(final Player player, final Board board) {
        return 2; // ENV_CARD_CANNOT_BE_PLACED error code
    }
}
