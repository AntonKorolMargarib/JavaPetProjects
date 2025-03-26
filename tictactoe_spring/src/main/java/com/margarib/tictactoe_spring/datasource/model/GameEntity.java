package com.margarib.tictactoe_spring.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data  // @Data use for getters, setters, equals, hashCode, toString and RequiredArgsConstructor
public class GameEntity {

    private UUID gameId;
    private GameBoardEntity board;
}
