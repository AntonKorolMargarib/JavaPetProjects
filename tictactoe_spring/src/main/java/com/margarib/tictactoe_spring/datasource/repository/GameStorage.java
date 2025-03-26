package com.margarib.tictactoe_spring.datasource.repository;

import com.margarib.tictactoe_spring.datasource.model.GameEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {
    private final Map<UUID, GameEntity> games = new ConcurrentHashMap<>();

    public void saveGame(GameEntity game) {
        games.put(game.getGameId(), game);
    }

    public GameEntity getGame(UUID gameId) {
        return games.get(gameId);
    }
}
