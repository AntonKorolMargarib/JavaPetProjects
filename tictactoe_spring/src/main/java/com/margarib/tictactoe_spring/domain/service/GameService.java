package com.margarib.tictactoe_spring.domain.service;

import com.margarib.tictactoe_spring.domain.model.Game;

import java.util.UUID;

public interface GameService {

    Game getMinimaxTurn(Game game);
    boolean validateGameBoard(int [][] board, int row, int col);
    String isGameOver(Game game);
    void saveGame(Game game);
    Game getGame(UUID gameId);
}
