package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.cards.Minion;

public class Tank extends Minion {
    public Tank(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }
}
