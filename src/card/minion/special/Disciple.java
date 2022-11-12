package card.minion.special;

import card.minion.Minion;

public class Disciple extends SpecialMinion {
    public Disciple() {
        name = "Disciple";
        boardPlacement.setRow(0);
    }

    public void useAbilityOn(Minion minion) {
        useAbility();
        minion.increaseHealthBy(2);
    }
}
