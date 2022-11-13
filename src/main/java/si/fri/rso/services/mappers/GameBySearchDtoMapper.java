package si.fri.rso.services.mappers;

import si.fri.rso.services.dtos.GameBySearchDto;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class GameBySearchDtoMapper {

    public List<GameBySearchDto> toDto(List<GamesBySearchResponse> response) {
        return response.stream().map(g -> new GameBySearchDto(g.name(), g.appid())).toList();
    }
}
