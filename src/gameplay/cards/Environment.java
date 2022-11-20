package gameplay.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.Player;

@JsonIgnoreProperties({"attackDamage", "health"})
public abstract class Environment extends Card {
    public Environment(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public Environment() {
    }

    public abstract int useAbilityOnRow(int rowIdx, Board board);

    // environment cards cannot be placed on the board`
    public int placeOnBoardOf(Player player, Board board) {
        return 2; // ENV_CARD_CANNOT_BE_PLACED error code
    }
}
