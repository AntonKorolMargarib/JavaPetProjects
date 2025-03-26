package com.margarib.tictactoe_spring.web.mapper;

import com.margarib.tictactoe_spring.domain.model.Game;
import com.margarib.tictactoe_spring.web.model.GameDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameWebMapper {

    @Mappings({
            @Mapping(target = "gameId", source = "gameId"),
            @Mapping(target = "board", source = "board")
    })
    GameDTO toGameDTO(Game game);

    @Mappings({
            @Mapping(target = "gameId", source = "gameId"),
            @Mapping(target = "board", source = "board")
    })
    Game toGame(GameDTO gameDTO);

}
