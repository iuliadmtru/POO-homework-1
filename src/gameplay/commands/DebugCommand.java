package gameplay.commands;

import fileio.ActionsInput;
import gameplay.Command;
import gameplay.Game;
import gameplay.Player;

public class DebugCommand extends Command {
    public DebugCommand(ActionsInput input) {
        super(input);
    }

    @Override
    public void runIn(Game game) {
        switch(this.getCommand()) {
            case "getCardsInHand":
            case "getPlayerDeck":
                Player player = game.getPlayers().get(this.getPlayerIdx());

                break;
            case "getCardsOnTable":
            case "getPlayerTurn":
            case "getPlayerHero":
            case "getCardAtPosition":
            case "getPlayerMana":
            case "getEnvironmentCardsInHand":
            case "getFrozenCardsOnTable":
        }
    }
}
