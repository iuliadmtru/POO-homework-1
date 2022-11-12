package card.minion.special;

import card.minion.Minion;

public class TheCursedOne extends SpecialMinion {
    public TheCursedOne() {
        name = "The Cursed One";
        boardPlacement.setRow(0);
    }

    private void swapHealthAndAttackFor(Minion minion) {
        int health = minion.getHealth();
        minion.setHealth(minion.getAttackDamage());
        minion.setAttackDamage(health);
    }

    public void useAbilityOn(Minion minion) {
        useAbility();
        swapHealthAndAttackFor(minion);
    }
}
