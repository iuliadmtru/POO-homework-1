package gameplay.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.Player;

@JsonIgnoreProperties({"attackDamage"})
public class Hero extends Card {
    public Hero(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setHealth(30);
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    // hero cards are not placed on the board
    public int placeOnBoardOf(Player player, Board board) {
        return 0;
    }
}
