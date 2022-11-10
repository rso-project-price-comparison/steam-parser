package si.fri.rso;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;
import si.fri.rso.services.SteamCommunityService;
import si.fri.rso.services.SteamService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("/")
public class SteamResource {

    @RestClient
    SteamCommunityService steamCommunityService;

    @RestClient
    SteamService steamService;

    @GET
    @Path("/games/{searchString}")
    public Set<GamesBySearchResponse> getGamesBySearchString(@PathParam("searchString")String searchString) {
        return steamCommunityService.getGameBySearch(searchString);
    }

    @GET
    @Path("/prices")
    public Map<String, GamePriceResponse> getGamePrices(@QueryParam("ids") List<String> ids) {
        return steamService.getGamePrice("price_overview", ids);
    }

}