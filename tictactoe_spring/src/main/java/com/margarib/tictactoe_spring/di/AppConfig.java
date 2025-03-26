package com.margarib.tictactoe_spring.di;

import com.margarib.tictactoe_spring.datasource.mapper.GameEntityMapper;
import com.margarib.tictactoe_spring.datasource.repository.GameRepositoryImpl;
import com.margarib.tictactoe_spring.datasource.repository.GameStorage;
import com.margarib.tictactoe_spring.domain.service.GameService;
import com.margarib.tictactoe_spring.domain.service.GameServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GameStorage gameStorage() {
        return new GameStorage();
    }

    @Bean
    public GameRepositoryImpl gameRepository(GameStorage gameStorage) {
        return new GameRepositoryImpl(gameStorage);
    }

    @Bean
    public GameService gameService(GameRepositoryImpl gameRepositoryImpl, GameEntityMapper gameEntityMapper) {
        return new GameServiceImpl(gameRepositoryImpl, gameEntityMapper);
    }
}
