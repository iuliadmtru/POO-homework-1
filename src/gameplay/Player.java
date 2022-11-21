package gameplay;

import fileio.CardInput;
import gameplay.cards.Environment;
import gameplay.cards.Hero;
import gameplay.cards.Minion;
import gameplay.cards.environment.Firestorm;
import gameplay.cards.environment.HeartHound;
import gameplay.cards.environment.Winterfell;
import gameplay.cards.minions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private int playerIdx;
    private int mana;
    private Hero hero;
    private ArrayList<Card> deck;
    private ArrayList<Card> cardsInHand = new ArrayList<>();

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public int getMana() {
        return mana;
    }

    public void increaseManaBy(int amount) {
        mana += amount;
    }

    public void decreaseManaBy(int amount) {
        mana -= amount;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(CardInput heroInput) {
        hero = new Hero(heroInput);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<CardInput> deckInput) {
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
            }
        }
    }

    public void shuffleDeckWithSeed(int seed) {
        Collections.shuffle(deck, new Random(seed));
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Environment> getEnvironmentCardsInHand() {
        ArrayList<Environment> environmentCards = new ArrayList<Environment>();
        for (Card card : cardsInHand) {
            if (card instanceof Environment) {
                environmentCards.add((Environment) card);
            }
        }
        return environmentCards;
    }

    public void takeCard() {
        // card belongs to player `playerIdx`
        deck.get(0).setOwner(playerIdx);
        // add card in hand and remove from deck
        cardsInHand.add(deck.get(0));
        deck.remove(0);
    }

    public int placeCard(int cardIdx, Board board) {
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

    public void unfreezeCards(Board board) {
        ArrayList<ArrayList<Card>> playerCards = board.getPlayerCardsOnBoard(playerIdx);
        for (ArrayList<Card> cardsOnRow : playerCards) {
            for (Card card : cardsOnRow) {
                card.setFrozen(false);
            }
        }
    }

    public boolean hasRow(int rowIdx) {
        return switch (playerIdx) {
            case 1 -> (rowIdx == 2 || rowIdx == 3);
            case 2 -> (rowIdx == 0 || rowIdx == 1);
            default -> false;
        };
    }

    public int useEnvironmentCard(Environment card, int rowIdx, Board board) {
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

    public boolean hasTanksOnBoard(Board board) {
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

    public void resetAttackStateOfCards(Board board) {
        switch (playerIdx) {
            case 1:
                for (int rowIdx = 2; rowIdx <=3; rowIdx++) {
                    for (Card card : board.getCardsOnBoard().get(rowIdx)) {
                        if (card instanceof Minion) {
                            ((Minion) card).resetAttackState();
                        }
                    }
                }
            case 2:
                for (int rowIdx = 0; rowIdx <=1; rowIdx++) {
                    for (Card card : board.getCardsOnBoard().get(rowIdx)) {
                        if (card instanceof Minion) {
                            ((Minion) card).resetAttackState();
                        }
                    }
                }
        }
    }
}
