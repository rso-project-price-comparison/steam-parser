package si.fri.rso.resources;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import si.fri.rso.GameBySearchDto;
import si.fri.rso.GamePriceDto;
import si.fri.rso.GameServiceGrpc;
import si.fri.rso.GetGamePricesRequest;
import si.fri.rso.GetGamePricesResponse;
import si.fri.rso.GetGamesBySearchStringRequest;
import si.fri.rso.GetGamesBySearchStringResponse;
import si.fri.rso.StoreEnum;
import si.fri.rso.services.domain.GameFetch;
import si.fri.rso.services.dtos.game_price_response.GamePriceResponse;
import si.fri.rso.services.dtos.games_by_search_response.GamesBySearchResponse;


@GrpcService
public class SteamService extends GameServiceGrpc.GameServiceImplBase {
    @Inject
    GameFetch gameFetch;

    @Blocking
    @Override
    public void getGamesBySearchString(GetGamesBySearchStringRequest request, StreamObserver<GetGamesBySearchStringResponse> responseObserver) {
        List<GamesBySearchResponse> gameBySearch = gameFetch.getGameBySearch(request.getSearchString());
        responseObserver.onNext(GetGamesBySearchStringResponse.newBuilder()
                .addAllGames(() -> gameBySearch.stream()
                        .map(gbsr ->
                                GameBySearchDto.newBuilder()
                                        .setAppid(gbsr.appid())
                                        .setName(gbsr.name())
                                        .setStoreEnum(si.fri.rso.StoreEnum.STEAM)
                                        .build()
                        ).iterator())
                .build());
        responseObserver.onCompleted();
    }


    @Blocking
    @Override
    public void getGamePrices(GetGamePricesRequest request, StreamObserver<GetGamePricesResponse> responseObserver) {
        String appids = request.getIdsList().stream().collect(Collectors.joining(","));
        Map<String, GamePriceResponse> gamePrice = gameFetch.getGamePrice(appids);


        responseObserver.onNext(GetGamePricesResponse.newBuilder()
                .addAllGamePrices(() -> gamePrice
                        .entrySet().stream()
                        .filter(entry -> !entry.getValue().data().isEmpty())
                        .map(entry -> GamePriceDto.newBuilder()
                                .setGameId(entry.getKey())
                                .setCurrency(entry.getValue().data().get(0).priceOverview().currency())
                                .setFinalPrice(extractPrice(entry.getValue().data().get(0).priceOverview().finalFormatted()))
                                .setStoreEnum(StoreEnum.STEAM)
                                .build())
                        .iterator())
                .build());

        responseObserver.onCompleted();

    }

    public static float extractPrice(String priceString) {
        priceString = priceString.replaceAll(",", ".");
        return Float.parseFloat(priceString.replaceAll("[^\\d.]", ""));
    }
}
