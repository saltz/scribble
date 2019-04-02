package gateways.concrete;

import gateways.interfaces.IServerGateway;
import jms.concrete.JmsReceiver;
import jms.concrete.JmsSender;
import jms.enums.JmsType;
import models.GameHostRequest;
import models.GameRequest;
import models.LobbyInfo;
import utils.JsonSerializer;
import utils.MockDataAPI;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public abstract class ServerGateway implements IServerGateway {
    private final JsonSerializer jsonSerializer;
    private final MockDataAPI mockDataAPI;

    public ServerGateway() {
        jsonSerializer = new JsonSerializer();
        mockDataAPI = new MockDataAPI();

        new JmsReceiver(JmsType.QUEUE, "joinGame").setCallback(msg -> {
            try {
                TextMessage textMessage = (TextMessage) msg;
                receiveGameRequest(jsonSerializer.toObject(textMessage.getText(), GameRequest.class));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        new JmsReceiver(JmsType.QUEUE, "hostGame").setCallback(msg -> {
            try {
                TextMessage textMessage = (TextMessage) msg;
                receiveHostRequest(jsonSerializer.toObject(textMessage.getText(), GameHostRequest.class));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void receiveGameRequest(GameRequest request) {
        // TODO check user score and get required game via data api

        Thread thread = new Thread(() -> {
            LobbyInfo lobby = null;

            while(lobby == null) {
                lobby = mockDataAPI.getLobbyByScore(request.userScore);
            }

            JmsSender sender = new JmsSender(JmsType.QUEUE, request.userId.toString());
            sender.send(jsonSerializer.toJson(lobby));
        });

        thread.start();
    }

    @Override
    public void receiveHostRequest(GameHostRequest request) {
        //TODO check user score, create and store lobby in data api
        mockDataAPI.addLobby(new LobbyInfo(10, request.lobbyName, request.maxAmountOfPlayers));
    }
}
