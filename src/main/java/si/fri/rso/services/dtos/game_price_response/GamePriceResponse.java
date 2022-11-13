package si.fri.rso.services.dtos.game_price_response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GamePriceResponse(

	@JsonProperty("data")
	List<PriceData> data,

	@JsonProperty("success")
	boolean success
) {
}