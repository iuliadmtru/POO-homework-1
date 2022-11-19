package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;
import gameplay.Card;
import gameplay.Game;
import gameplay.Player;
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
            Player playerTwo = new Player();
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
                switch (actionInput.getCommand()) {
                    // debug commands
                    case "getPlayerDeck":
                        Player playerWithDeck = gameConfiguration.getPlayers().get(actionInput.getPlayerIdx() - 1);
                        ArrayList<Card> playerDeck = playerWithDeck.getDeck();
                        // store output
                        actionOutput.put("command", "getPlayerDeck");
                        actionOutput.put("playerIdx", actionInput.getPlayerIdx());
                        ArrayNode cardArray = objectMapper.valueToTree(playerDeck);
                        actionOutput.set("output", cardArray);
                        break;
                    case "getPlayerHero":
                        Player playerWithHero = gameConfiguration.getPlayers().get(actionInput.getPlayerIdx() - 1);
                        Hero playerHero = playerWithHero.getHero();
                        // store output
                        actionOutput.put("command", "getPlayerHero");
                        actionOutput.put("playerIdx", actionInput.getPlayerIdx());
                        ObjectNode heroNode = objectMapper.valueToTree(playerHero);
                        actionOutput.set("output", heroNode);
                        break;
                    case "getPlayerTurn":
                        int playerTurn = gameConfiguration.getPlayerTurn();
                        // store output
                        actionOutput.put("command", "getPlayerTurn");
                        actionOutput.put("output", playerTurn);
                        break;
                    case "getCardsInHand":
                    case "getCardsOnTable":
                    case "getCardAtPosition":
                    case "getPlayerMana":
                    case "getEnvironmentCardsInHand":
                    case "getFrozenCardsOnTable":
                        break;
                    // game commands
                    case "endPlayerTurn":
                        gameConfiguration.nextTurn();
                        break;
                    case "placeCard":
                        break;
                }
                // add the action output to the final output
                output.add(actionOutput);
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
