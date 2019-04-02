package gateways.concrete;

import gateways.interfaces.IClientGateway;
import jms.concrete.JmsReceiver;
import jms.concrete.JmsSender;
import jms.enums.JmsType;
import models.GameRequest;
import models.GameUpdate;
import models.LobbyInfo;
import models.User;
import utils.JsonSerializer;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.UUID;

public abstract class ClientGateway implements IClientGateway {
    private final JsonSerializer jsonSerializer;

    public ClientGateway(UUID userId) {
        jsonSerializer = new JsonSerializer();

        new JmsReceiver(JmsType.QUEUE, userId.toString()).setCallback(msg -> {
            try {
                TextMessage textMessage = (TextMessage) msg;
                receiveLobby(jsonSerializer.toObject(textMessage.getText(), LobbyInfo.class));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void sendGameRequest(GameRequest request) {
        JmsSender sender = new JmsSender(JmsType.QUEUE, "joinGame");
        sender.send(jsonSerializer.toJson(request));
    }

    @Override
    public abstract void receiveLobby(LobbyInfo lobbyInfo);

    @Override
    public void joinGame(User user, String lobbyName) {
        JmsSender sender = new JmsSender(JmsType.QUEUE, lobbyName);
        sender.send(jsonSerializer.toJson(user));
    }

    @Override
    public void setGameUpdateCallback(String name) {
        new JmsReceiver(JmsType.QUEUE, name).setCallback(msg -> {
            try {
                TextMessage textMessage = (TextMessage) msg;
                receiveGameUpdates(jsonSerializer.toObject(textMessage.getText(), GameUpdate.class));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public abstract void receiveGameUpdates(GameUpdate update);

    @Override
    public void hostGame(LobbyInfo lobbyInfo) {
        JmsSender sender = new JmsSender(JmsType.QUEUE, "hostGame");
        sender.send(jsonSerializer.toJson(lobbyInfo));
    }
}
