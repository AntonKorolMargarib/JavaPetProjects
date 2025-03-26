package com.margarib.tictactoe_spring.web.controller;

import com.margarib.tictactoe_spring.domain.model.Game;
import com.margarib.tictactoe_spring.domain.service.GameService;
import com.margarib.tictactoe_spring.web.mapper.GameWebMapper;
import com.margarib.tictactoe_spring.web.model.GameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final GameWebMapper gameWebMapper;

    @GetMapping()
    public String gameStart(Model model) {
        Game newGame = new Game();
        gameService.saveGame(newGame);

        model.addAttribute("gameId", gameWebMapper.toGameDTO(newGame).getGameId());
        model.addAttribute("board", gameWebMapper.toGameDTO(newGame).getBoard().getBoard());
        model.addAttribute("winner", "");

        return "redirect:/game/" + newGame.getGameId().toString();
    }

    @GetMapping("/{gameId}")
    public String getGame(@PathVariable("gameId") UUID gameId, Model model) {
        Game game = gameService.getGame(gameId);
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }

        model.addAttribute("gameId", gameWebMapper.toGameDTO(game).getGameId());
        model.addAttribute("board", gameWebMapper.toGameDTO(game).getBoard().getBoard());
        model.addAttribute("winner", "");

        return "game";
    }

    @PostMapping("/{gameId}")
    public String updateGame(@PathVariable("gameId") UUID gameId,
                             @RequestParam("row") int row,
                             @RequestParam("col") int col,
                             Model model) {

        Game game = gameService.getGame(gameId);
        int [][] board = game.getBoard().getBoard();

        if (gameService.validateGameBoard(board, row, col)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move");
        }

        board[row][col] = 1;
        game.getBoard().setBoard(board);
        gameService.saveGame(game);

        String gameOverResult = gameService.isGameOver(game);
        if (gameOverResult != null) {
            model.addAttribute("gameId", gameWebMapper.toGameDTO(game).getGameId());
            model.addAttribute("board", gameWebMapper.toGameDTO(game).getBoard().getBoard());
            model.addAttribute("winner", gameOverResult);
            return "game";
        }

        Game computerGame = gameService.getMinimaxTurn(game);
        gameService.saveGame(computerGame);

        gameOverResult = gameService.isGameOver(computerGame);
        if (gameOverResult != null) {
            model.addAttribute("gameId", gameWebMapper.toGameDTO(game).getGameId());
            model.addAttribute("board", gameWebMapper.toGameDTO(game).getBoard().getBoard());
            model.addAttribute("winner", gameOverResult);
            return "game";
        }


        return "redirect:/game/" + computerGame.getGameId();
    }
}
