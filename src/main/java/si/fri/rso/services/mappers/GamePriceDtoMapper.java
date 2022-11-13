package si.fri.rso.services.mappers;

import si.fri.rso.services.dtos.GamePriceDto;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;
import si.fri.rso.services.dtos.game_price_response.PriceData;

import javax.enterprise.context.RequestScoped;
import java.util.Map;
import java.util.Optional;

@RequestScoped
public class GamePriceDtoMapper {

    public GamePriceDto toDto(Map<String, GamePriceResponse> response) {

        Map.Entry<String, GamePriceResponse> gamePriceResponse = response.entrySet().iterator().next();
        String key = gamePriceResponse.getKey();
        GamePriceResponse value = gamePriceResponse.getValue();

        PriceData priceData = Optional.of(value.data().stream().findFirst()).get().orElse(null);

        if (priceData == null)
            return new GamePriceDto(key, null, null);

        String finalPriceString = priceData.priceOverview().finalFormatted();
        Float finalPrice = Float.parseFloat(finalPriceString
                .substring(0, finalPriceString.length() - 1)
                .replaceAll(",", "."));

        return new GamePriceDto(key, finalPrice, priceData.priceOverview().currency());
    }

}
