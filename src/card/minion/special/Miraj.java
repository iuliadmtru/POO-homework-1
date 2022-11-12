package card.minion.special;

import card.minion.Minion;

public class Miraj extends SpecialMinion {
    public Miraj() {
        name = "Miraj";
        boardPlacement.setRow(1);
    }

    private void swapHealthWith(Minion minion) {
        int swapped = minion.getHealth();
        minion.setHealth(this.getHealth());
        this.setHealth(swapped);
    }

    public void useAbilityOn(Minion minion) {
        useAbility();
        swapHealthWith(minion);
    }
}
