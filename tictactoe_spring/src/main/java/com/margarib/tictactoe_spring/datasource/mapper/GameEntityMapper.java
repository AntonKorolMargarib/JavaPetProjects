package com.margarib.tictactoe_spring.datasource.mapper;

import com.margarib.tictactoe_spring.datasource.model.GameEntity;
import com.margarib.tictactoe_spring.domain.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameEntityMapper {

    @Mappings({
            @Mapping(target = "gameId", source = "gameId"),
            @Mapping(target = "board", source = "board")
    })
    GameEntity toGameEntity(Game game);

    @Mappings({
            @Mapping(target = "gameId", source = "gameId"),
            @Mapping(target = "board", source = "board")
    })
    Game toGame(GameEntity gameEntity);

//    GameEntity toGameEntity(Game game);
//
//    Game toGame(GameEntity gameEntity);
//
//    GameEntityMapper INSTANCE = Mappers.getMapper(GameEntityMapper.class)
//    - possible variant of realisation
}
