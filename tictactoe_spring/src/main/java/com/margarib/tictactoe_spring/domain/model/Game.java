package com.margarib.tictactoe_spring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Game {
    private UUID gameId;
    private GameBoard board;

    public Game() {
        this.gameId = UUID.randomUUID();
        this.board = new GameBoard();
    }
}

