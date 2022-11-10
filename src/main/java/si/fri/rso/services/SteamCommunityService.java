package si.fri.rso.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Set;

@Path("/")
@RegisterRestClient(configKey = "steam-community-api")
public interface SteamCommunityService {

    @GET
    @Path("/{searchString}")
    Set<GamesBySearchResponse> getGameBySearch(@PathParam("searchString") String searchString);

}