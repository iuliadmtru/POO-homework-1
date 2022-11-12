package card.minion.special;

import card.minion.Minion;

public class TheRipper extends SpecialMinion {
    public TheRipper() {
        boardPlacement.setRow(1);
    }

    public void useAbilityOn(Minion minion) {
        useAbility();
        minion.decreaseAttackDamageBy(2);
    }
}
