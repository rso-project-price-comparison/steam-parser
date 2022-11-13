package si.fri.rso;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.Arc;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.rso.services.dtos.GameBySearchDto;
import si.fri.rso.services.dtos.GamePriceDto;
import si.fri.rso.services.SteamCommunityService;
import si.fri.rso.services.SteamService;
import si.fri.rso.services.mappers.GameBySearchDtoMapper;
import si.fri.rso.services.mappers.GamePriceDtoMapper;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/steam")
public class SteamResource {

    @RestClient
    SteamCommunityService steamCommunityService;
    @RestClient
    SteamService steamService;
    @Inject
    GameBySearchDtoMapper gameBySearchDtoMapper;
    @Inject
    GamePriceDtoMapper gamePriceDtoMapper;

    @PostConstruct
    void init() {
        ObjectMapper objectMapper = Arc.container().instance(ObjectMapper.class).get();
        objectMapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    @GET
    @Path("/games")
    public List<GameBySearchDto> getGamesBySearchString(@QueryParam("searchString")String searchString) {
        return gameBySearchDtoMapper.toDto(steamCommunityService.getGameBySearch(searchString));
    }

    @GET
    @Path("/prices")
    public List<GamePriceDto> getGamePrices(@QueryParam("ids") List<String> ids) {

        return ids.stream()
                .map(i -> steamService.getGamePrice("price_overview", i))
                .map(i -> gamePriceDtoMapper.toDto(i))
                .toList();
    }

}