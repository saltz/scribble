package models;

import java.util.UUID;

public class GameHostRequest {
    public UUID hostId;
    public String lobbyName;
    public int maxAmountOfPlayers;

    public GameHostRequest(UUID hostId, String lobbyName, int maxAmountOfPlayers) {
        this.hostId = hostId;
        this.lobbyName = lobbyName;
        this.maxAmountOfPlayers = maxAmountOfPlayers;
    }
}
