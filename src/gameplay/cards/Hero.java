package gameplay.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import gameplay.Board;
import gameplay.Card;
import gameplay.Player;

@JsonIgnoreProperties({"attackDamage", "frozen"})
public abstract class Hero extends Card {
    private boolean attackState = false;

    public Hero(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setHealth(30);
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public boolean hasAttacked() {
        return attackState;
    }

    public void setAttackState(boolean attackState) {
        this.attackState = attackState;
    }

    public void resetAttackState() {
        attackState = false;
    }

    public abstract void useAbility(Board board, int rowIdx);

    // hero cards are not placed on the board
    public int placeOnBoardOf(Player player, Board board) {
        return 0;
    }
}
