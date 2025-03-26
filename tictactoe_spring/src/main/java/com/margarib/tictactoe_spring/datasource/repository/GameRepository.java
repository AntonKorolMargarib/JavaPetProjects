package com.margarib.tictactoe_spring.datasource.repository;

import com.margarib.tictactoe_spring.datasource.model.GameEntity;

import java.util.UUID;

public interface GameRepository {
    void saveGame(GameEntity gameEntity);
    GameEntity getGame(UUID gameId);
}
