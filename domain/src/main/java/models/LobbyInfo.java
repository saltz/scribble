package models;

public class LobbyInfo {
    public int hostScore;
    public String lobbyName;
    public int maxAmountOfUsers;

    public LobbyInfo(int hostScore, String lobbyName, int maxAmountOfUsers) {
        this.hostScore = hostScore;
        this.lobbyName = lobbyName;
        this.maxAmountOfUsers = maxAmountOfUsers;
    }
}
