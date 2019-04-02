package models;

        import java.util.UUID;

public class GameRequest {
    public UUID userId;
    public int userScore;

    public GameRequest(UUID userId, int userScore) {
        this.userId = userId;
        this.userScore = userScore;
    }
}
