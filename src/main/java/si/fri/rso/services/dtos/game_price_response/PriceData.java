package si.fri.rso.services.dtos.game_price_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceData(

	@JsonProperty("price_overview")
    PriceOverview priceOverview
) {
}