package com.margarib.tictactoe_spring.web.model;


import lombok.Data;

import java.util.UUID;

@Data
public class GameDTO {
    private UUID gameId;
    private GameBoardDTO board;
}
