package gameplay;

import fileio.CardInput;
import gameplay.cards.Environment;
import gameplay.cards.Hero;
import gameplay.cards.Minion;
import gameplay.cards.environment.Firestorm;
import gameplay.cards.environment.HeartHound;
import gameplay.cards.environment.Winterfell;
import gameplay.cards.heroes.EmpressThorina;
import gameplay.cards.heroes.GeneralKocioraw;
import gameplay.cards.heroes.KingMudface;
import gameplay.cards.heroes.LordRoyce;
import gameplay.cards.minions.Disciple;
import gameplay.cards.minions.Miraj;
import gameplay.cards.minions.RegularMinion;
import gameplay.cards.minions.Tank;
import gameplay.cards.minions.TheCursedOne;
import gameplay.cards.minions.TheRipper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private int playerIdx;
    private int mana;
    private Hero hero;
    private ArrayList<Card> deck;
    private final ArrayList<Card> cardsInHand = new ArrayList<>();

    /**
     * Returns the index of the player (1 or 2).
     *
     * @return player index
     */
    public int getPlayerIdx() {
        return playerIdx;
    }

    /**
     * Sets the index of the player (1 or 2).
     *
     * @param playerIdx player index
     */
    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Returns the amount of mana the player has.
     *
     * @return player mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * Increases the amount of mana the player has by an amount.
     *
     * @param amount amount by which the mana is increased
     */
    public void increaseManaBy(final int amount) {
        mana += amount;
    }

    /**
     * Decreases the amount of mana the player has by an amount.
     *
     * @param amount amount by which the mana is decreased
     */
    public void decreaseManaBy(final int amount) {
        mana -= amount;
    }

    /**
     * Returns the hero of the player.
     *
     * @return player hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Sets the hero of the player.
     *
     * @param heroInput player hero input
     */
    public void setHero(final CardInput heroInput) {
        hero = switch (heroInput.getName()) {
            case "Lord Royce" -> new LordRoyce(heroInput);
            case "Empress Thorina" -> new EmpressThorina(heroInput);
            case "King Mudface" -> new KingMudface(heroInput);
            case "General Kocioraw" -> new GeneralKocioraw(heroInput);
            default -> null;
        };
    }

    /**
     * Returns the deck that the player chose.
     *
     * @return player deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Sets the deck that the player chose.
     *
     * @param deckInput player deck input
     */
    public void setDeck(final ArrayList<CardInput> deckInput) {
        deck = new ArrayList<Card>();
        for (CardInput cardInput : deckInput) {
            // convert cards to their corresponding type
            switch (cardInput.getName()) {
                // environment
                case "Firestorm" -> deck.add(new Firestorm(cardInput));
                case "Winterfell" -> deck.add(new Winterfell(cardInput));
                case "Heart Hound" -> deck.add(new HeartHound(cardInput));
                // minion
                case "Goliath", "Warden" -> deck.add(new Tank(cardInput));
                case "Sentinel", "Berserker" -> deck.add(new RegularMinion(cardInput));
                case "Miraj" -> deck.add(new Miraj(cardInput));
                case "The Ripper" -> deck.add(new TheRipper(cardInput));
                case "Disciple" -> deck.add(new Disciple(cardInput));
                case "The Cursed One" -> deck.add(new TheCursedOne(cardInput));
                default -> deck.add(null);
            }
        }
    }

    /**
     * Shuffles the deck that the player chose with a given seed.
     *
     * @param seed shuffle seed
     */
    public void shuffleDeckWithSeed(final int seed) {
        Collections.shuffle(deck, new Random(seed));
    }

    /**
     * Returns the cards that the player has in hand.
     *
     * @return cards in hand
     */
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * Returns the environment cards that the player has in hand.
     *
     * @return environment cards in hand
     */
    public ArrayList<Environment> getEnvironmentCardsInHand() {
        ArrayList<Environment> environmentCards = new ArrayList<Environment>();
        for (Card card : cardsInHand) {
            if (card instanceof Environment) {
                environmentCards.add((Environment) card);
            }
        }
        return environmentCards;
    }

    /**
     * Takes a card from the deck.
     */
    public void takeCard() {
        // add card in hand and remove from deck
        cardsInHand.add(deck.get(0));
        deck.remove(0);
    }

    /**
     * Places a card on the board.
     *
     * @param cardIdx index of the card from the hand that is placed on the board
     * @param board game board
     * @return 0 if successful, error code otherwise
     */
    public int placeCard(final int cardIdx, final Board board) {
        // get the card from the player's hand
        Card card = cardsInHand.get(cardIdx);
        if (card.getMana() > mana) {
            return 3; // NOT_ENOUGH_MANA error code
        }
        // place the card on the board
        int exitCode = card.placeOnBoardOf(this, board);
        // if successful remove the card from the player's hand
        if (exitCode == 0) {
            cardsInHand.remove(cardIdx);
        }
        return exitCode;
    }

    /**
     * Unfreeze all the cards of the player (usually done at the end of the player's turn).
     *
     * @param board game board
     */
    public void unfreezeCards(final Board board) {
        ArrayList<ArrayList<Card>> playerCards = board.getPlayerCardsOnBoard(playerIdx);
        for (ArrayList<Card> cardsOnRow : playerCards) {
            for (Card card : cardsOnRow) {
                card.setFrozen(false);
            }
        }
    }

    /**
     * Checks if a board row corresponds to the player.
     *
     * @param rowIdx index of checked row
     * @return `true` if the row corresponds to the player, `false` otherwise
     */
    public boolean hasRow(final int rowIdx) {
        return switch (playerIdx) {
            case 1 -> (rowIdx == 2 || rowIdx == 3);
            case 2 -> (rowIdx == 0 || rowIdx == 1);
            default -> false;
        };
    }

    /**
     * Use an environment card from the hand deck on a given row.
     *
     * @param rowIdx affected row index
     * @param card used card
     * @param board game board
     * @return 0 if successful, error code otherwise
     */
    public int useEnvironmentCard(final Environment card, final int rowIdx, final Board board) {
        if (card.getMana() > mana) {
            return 4; // NOT_ENOUGH_MANA_ENV error code
        }
        if (this.hasRow(rowIdx)) {
            return 5;
        }
        // use card ability
        int exitCode = card.useAbilityOnRow(rowIdx, board);
        // if successful remove card from hand and decrease mana
        if (exitCode == 0) {
            cardsInHand.remove(card);
            // decrease mana
            decreaseManaBy(card.getMana());
        }
        return exitCode;
    }

    /**
     * Check if the player has cards of type `Tank` placed on the board.
     *
     * @param board game board
     * @return `true` if the player has `Tank`s, `false` otherwise
     */
    public boolean hasTanksOnBoard(final Board board) {
        switch (playerIdx) {
            case 1:
                ArrayList<Card> playerOneFrontCards = board.getCardsOnBoard().get(2);
                for (Card card : playerOneFrontCards) {
                    if (card instanceof Tank) {
                        return true;
                    }
                }
                return false;
            case 2:
                ArrayList<Card> playerTwoFrontCards = board.getCardsOnBoard().get(1);
                for (Card card : playerTwoFrontCards) {
                    if (card instanceof Tank) {
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * Marks all the player's cards on the board as cards that have not attacked (usually done at
     * the end of the player's turn).
     *
     * @param board game board
     */
    public void resetAttackStateOfCards(final Board board) {
        switch (playerIdx) {
            case 1:
                for (int rowIdx = 2; rowIdx <= 3; rowIdx++) {
                    for (Card card : board.getCardsOnBoard().get(rowIdx)) {
                        if (card instanceof Minion) {
                            ((Minion) card).resetAttackState();
                        }
                    }
                }
                break;
            case 2:
                for (int rowIdx = 0; rowIdx <= 1; rowIdx++) {
                    for (Card card : board.getCardsOnBoard().get(rowIdx)) {
                        if (card instanceof Minion) {
                            ((Minion) card).resetAttackState();
                        }
                    }
                }
                break;
            default:
        }
    }

    /**
     * Marks the player's hero as card that has not attacked (usually done at the end of the
     * player's turn).
     */
    public void resetAttackStateOfHero() {
        getHero().resetAttackState();
    }
}
