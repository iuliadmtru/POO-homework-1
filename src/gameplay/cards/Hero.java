package gameplay.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;
import gameplay.Card;

@JsonIgnoreProperties({"attackDamage"})
public class Hero extends Card {
    public Hero(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setHealth(30);
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }
}
