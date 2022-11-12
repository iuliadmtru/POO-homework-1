package card.minion.basic;

import card.minion.BoardPlacementInfo;
import card.minion.Minion;

abstract public class Tank extends Minion {
    public Tank() {
        defendPriority = 1;
        boardPlacement.setRow(1);
    }
}
