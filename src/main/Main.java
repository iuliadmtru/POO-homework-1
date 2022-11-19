package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.*;
import fileio.actionsoutput.ActionOnCardOutput;
import fileio.actionsoutput.ActionOnDeckOutput;
import fileio.actionsoutput.ActionOnPlayerOutput;
import fileio.actionsoutput.ActionOnStateOutput;
import gameplay.Actions;
import gameplay.Card;
import gameplay.Game;
import gameplay.Player;

import java.io.File;
import java.io.IOException;
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

        Output gameOutput = new Output();
        ArrayList<ActionsOutput> actionsOutputs = new ArrayList<ActionsOutput>();

        // get players decks
        ArrayList<ArrayList<CardInput>> playerOneDecks = inputData.getPlayerOneDecks().getDecks();
        ArrayList<ArrayList<CardInput>> playerTwoDecks = inputData.getPlayerTwoDecks().getDecks();

        // play games
        ArrayList<GameInput> games = inputData.getGames();
        for (GameInput game : games) {
            // set starting configuration
            StartGameInput startGame = game.getStartGame();
            Game gameState = new Game();
            gameState.setPlayerTurn(startGame.getStartingPlayer());
            // set player one hero and deck
            Player playerOne = new Player();
            playerOne.setHero(startGame.getPlayerOneHero());
            playerOne.setDeck(playerOneDecks.get(startGame.getPlayerOneDeckIdx()));
            // set player two hero and deck
            Player playerTwo = new Player();
            playerTwo.setHero(startGame.getPlayerTwoHero());
            playerTwo.setDeck(playerTwoDecks.get(startGame.getPlayerTwoDeckIdx()));

            // play the game
            Actions doAction = new Actions();
            doAction.setGame(gameState);
            doAction.setPlayers(new ArrayList<Player>(Arrays.asList(playerOne, playerTwo)));
            ArrayList<ActionsInput> actions = game.getActions();
            for (ActionsInput action : actions) {
                switch (action.getCommand()) {
                    case "getPlayerDeck":
                        ActionOnDeckOutput getPlayerDeckOutput = new ActionOnDeckOutput();
                        getPlayerDeckOutput.setCommand("getPlayerDeck");
                        ArrayList<Card> deck = doAction.getPlayerDeck(action.getPlayerIdx());
                        // store output
                        ArrayList<ActionOnCardOutput> actionOnDeckOutput = new ArrayList<ActionOnCardOutput>();
                        for (Card card : deck) {
                            actionOnDeckOutput.add(new ActionOnCardOutput(card));
                        }
                        getPlayerDeckOutput.setCardOutputs(actionOnDeckOutput);
                        actionsOutputs.add(getPlayerDeckOutput);
                        break;
                    case "getPlayerHero":
                        ActionOnPlayerOutput getPlayerHeroOutput = new ActionOnPlayerOutput();
                        getPlayerHeroOutput.setCommand("getPlayerHero");
                        Card hero = doAction.getPlayerHero(action.getPlayerIdx());
                        // store output
                        getPlayerHeroOutput.setOutput(new ActionOnCardOutput(hero));
                        actionsOutputs.add(getPlayerHeroOutput);
                        break;
                    case "getPlayerTurn":
                        ActionOnStateOutput getPlayerTurnOutput = new ActionOnStateOutput();
                        getPlayerTurnOutput.setCommand("getPlayerTurn");
                        getPlayerTurnOutput.setState(gameState.getPlayerTurn());
                        // store output
                        actionsOutputs.add(getPlayerTurnOutput);
                        break;
                }
            }
        }

        output.addAll(output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
