package card.minion.special;

import card.minion.Minion;

abstract public class SpecialMinion extends Minion implements Ability {
    private boolean hasAbility = true;

    public boolean canUseAbility() {
        return (hasAbility && !isFrozen());
    }

    public void useAbility() {
        hasAbility = false;
    }
}
