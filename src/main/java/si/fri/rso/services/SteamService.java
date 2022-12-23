package si.fri.rso.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;


@Path("/")
@RegisterRestClient(configKey = "steam-api")
public interface SteamService {

    @GET
    @Path("/appdetails")
    Map<String, GamePriceResponse> getGamePrice(@QueryParam("filters") String filters, @QueryParam("appids") String appids);
}