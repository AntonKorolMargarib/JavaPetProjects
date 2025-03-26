package com.margarib.tictactoe_spring.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameBoardEntity {
    private int[][] board;
}
