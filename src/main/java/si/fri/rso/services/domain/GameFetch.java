package si.fri.rso.services.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.Arc;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.rso.services.SteamCommunityService;
import si.fri.rso.services.SteamService;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Map;

@RequestScoped
public class GameFetch {

    @RestClient
    SteamCommunityService steamCommunityService;
    @RestClient
    SteamService steamService;

    @PostConstruct
    void init() {
        ObjectMapper objectMapper = Arc.container().instance(ObjectMapper.class).get();
        objectMapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    public List<GamesBySearchResponse> getGameBySearch(String searchString) {
        return steamCommunityService.getGameBySearch(searchString);
    }

    public Map<String, GamePriceResponse>  getGamePrice(String ids) {
        return steamService.getGamePrice("price_overview", ids);
    }
}
