package gameplay.cards.minions;

import fileio.CardInput;
import gameplay.cards.Minion;

public class TheCursedOne extends Minion implements SpecialAbility {
    public TheCursedOne(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setAttackDamage(0);
        this.setHealth(cardInput.getHealth());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName("The Cursed One");
    }
    // shapeshift
    public void useAbilityOn(Minion minion) {
        int newOpponentAttackDamage = minion.getHealth();
        minion.setHealth(minion.getAttackDamage());
        minion.setAttackDamage(newOpponentAttackDamage);
    }
}
