package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;
import gameplay.Board;
import gameplay.Card;
import gameplay.Game;
import gameplay.Player;
import gameplay.cards.Environment;
import gameplay.cards.Hero;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        // get players decks lists
        ArrayList<ArrayList<CardInput>> playerOneDecks = inputData.getPlayerOneDecks().getDecks();
        ArrayList<ArrayList<CardInput>> playerTwoDecks = inputData.getPlayerTwoDecks().getDecks();
        // iterate through all games
        ArrayList<GameInput> games = inputData.getGames();
        for (GameInput game : games) {
            // set starting configuration
            StartGameInput startInput = game.getStartGame();
            Player playerOne = new Player();
            playerOne.setPlayerIdx(1);
            Player playerTwo = new Player();
            playerTwo.setPlayerIdx(2);
            // players choose decks
            playerOne.setDeck(playerOneDecks.get(startInput.getPlayerOneDeckIdx()));
            playerTwo.setDeck(playerTwoDecks.get(startInput.getPlayerTwoDeckIdx()));
            // shuffle decks
            playerOne.shuffleDeckWithSeed(startInput.getShuffleSeed());
            playerTwo.shuffleDeckWithSeed(startInput.getShuffleSeed());
            // players choose heroes
            playerOne.setHero(startInput.getPlayerOneHero());
            playerTwo.setHero(startInput.getPlayerTwoHero());

            // set game configuration
            Game gameConfiguration = new Game();
            // set empty board
            gameConfiguration.setGameBoard(new Board());
            // set starting player turn
            gameConfiguration.setPlayerTurn(startInput.getStartingPlayer());
            // set players
            ArrayList<Player> players = new ArrayList<Player>();
            players.add(playerOne);
            players.add(playerTwo);
            gameConfiguration.setPlayers(players);
            // start first round
            gameConfiguration.nextRound();

            // do actions
            for (ActionsInput actionInput : game.getActions()) {
                // instantiate action output node
                ObjectNode actionOutput = objectMapper.createObjectNode();
                // get action configuration
                int playerTurn = gameConfiguration.getPlayerTurn();
                int playerIdx = actionInput.getPlayerIdx();
                int handIdx = actionInput.getHandIdx();
                Board gameBoard = gameConfiguration.getGameBoard();
                switch (actionInput.getCommand()) {
                    // debug commands
                    case "getPlayerDeck":
                        Player playerWithDeck = players.get(playerIdx - 1);
                        ArrayList<Card> playerDeck = playerWithDeck.getDeck();
                        // store output
                        actionOutput.put("command", "getPlayerDeck");
                        actionOutput.put("playerIdx", playerIdx);
                        ArrayNode cardArray = objectMapper.valueToTree(playerDeck);
                        actionOutput.set("output", cardArray);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getPlayerHero":
                        Player playerWithHero = players.get(playerIdx - 1);
                        Hero playerHero = playerWithHero.getHero();
                        // store output
                        actionOutput.put("command", "getPlayerHero");
                        actionOutput.put("playerIdx", playerIdx);
                        ObjectNode heroNode = objectMapper.valueToTree(playerHero);
                        actionOutput.set("output", heroNode);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getPlayerTurn":
                        // store output
                        actionOutput.put("command", "getPlayerTurn");
                        actionOutput.put("output", playerTurn);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getCardsInHand":
                        Player playerWithCards = players.get(playerIdx - 1);
                        ArrayList<Card> cardsInHand = playerWithCards.getCardsInHand();
                        // store output
                        actionOutput.put("command", "getCardsInHand");
                        actionOutput.put("playerIdx", playerIdx);
                        ArrayNode cardsInHandArray = objectMapper.valueToTree(cardsInHand);
                        actionOutput.set("output", cardsInHandArray);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getPlayerMana":
                        Player playerWithMana = players.get(playerIdx - 1);
                        int playerMana = playerWithMana.getMana();
                        actionOutput.put("command", "getPlayerMana");
                        actionOutput.put("playerIdx", playerIdx);
                        actionOutput.put("output", playerMana);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getCardsOnTable":
                        ArrayList<ArrayList<Card>> cardsOnTable = gameBoard.getCardsOnBoard();
                        // store output
                        actionOutput.put("command", "getCardsOnTable");
                        ArrayNode cardsOnTableArray = objectMapper.valueToTree(cardsOnTable);
                        actionOutput.set("output", cardsOnTableArray);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getEnvironmentCardsInHand":
                        Player playerWithEnvCards = players.get(playerIdx - 1);
                        ArrayList<Environment> environmentCardsInHand = playerWithEnvCards.getEnvironmentCardsInHand();
                        // store output
                        actionOutput.put("command", "getEnvironmentCardsInHand");
                        actionOutput.put("playerIdx", playerIdx);
                        ArrayNode environmentCardsArray = objectMapper.valueToTree(environmentCardsInHand);
                        actionOutput.set("output", environmentCardsArray);
                        // add the action output to the final output
                        output.add(actionOutput);
                        break;
                    case "getCardAtPosition":
                    case "getFrozenCardsOnTable":
                        break;
                    // game commands
                    case "endPlayerTurn":
                        gameConfiguration.nextTurn();
                        break;
                    case "placeCard":
                        Player playerThatPlacesCard = players.get(playerTurn - 1);
                        switch (playerThatPlacesCard.placeCard(handIdx, gameBoard)) {
                            case 1: // ROW_IS_FULL
                                actionOutput.put("command", "placeCard");
                                actionOutput.put("handIdx", handIdx);
                                actionOutput.put("error", "Cannot place card on table since row is full.");
                                // add the action output to the final output
                                output.add(actionOutput);
                                break;
                            case 2: // ENV_CARD_CANNOT_BE_PLACED
                                actionOutput.put("command", "placeCard");
                                actionOutput.put("handIdx", handIdx);
                                actionOutput.put("error", "Cannot place environment card on table.");
                                // add the action output to the final output
                                output.add(actionOutput);
                                break;
                            case 3: // NOT_ENOUGH_MANA
                                actionOutput.put("command", "placeCard");
                                actionOutput.put("handIdx", handIdx);
                                actionOutput.put("error", "Not enough mana to place card on table.");
                                // add the action output to the final output
                                output.add(actionOutput);
                                break;
                        }
                        break;
                    case "useEnvironmentCard":
                        Player playerThatUsesEnv = players.get(playerTurn - 1);
                        break;
                }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
