package gateways.interfaces;

import models.*;

public interface IClientGateway {
    void sendGameRequest(GameRequest request);
    void receiveLobby(LobbyInfo lobbyInfo);

    void joinGame(User user, String lobbyName);
    void receiveGameUpdates(GameUpdate update);

    void hostGame(GameHostRequest request);
    void sendGameUpdate(String name, GameUpdate update);
}
