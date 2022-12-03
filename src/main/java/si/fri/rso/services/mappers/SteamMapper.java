package si.fri.rso.services.mappers;

import si.fri.rso.services.dtos.GameBySearchDto;
import si.fri.rso.services.dtos.GamePriceDto;
import si.fri.rso.services.dtos.StoreEnum;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;
import si.fri.rso.services.dtos.game_price_response.PriceData;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class SteamMapper {

    private SteamMapper() {}

    public static List<GameBySearchDto> toGameBySearchDto(List<GamesBySearchResponse> response) {
        return response.stream()
                .map(g -> new GameBySearchDto(g.name(), g.appid(), StoreEnum.STEAM))
                .toList();
    }

    public static GamePriceDto toGamePriceDto(Map<String, GamePriceResponse> response) {

        Map.Entry<String, GamePriceResponse> gamePriceResponse = response.entrySet().iterator().next();
        String key = gamePriceResponse.getKey();
        GamePriceResponse value = gamePriceResponse.getValue();

        PriceData priceData = Optional.of(value.data().stream().findFirst()).get().orElse(null);

        if (priceData == null)
            return new GamePriceDto(key, null, null, StoreEnum.STEAM);

        String finalPriceString = priceData.priceOverview().finalFormatted();
        Float finalPrice = Float.parseFloat(finalPriceString
                .substring(0, finalPriceString.length() - 1)
                .replaceAll(",", "."));

        return new GamePriceDto(key, finalPrice, priceData.priceOverview().currency(), StoreEnum.STEAM);
    }
}
