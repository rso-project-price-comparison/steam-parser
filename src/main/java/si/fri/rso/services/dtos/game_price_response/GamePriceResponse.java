package si.fri.rso.services.dtos.game_price_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GamePriceResponse(

	@JsonProperty("data")
	PriceData data,

	@JsonProperty("success")
	boolean success
) {
}