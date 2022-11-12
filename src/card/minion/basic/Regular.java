package card.minion.basic;

import card.minion.Minion;

abstract public class Regular extends Minion {
    public Regular() {
        defendPriority = 0;
        boardPlacement.setRow(0);
    }
}
