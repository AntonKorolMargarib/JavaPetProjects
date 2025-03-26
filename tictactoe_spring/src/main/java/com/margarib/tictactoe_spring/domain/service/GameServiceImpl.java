package com.margarib.tictactoe_spring.domain.service;

import com.margarib.tictactoe_spring.datasource.mapper.GameEntityMapper;
import com.margarib.tictactoe_spring.datasource.model.GameEntity;
import com.margarib.tictactoe_spring.datasource.repository.GameRepository;
import com.margarib.tictactoe_spring.domain.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
//    private static final int FIELD_SIZE = 3;
    private final GameEntityMapper gameEntityMapper;

    @Override
    public Game getMinimaxTurn(Game game) {
        int[][] board = game.getBoard().getBoard();
        int[] bestMove = Minimax.findBestMove(board);

        if (bestMove != null) {
            board[bestMove[0]][bestMove[1]] = 2; // Компьютер ставит 2
        }
        game.getBoard().setBoard(board);

        return game;
    }

    @Override
    public boolean validateGameBoard(int[][] currentBoard, int row, int col) {
        return currentBoard[row][col] != 0;
    }

    @Override
    public String isGameOver(Game game) {
        int[][] board = game.getBoard().getBoard();

        // Проверка строк
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return (board[i][0] == 1) ? "Player wins!" : "Computer wins!";
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return (board[0][i] == 1) ? "Player wins!" : "Computer wins!";
            }
        }

        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return (board[0][0] == 1) ? "Player wins!" : "Computer wins!";
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return (board[0][2] == 1) ? "Player wins!" : "Computer wins!";
        }

        // Check for a draw
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) return null; // Game not over
            }
        }

        return "It's a draw!";
    }

    @Override
    public void saveGame(Game game) {
        GameEntity gameEntity = gameEntityMapper.toGameEntity(game);
        gameRepository.saveGame(gameEntity);
    }

    @Override
    public Game getGame(UUID gameId) {
        GameEntity gameEntity = gameRepository.getGame(gameId);
        if (gameEntity == null) return null;
        return gameEntityMapper.toGame(gameEntity);
    }

}
