package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.cards.Minion;

public class Miraj extends Minion implements SpecialAbility {
    public Miraj(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("Miraj");
    }
    // skyjack
    public void useAbilityOn(Minion minion) {
        int newOpponentHealth = this.getHealth();
        this.setHealth(minion.getHealth());
        minion.setHealth(newOpponentHealth);
    }
}
