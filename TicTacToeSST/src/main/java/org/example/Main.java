package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Hello world!");
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = List.of(
                new Player("Harsh", new Symbol('X'), PlayerType.HUMAN),
                new Bot("Scaler", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        Game game = gameController.startGame(dimension, players);
        //gameController.printBoard(game);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if (!gameController.checkState(game).equals(GameState.ENDED)) {
            game.setGameState(GameState.DRAW);
            System.out.println("Game DRAW");
        } else {
            gameController.printBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
}