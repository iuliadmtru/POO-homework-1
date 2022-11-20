package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.cards.Minion;

public class TheRipper extends Minion implements SpecialAbility {
    public TheRipper(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("The Ripper");
    }
    // weak knees
    public void useAbilityOn(Minion minion) {
        minion.setAttackDamage(minion.getAttackDamage() - 2);
    }
}
