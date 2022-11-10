package si.fri.rso.services.dtos.game_price_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceOverview(

	@JsonProperty("final_formatted")
	String finalFormatted,

	@JsonProperty("initial")
	int initial,

	@JsonProperty("final")
	int jsonMemberFinal,

	@JsonProperty("currency")
	String currency,

	@JsonProperty("initial_formatted")
	String initialFormatted,

	@JsonProperty("discount_percent")
	int discountPercent
) {
}