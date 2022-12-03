package si.fri.rso;

import si.fri.rso.services.dtos.GameBySearchDto;
import si.fri.rso.services.dtos.GamePriceDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/steam")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SteamResource {
    @GET
    @Path("/games")
    List<GameBySearchDto> getGamesBySearchString(@QueryParam("searchString") String searchString);

    @GET
    @Path("/prices")
    List<GamePriceDto> getGamePrices(@QueryParam("ids") List<String> ids);
}