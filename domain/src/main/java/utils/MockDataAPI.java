package utils;

import models.LobbyInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockDataAPI {
    private List<LobbyInfo> lobbies;

    public MockDataAPI() {
        lobbies = new ArrayList<>();
    }

    public LobbyInfo getLobbyByScore(int score) {
        List<LobbyInfo> lobbies = this.lobbies.stream().filter(lobbyInfo -> score < (lobbyInfo.hostScore + 50) && score > (lobbyInfo.hostScore - 50)).collect(Collectors.toList());
        return lobbies.get(0);
    }

    public void addLobby(LobbyInfo lobby) {
        this.lobbies.add(lobby);
    }
}
