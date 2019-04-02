package gateways.interfaces;

import models.GameRequest;
import models.GameUpdate;
import models.LobbyInfo;
import models.User;

public interface IClientGateway {
    void sendGameRequest(GameRequest request);
    void receiveLobby(LobbyInfo lobbyInfo);

    void joinGame(User user, String lobbyName);
    void setGameUpdateCallback(String name);
    void receiveGameUpdates(GameUpdate update);

    void hostGame(LobbyInfo lobbyInfo);
}
