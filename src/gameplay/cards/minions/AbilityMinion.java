package gameplay.cards.minions;

import gameplay.cards.Minion;

public abstract class AbilityMinion extends Minion {
    /**
     * Use special ability of minion; counts as an attack.
     *
     * @param minion opponent or allied minion
     */
    public abstract void useAbilityOn(Minion minion);
}
