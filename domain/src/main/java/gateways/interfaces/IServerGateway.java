package gateways.interfaces;

import models.GameHostRequest;
import models.GameRequest;

public interface IServerGateway {
    void receiveGameRequest(GameRequest request);
    void receiveHostRequest(GameHostRequest request);
}
