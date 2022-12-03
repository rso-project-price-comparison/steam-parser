package si.fri.rso;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import si.fri.rso.domain.GameFetch;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;
import si.fri.rso.services.dtos.game_price_response.PriceData;
import si.fri.rso.services.dtos.game_price_response.PriceOverview;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SteamResourceImplTest {

    @InjectMock
    GameFetch gameFetch;

    public static final List<GamesBySearchResponse> mockGameSearch = Collections.singletonList(new GamesBySearchResponse("Igrica", "1", "ico", "logo"));
    public static final Map<String, GamePriceResponse> mockPrice =  Collections.singletonMap("292030", new GamePriceResponse( Collections.singletonList(new PriceData( new PriceOverview("9,75€", 1, 1,"EUR", "world", 20 ))), true));

    @BeforeEach
    public void setup() {
        Mockito.when(gameFetch.getGameBySearch("1")).thenReturn(mockGameSearch);
        Mockito.when(gameFetch.getGamePrice("292030")).thenReturn(mockPrice);
    }

    @Test
    void testGetGameBySearchMissingQueryParameterSearchString() {
        given().when().get("/steam/games").then().statusCode(400);
    }

    @Test
    void testGetGameBySearch() {
        given().when().get("/steam/games?searchString=1").then().statusCode(200).body(is("[{\"name\":\"Igrica\",\"appid\":\"1\",\"storeEnum\":\"STEAM\"}]"));
    }

    @Test
    void testGetGamePrices() {
        given().when().get("/steam/prices?ids=292030").then().statusCode(200).body(is("[{\"gameId\":\"292030\",\"finalPrice\":9.75,\"currency\":\"EUR\",\"storeEnum\":\"STEAM\"}]"));
    }
}
