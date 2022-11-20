package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.cards.Minion;

public class Disciple extends Minion implements SpecialAbility {
    public Disciple(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(0);
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("Disciple");
    }
    // god's plan
    public void useAbilityOn(Minion minion) {
        minion.setHealth(minion.getHealth() + 2);
    }
}
