# steam-parser Project

Steam parser fetches game data from the SteamApps API  and StoreFront API  and 
converts the data for internal use.

It offers two gRPC methods:
- GetGamesBySearchString(GetGamesBySearchStringRequest) returns (GetGamesBySearchStringResponse);
- getGamePrices (GetGamePricesRequest) returns (GetGamePricesResponse);

This project uses Quarkus, the Supersonic Subatomic Java Framework.
To run the project locally in docker run commands: 

## Docker build
```shell script
docker build -f Dockerfile.jvm -t tjasad/rso-steam-parser .
```

## Docker run
```shell script
docker run -i --rm -p 9000:9000 tjasad/rso-steam-parser
```

There is also a deployment file for k8s present which can be used for kubernetes deployment.