package si.fri.rso;

import si.fri.rso.domain.GameFetch;
import si.fri.rso.services.dtos.GameBySearchDto;
import si.fri.rso.services.dtos.GamePriceDto;
import si.fri.rso.services.mappers.SteamMapper;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.List;


public class SteamResourceImpl implements SteamResource {
    @Inject
    GameFetch gameFetch;

    @Override
    public List<GameBySearchDto> getGamesBySearchString(String searchString) {
        if (searchString == null) {
            throw new BadRequestException("Query parameter searchString is required");
        }

        return SteamMapper.toGameBySearchDto(gameFetch.getGameBySearch(searchString));
    }

    @Override
    public List<GamePriceDto> getGamePrices(List<String> ids) {
        if (ids == null) {
            throw new BadRequestException("Query parameter ids is required");
        }

        return ids.stream()
                .map(id -> gameFetch.getGamePrice(id))
                .map(SteamMapper::toGamePriceDto)
                .toList();
    }

}