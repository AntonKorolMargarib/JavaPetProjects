package com.margarib.tictactoe_spring.datasource.repository;

import com.margarib.tictactoe_spring.datasource.model.GameEntity;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Data
public class GameRepositoryImpl implements GameRepository {

    private final GameStorage gameStorage;

    @Override
    public void saveGame(GameEntity gameEntity) {
        gameStorage.saveGame(gameEntity);
    }

    @Override
    public GameEntity getGame(UUID gameId) {
        return gameStorage.getGame(gameId);
    }
}
